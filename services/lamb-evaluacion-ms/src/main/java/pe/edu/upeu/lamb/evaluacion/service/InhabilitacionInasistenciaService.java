package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.InhabilitacionInasistenciaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.InhabilitacionInasistenciaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.InhabilitacionInasistencia;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.InhabilitacionInasistenciaMapper;
import pe.edu.upeu.lamb.evaluacion.repository.InhabilitacionInasistenciaRepository;
import pe.edu.upeu.lamb.evaluacion.entity.EstadoAsistencia;
import pe.edu.upeu.lamb.evaluacion.repository.RegistroAsistenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InhabilitacionInasistenciaService {

    private static final BigDecimal LIMITE_INASISTENCIA = new BigDecimal("30");

    private final InhabilitacionInasistenciaRepository inhabilitacionInasistenciaRepository;
    private final RegistroAsistenciaRepository registroAsistenciaRepository;
    private final InhabilitacionInasistenciaMapper inhabilitacionInasistenciaMapper;

    @Transactional(readOnly = true)
    public List<InhabilitacionInasistenciaResponse> listar(Long idGrupo) {
        List<InhabilitacionInasistencia> lista = idGrupo == null
                ? inhabilitacionInasistenciaRepository.findAll()
                : inhabilitacionInasistenciaRepository.findByIdGrupo(idGrupo);
        return lista.stream().map(inhabilitacionInasistenciaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public InhabilitacionInasistenciaResponse obtener(Long id) {
        return inhabilitacionInasistenciaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public InhabilitacionInasistenciaResponse crear(InhabilitacionInasistenciaRequest request) {
        validar(request, null);
        InhabilitacionInasistencia inhabilitacionInasistencia = inhabilitacionInasistenciaMapper.toEntity(request);
        inhabilitacionInasistencia.setPorcentajeInasistencia(calcularPorcentaje(request.getIdGrupo(), request.getIdPersona()));
        return inhabilitacionInasistenciaMapper.toResponse(inhabilitacionInasistenciaRepository.save(inhabilitacionInasistencia));
    }

    @Transactional
    public InhabilitacionInasistenciaResponse actualizar(Long id, InhabilitacionInasistenciaRequest request) {
        InhabilitacionInasistencia inhabilitacionInasistencia = buscarOFallar(id);
        validar(request, id);
        inhabilitacionInasistencia.setIdGrupo(request.getIdGrupo());
        inhabilitacionInasistencia.setIdPersona(request.getIdPersona());
        inhabilitacionInasistencia.setFecha(request.getFecha());
        inhabilitacionInasistencia.setPorcentajeInasistencia(calcularPorcentaje(request.getIdGrupo(), request.getIdPersona()));
        inhabilitacionInasistencia.setObservacion(request.getObservacion());
        return inhabilitacionInasistenciaMapper.toResponse(inhabilitacionInasistenciaRepository.save(inhabilitacionInasistencia));
    }

    @Transactional
    public void eliminar(Long id) {
        inhabilitacionInasistenciaRepository.delete(buscarOFallar(id));
    }

    // Faltas sin justificar / sesiones registradas; inhabilita desde el 30 % (reglamento de la universidad)
    private BigDecimal calcularPorcentaje(Long idGrupo, Long idPersona) {
        long registradas = registroAsistenciaRepository.countByIdGrupoAndIdPersona(idGrupo, idPersona);
        if (registradas == 0) {
            throw new BusinessRuleException("El estudiante " + idPersona + " no tiene asistencias registradas en el grupo " + idGrupo);
        }
        long faltas = registroAsistenciaRepository.countByIdGrupoAndIdPersonaAndEstado(idGrupo, idPersona, EstadoAsistencia.FALTA);
        BigDecimal porcentaje = BigDecimal.valueOf(faltas * 100).divide(BigDecimal.valueOf(registradas), 2, RoundingMode.HALF_UP);
        if (porcentaje.compareTo(LIMITE_INASISTENCIA) < 0) {
            throw new BusinessRuleException("El estudiante " + idPersona + " tiene " + porcentaje
                    + " % de inasistencias; la inhabilitacion es desde el " + LIMITE_INASISTENCIA + " %");
        }
        return porcentaje;
    }

    InhabilitacionInasistencia buscarOFallar(Long id) {
        return inhabilitacionInasistenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inhabilitacion por inasistencia no encontrada: " + id));
    }

    private void validar(InhabilitacionInasistenciaRequest request, Long id) {
        boolean duplicado = id == null
                ? inhabilitacionInasistenciaRepository.existsByIdGrupoAndIdPersona(request.getIdGrupo(), request.getIdPersona())
                : inhabilitacionInasistenciaRepository.existsByIdGrupoAndIdPersonaAndIdNot(request.getIdGrupo(), request.getIdPersona(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de inhabilitacion por inasistencia para esa combinacion de: grupo, persona");
        }
    }
}
