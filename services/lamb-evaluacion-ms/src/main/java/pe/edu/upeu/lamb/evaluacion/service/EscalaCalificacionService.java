package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.EscalaCalificacionRequest;
import pe.edu.upeu.lamb.evaluacion.dto.EscalaCalificacionResponse;
import pe.edu.upeu.lamb.evaluacion.entity.EscalaCalificacion;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.EscalaCalificacionMapper;
import pe.edu.upeu.lamb.evaluacion.repository.EscalaCalificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EscalaCalificacionService {

    private final EscalaCalificacionRepository escalaCalificacionRepository;
    private final EscalaCalificacionMapper escalaCalificacionMapper;

    @Transactional(readOnly = true)
    public List<EscalaCalificacionResponse> listar() {
        return escalaCalificacionRepository.findAll().stream()
                .map(escalaCalificacionMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public EscalaCalificacionResponse obtener(Long id) {
        return escalaCalificacionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public EscalaCalificacionResponse crear(EscalaCalificacionRequest request) {
        validar(request, null);
        EscalaCalificacion escalaCalificacion = escalaCalificacionMapper.toEntity(request);
        return escalaCalificacionMapper.toResponse(escalaCalificacionRepository.save(escalaCalificacion));
    }

    @Transactional
    public EscalaCalificacionResponse actualizar(Long id, EscalaCalificacionRequest request) {
        EscalaCalificacion escalaCalificacion = buscarOFallar(id);
        validar(request, id);
        escalaCalificacion.setCodigo(request.getCodigo().trim());
        escalaCalificacion.setNombre(request.getNombre().trim());
        escalaCalificacion.setNotaMinima(request.getNotaMinima());
        escalaCalificacion.setNotaMaxima(request.getNotaMaxima());
        escalaCalificacion.setNotaAprobatoria(request.getNotaAprobatoria());
        escalaCalificacion.setDecimales(request.getDecimales());
        escalaCalificacion.setRedondeo(request.getRedondeo());
        escalaCalificacion.setVigente(request.getVigente());
        return escalaCalificacionMapper.toResponse(escalaCalificacionRepository.save(escalaCalificacion));
    }

    @Transactional
    public void eliminar(Long id) {
        escalaCalificacionRepository.delete(buscarOFallar(id));
    }

    EscalaCalificacion buscarOFallar(Long id) {
        return escalaCalificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Escala de calificacion no encontrada: " + id));
    }

    private void validar(EscalaCalificacionRequest request, Long id) {
        boolean duplicado = id == null
                ? escalaCalificacionRepository.existsByCodigo(request.getCodigo().trim())
                : escalaCalificacionRepository.existsByCodigoAndIdNot(request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de escala de calificacion para esa combinacion de: codigo");
        }
        if (request.getNotaMinima().compareTo(request.getNotaAprobatoria()) >= 0
                || request.getNotaAprobatoria().compareTo(request.getNotaMaxima()) > 0) {
            throw new BusinessRuleException("La escala debe cumplir: nota minima < nota aprobatoria <= nota maxima");
        }
        if (Boolean.TRUE.equals(request.getVigente())
                && escalaCalificacionRepository.existsByVigenteTrueAndIdNot(id == null ? 0L : id)) {
            throw new BusinessRuleException("Ya hay una escala vigente; desactivala antes de marcar otra");
        }
    }
}
