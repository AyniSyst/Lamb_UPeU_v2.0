package pe.edu.upeu.lamb.admision.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.admision.entity.*;
import pe.edu.upeu.lamb.admision.exception.BusinessRuleException;
import pe.edu.upeu.lamb.admision.repository.ModalidadDocumentoRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Reglas de negocio del transaccional de inscripcion.
 *
 * MANDATORY: solo se puede llamar dentro de una transaccion abierta, porque
 * los bloqueos sobre la modalidad y la verificacion de documentos deben vivir
 * hasta el commit (o rollback) de quien llama.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReglasInscripcionService {

    private final ModalidadDocumentoRepository modalidadDocumentoRepository;

    @Transactional(propagation = Propagation.MANDATORY)
    public void validarDocumentosRequeridos(ModalidadAdmision modalidad, List<DocumentoPresentado> presentados) {
        List<ModalidadDocumento> requeridos = modalidadDocumentoRepository
                .findByModalidadIdAndObligatorioTrue(modalidad.getId());

        if (requeridos.isEmpty()) {
            throw new BusinessRuleException("La modalidad " + modalidad.getCodigo()
                    + " no tiene documentos requeridos configurados");
        }

        Set<Long> tiposPresentados = presentados.stream()
                .map(d -> d.getTipoDocumento().getId())
                .collect(Collectors.toSet());

        List<String> faltantes = requeridos.stream()
                .filter(r -> !tiposPresentados.contains(r.getTipoDocumento().getId()))
                .map(r -> r.getTipoDocumento().getCodigo())
                .toList();

        if (!faltantes.isEmpty()) {
            throw new BusinessRuleException("No se puede aprobar la inscripcion: faltan documentos obligatorios "
                    + "para la modalidad " + modalidad.getCodigo() + " -> " + String.join(", ", faltantes));
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void validarTiposDocumentoPermitidos(ModalidadAdmision modalidad, List<DocumentoPresentado> presentados) {
        Set<Long> tiposValidos = modalidadDocumentoRepository.findByModalidadId(modalidad.getId()).stream()
                .map(md -> md.getTipoDocumento().getId())
                .collect(Collectors.toSet());

        List<String> noPermitidos = presentados.stream()
                .map(d -> d.getTipoDocumento())
                .filter(t -> !tiposValidos.contains(t.getId()))
                .map(TipoDocumento::getCodigo)
                .distinct()
                .toList();

        if (!noPermitidos.isEmpty()) {
            throw new BusinessRuleException("Se presentaron documentos no asociados a la modalidad "
                    + modalidad.getCodigo() + ": " + String.join(", ", noPermitidos));
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void reservarVacante(ModalidadAdmision modalidad) {
        if (Boolean.FALSE.equals(modalidad.getActivo())) {
            throw new BusinessRuleException("La modalidad " + modalidad.getCodigo() + " no esta activa");
        }
        int ocupadas = modalidad.getVacantesOcupadas() == null ? 0 : modalidad.getVacantesOcupadas();
        int disponibles = modalidad.getVacantesDisponibles() == null ? 0 : modalidad.getVacantesDisponibles();
        if (ocupadas + 1 > disponibles) {
            throw new BusinessRuleException("No hay vacantes disponibles en la modalidad "
                    + modalidad.getCodigo() + " (" + ocupadas + "/" + disponibles + ")");
        }
        modalidad.setVacantesOcupadas(ocupadas + 1);
        log.info("component=reglas modalidad={} vacantes={}/{} status=reservada",
                modalidad.getCodigo(), modalidad.getVacantesOcupadas(), disponibles);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void liberarVacante(ModalidadAdmision modalidad) {
        int ocupadas = modalidad.getVacantesOcupadas() == null ? 0 : modalidad.getVacantesOcupadas();
        if (ocupadas > 0) {
            modalidad.setVacantesOcupadas(ocupadas - 1);
        }
    }
}
