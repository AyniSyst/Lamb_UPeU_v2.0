package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.NotaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.NotaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.ComponenteEvaluacion;
import pe.edu.upeu.lamb.evaluacion.entity.EscalaCalificacion;
import pe.edu.upeu.lamb.evaluacion.entity.Nota;
import pe.edu.upeu.lamb.evaluacion.entity.EstadoActa;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.repository.ActaRepository;
import pe.edu.upeu.lamb.evaluacion.mapper.NotaMapper;
import pe.edu.upeu.lamb.evaluacion.repository.ComponenteEvaluacionRepository;
import pe.edu.upeu.lamb.evaluacion.repository.NotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotaService {

    private final NotaRepository notaRepository;
    private final NotaMapper notaMapper;
    private final ActaRepository actaRepository;
    private final ComponenteEvaluacionRepository componenteEvaluacionRepository;
    private final CalculoNotasService calculoNotasService;

    @Transactional(readOnly = true)
    public List<NotaResponse> listar(Long idGrupo) {
        List<Nota> notas = idGrupo == null ? notaRepository.findAll() : notaRepository.findByIdGrupo(idGrupo);
        return notas.stream()
                .map(notaMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public NotaResponse obtener(Long id) {
        return notaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public NotaResponse crear(NotaRequest request) {
        validarActaNoCerrada(request.getIdGrupo());
        validarDentroDeEscala(request.getCalificacion());
        Nota nota = notaMapper.toEntity(request);
        nota.setComponente(buscarComponente(request.getIdComponente()));
        return notaMapper.toResponse(notaRepository.save(nota));
    }

    @Transactional
    public NotaResponse actualizar(Long id, NotaRequest request) {
        Nota nota = buscarOFallar(id);
        validarActaNoCerrada(nota.getIdGrupo());
        validarDentroDeEscala(request.getCalificacion());
        nota.setIdPersona(request.getIdPersona());
        nota.setIdGrupo(request.getIdGrupo());
        nota.setComponente(buscarComponente(request.getIdComponente()));
        nota.setCalificacion(request.getCalificacion());
        nota.setPeriodo(request.getPeriodo());
        return notaMapper.toResponse(notaRepository.save(nota));
    }

    @Transactional
    public void eliminar(Long id) {
        Nota nota = buscarOFallar(id);
        validarActaNoCerrada(nota.getIdGrupo());
        notaRepository.delete(nota);
    }

    // Con el acta CERRADA las notas ya se publicaron a trayectoria: no se pueden cambiar
    private void validarActaNoCerrada(Long idGrupo) {
        actaRepository.findByIdGrupo(idGrupo)
                .filter(acta -> acta.getEstado() == EstadoActa.CERRADA)
                .ifPresent(acta -> {
                    throw new BusinessRuleException("El acta del grupo " + idGrupo + " esta CERRADA: no se pueden registrar ni modificar notas");
                });
    }

    // La calificacion debe caber en la escala vigente (CRUD 3)
    private void validarDentroDeEscala(BigDecimal calificacion) {
        EscalaCalificacion escala = calculoNotasService.escalaVigente();
        if (calificacion.compareTo(escala.getNotaMinima()) < 0 || calificacion.compareTo(escala.getNotaMaxima()) > 0) {
            throw new BusinessRuleException("La calificacion " + calificacion + " esta fuera de la escala " + escala.getCodigo()
                    + " (" + escala.getNotaMinima() + " a " + escala.getNotaMaxima() + ")");
        }
    }

    private ComponenteEvaluacion buscarComponente(Long idComponente) {
        return componenteEvaluacionRepository.findById(idComponente)
                .orElseThrow(() -> new ResourceNotFoundException("Componente de evaluacion no encontrado: " + idComponente));
    }

    private Nota buscarOFallar(Long id) {
        return notaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nota no encontrada: " + id));
    }
}
