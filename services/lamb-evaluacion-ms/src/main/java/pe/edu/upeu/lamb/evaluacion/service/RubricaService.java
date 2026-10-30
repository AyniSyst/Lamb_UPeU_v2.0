package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.RubricaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.RubricaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.ComponenteEvaluacion;
import pe.edu.upeu.lamb.evaluacion.entity.Rubrica;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.RubricaMapper;
import pe.edu.upeu.lamb.evaluacion.repository.ComponenteEvaluacionRepository;
import pe.edu.upeu.lamb.evaluacion.repository.RubricaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RubricaService {

    private final RubricaRepository rubricaRepository;
    private final ComponenteEvaluacionRepository componenteEvaluacionRepository;
    private final RubricaMapper rubricaMapper;

    @Transactional(readOnly = true)
    public List<RubricaResponse> listar(Long idComponente) {
        List<Rubrica> lista = idComponente == null
                ? rubricaRepository.findAll()
                : rubricaRepository.findByComponenteId(idComponente);
        return lista.stream().map(rubricaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public RubricaResponse obtener(Long id) {
        return rubricaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public RubricaResponse crear(RubricaRequest request) {
        validar(request, null);
        Rubrica rubrica = rubricaMapper.toEntity(request);
        asignarRelaciones(rubrica, request);
        return rubricaMapper.toResponse(rubricaRepository.save(rubrica));
    }

    @Transactional
    public RubricaResponse actualizar(Long id, RubricaRequest request) {
        Rubrica rubrica = buscarOFallar(id);
        validar(request, id);
        rubrica.setNombre(request.getNombre().trim());
        rubrica.setDescripcion(request.getDescripcion());
        asignarRelaciones(rubrica, request);
        return rubricaMapper.toResponse(rubricaRepository.save(rubrica));
    }

    @Transactional
    public void eliminar(Long id) {
        rubricaRepository.delete(buscarOFallar(id));
    }

    Rubrica buscarOFallar(Long id) {
        return rubricaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rubrica no encontrada: " + id));
    }

    private void validar(RubricaRequest request, Long id) {
    }

    private void asignarRelaciones(Rubrica rubrica, RubricaRequest request) {
        rubrica.setComponente(componenteEvaluacionRepository.findById(request.getIdComponente())
                .orElseThrow(() -> new ResourceNotFoundException("Componente de evaluacion no encontrado: " + request.getIdComponente())));
    }
}
