package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.EncuestaDocenteRequest;
import pe.edu.upeu.lamb.evaluacion.dto.EncuestaDocenteResponse;
import pe.edu.upeu.lamb.evaluacion.entity.EncuestaDocente;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.EncuestaDocenteMapper;
import pe.edu.upeu.lamb.evaluacion.repository.EncuestaDocenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EncuestaDocenteService {

    private final EncuestaDocenteRepository encuestaDocenteRepository;
    private final EncuestaDocenteMapper encuestaDocenteMapper;

    @Transactional(readOnly = true)
    public List<EncuestaDocenteResponse> listar(Long idPeriodo) {
        List<EncuestaDocente> lista = idPeriodo == null
                ? encuestaDocenteRepository.findAll()
                : encuestaDocenteRepository.findByIdPeriodo(idPeriodo);
        return lista.stream().map(encuestaDocenteMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public EncuestaDocenteResponse obtener(Long id) {
        return encuestaDocenteMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public EncuestaDocenteResponse crear(EncuestaDocenteRequest request) {
        validar(request, null);
        EncuestaDocente encuestaDocente = encuestaDocenteMapper.toEntity(request);
        return encuestaDocenteMapper.toResponse(encuestaDocenteRepository.save(encuestaDocente));
    }

    @Transactional
    public EncuestaDocenteResponse actualizar(Long id, EncuestaDocenteRequest request) {
        EncuestaDocente encuestaDocente = buscarOFallar(id);
        validar(request, id);
        encuestaDocente.setNombre(request.getNombre().trim());
        encuestaDocente.setIdPeriodo(request.getIdPeriodo());
        encuestaDocente.setFechaInicio(request.getFechaInicio());
        encuestaDocente.setFechaFin(request.getFechaFin());
        encuestaDocente.setEstado(request.getEstado());
        return encuestaDocenteMapper.toResponse(encuestaDocenteRepository.save(encuestaDocente));
    }

    @Transactional
    public void eliminar(Long id) {
        encuestaDocenteRepository.delete(buscarOFallar(id));
    }

    EncuestaDocente buscarOFallar(Long id) {
        return encuestaDocenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Encuesta docente no encontrada: " + id));
    }

    private void validar(EncuestaDocenteRequest request, Long id) {
        if (request.getFechaFin().isBefore(request.getFechaInicio())) {
            throw new BusinessRuleException("Fecha fin no puede ser anterior a fecha inicio");
        }
    }
}
