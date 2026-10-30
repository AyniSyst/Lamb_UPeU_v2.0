package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.CriterioRubricaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.CriterioRubricaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.CriterioRubrica;
import pe.edu.upeu.lamb.evaluacion.entity.Rubrica;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.CriterioRubricaMapper;
import pe.edu.upeu.lamb.evaluacion.repository.CriterioRubricaRepository;
import pe.edu.upeu.lamb.evaluacion.repository.RubricaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CriterioRubricaService {

    private final CriterioRubricaRepository criterioRubricaRepository;
    private final RubricaRepository rubricaRepository;
    private final CriterioRubricaMapper criterioRubricaMapper;

    @Transactional(readOnly = true)
    public List<CriterioRubricaResponse> listar(Long idRubrica) {
        List<CriterioRubrica> lista = idRubrica == null
                ? criterioRubricaRepository.findAll()
                : criterioRubricaRepository.findByRubricaId(idRubrica);
        return lista.stream().map(criterioRubricaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CriterioRubricaResponse obtener(Long id) {
        return criterioRubricaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CriterioRubricaResponse crear(CriterioRubricaRequest request) {
        validar(request, null);
        CriterioRubrica criterioRubrica = criterioRubricaMapper.toEntity(request);
        asignarRelaciones(criterioRubrica, request);
        return criterioRubricaMapper.toResponse(criterioRubricaRepository.save(criterioRubrica));
    }

    @Transactional
    public CriterioRubricaResponse actualizar(Long id, CriterioRubricaRequest request) {
        CriterioRubrica criterioRubrica = buscarOFallar(id);
        validar(request, id);
        criterioRubrica.setOrden(request.getOrden());
        criterioRubrica.setDescripcion(request.getDescripcion().trim());
        criterioRubrica.setPuntajeMaximo(request.getPuntajeMaximo());
        asignarRelaciones(criterioRubrica, request);
        return criterioRubricaMapper.toResponse(criterioRubricaRepository.save(criterioRubrica));
    }

    @Transactional
    public void eliminar(Long id) {
        criterioRubricaRepository.delete(buscarOFallar(id));
    }

    CriterioRubrica buscarOFallar(Long id) {
        return criterioRubricaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Criterio de rubrica no encontrado: " + id));
    }

    private void validar(CriterioRubricaRequest request, Long id) {
        boolean duplicado = id == null
                ? criterioRubricaRepository.existsByRubricaIdAndOrden(request.getIdRubrica(), request.getOrden())
                : criterioRubricaRepository.existsByRubricaIdAndOrdenAndIdNot(request.getIdRubrica(), request.getOrden(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de criterio de rubrica para esa combinacion de: rubrica, orden");
        }
    }

    private void asignarRelaciones(CriterioRubrica criterioRubrica, CriterioRubricaRequest request) {
        criterioRubrica.setRubrica(rubricaRepository.findById(request.getIdRubrica())
                .orElseThrow(() -> new ResourceNotFoundException("Rubrica no encontrada: " + request.getIdRubrica())));
    }
}
