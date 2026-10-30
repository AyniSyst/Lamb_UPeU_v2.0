package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.CursoExternoConvalidableRequest;
import pe.edu.upeu.lamb.curriculo.dto.CursoExternoConvalidableResponse;
import pe.edu.upeu.lamb.curriculo.entity.CursoExternoConvalidable;
import pe.edu.upeu.lamb.curriculo.entity.PlanCurso;
import pe.edu.upeu.lamb.curriculo.entity.TablaConvalidacion;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.CursoExternoConvalidableMapper;
import pe.edu.upeu.lamb.curriculo.repository.CursoExternoConvalidableRepository;
import pe.edu.upeu.lamb.curriculo.repository.PlanCursoRepository;
import pe.edu.upeu.lamb.curriculo.repository.TablaConvalidacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoExternoConvalidableService {

    private final CursoExternoConvalidableRepository cursoExternoConvalidableRepository;
    private final TablaConvalidacionRepository tablaConvalidacionRepository;
    private final PlanCursoRepository planCursoRepository;
    private final CursoExternoConvalidableMapper cursoExternoConvalidableMapper;

    @Transactional(readOnly = true)
    public List<CursoExternoConvalidableResponse> listar(Long idTabla) {
        List<CursoExternoConvalidable> lista = idTabla == null
                ? cursoExternoConvalidableRepository.findAll()
                : cursoExternoConvalidableRepository.findByTablaId(idTabla);
        return lista.stream().map(cursoExternoConvalidableMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CursoExternoConvalidableResponse obtener(Long id) {
        return cursoExternoConvalidableMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CursoExternoConvalidableResponse crear(CursoExternoConvalidableRequest request) {
        validar(request, null);
        CursoExternoConvalidable cursoExternoConvalidable = cursoExternoConvalidableMapper.toEntity(request);
        asignarRelaciones(cursoExternoConvalidable, request);
        return cursoExternoConvalidableMapper.toResponse(cursoExternoConvalidableRepository.save(cursoExternoConvalidable));
    }

    @Transactional
    public CursoExternoConvalidableResponse actualizar(Long id, CursoExternoConvalidableRequest request) {
        CursoExternoConvalidable cursoExternoConvalidable = buscarOFallar(id);
        validar(request, id);
        cursoExternoConvalidable.setCodigoExterno(request.getCodigoExterno().trim());
        cursoExternoConvalidable.setNombreExterno(request.getNombreExterno().trim());
        cursoExternoConvalidable.setCreditosExterno(request.getCreditosExterno());
        cursoExternoConvalidable.setNotaMinima(request.getNotaMinima());
        asignarRelaciones(cursoExternoConvalidable, request);
        return cursoExternoConvalidableMapper.toResponse(cursoExternoConvalidableRepository.save(cursoExternoConvalidable));
    }

    @Transactional
    public void eliminar(Long id) {
        cursoExternoConvalidableRepository.delete(buscarOFallar(id));
    }

    CursoExternoConvalidable buscarOFallar(Long id) {
        return cursoExternoConvalidableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso externo convalidable no encontrado: " + id));
    }

    private void validar(CursoExternoConvalidableRequest request, Long id) {
        boolean duplicado = id == null
                ? cursoExternoConvalidableRepository.existsByTablaIdAndCodigoExterno(request.getIdTabla(), request.getCodigoExterno().trim())
                : cursoExternoConvalidableRepository.existsByTablaIdAndCodigoExternoAndIdNot(request.getIdTabla(), request.getCodigoExterno().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de curso externo convalidable para esa combinacion de: tabla, codigo externo");
        }
    }

    private void asignarRelaciones(CursoExternoConvalidable cursoExternoConvalidable, CursoExternoConvalidableRequest request) {
        cursoExternoConvalidable.setTabla(tablaConvalidacionRepository.findById(request.getIdTabla())
                .orElseThrow(() -> new ResourceNotFoundException("Tabla de convalidacion no encontrada: " + request.getIdTabla())));
        cursoExternoConvalidable.setPlanCurso(planCursoRepository.findById(request.getIdPlanCurso())
                .orElseThrow(() -> new ResourceNotFoundException("Curso del plan no encontrado: " + request.getIdPlanCurso())));
    }
}
