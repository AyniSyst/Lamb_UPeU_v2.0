package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.ResultadoAprendizajeRequest;
import pe.edu.upeu.lamb.curriculo.dto.ResultadoAprendizajeResponse;
import pe.edu.upeu.lamb.curriculo.entity.CursoCatalogo;
import pe.edu.upeu.lamb.curriculo.entity.ResultadoAprendizaje;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.ResultadoAprendizajeMapper;
import pe.edu.upeu.lamb.curriculo.repository.CursoCatalogoRepository;
import pe.edu.upeu.lamb.curriculo.repository.ResultadoAprendizajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultadoAprendizajeService {

    private final ResultadoAprendizajeRepository resultadoAprendizajeRepository;
    private final CursoCatalogoRepository cursoCatalogoRepository;
    private final ResultadoAprendizajeMapper resultadoAprendizajeMapper;

    @Transactional(readOnly = true)
    public List<ResultadoAprendizajeResponse> listar(Long idCurso) {
        List<ResultadoAprendizaje> lista = idCurso == null
                ? resultadoAprendizajeRepository.findAll()
                : resultadoAprendizajeRepository.findByCursoId(idCurso);
        return lista.stream().map(resultadoAprendizajeMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ResultadoAprendizajeResponse obtener(Long id) {
        return resultadoAprendizajeMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ResultadoAprendizajeResponse crear(ResultadoAprendizajeRequest request) {
        validar(request, null);
        ResultadoAprendizaje resultadoAprendizaje = resultadoAprendizajeMapper.toEntity(request);
        asignarRelaciones(resultadoAprendizaje, request);
        return resultadoAprendizajeMapper.toResponse(resultadoAprendizajeRepository.save(resultadoAprendizaje));
    }

    @Transactional
    public ResultadoAprendizajeResponse actualizar(Long id, ResultadoAprendizajeRequest request) {
        ResultadoAprendizaje resultadoAprendizaje = buscarOFallar(id);
        validar(request, id);
        resultadoAprendizaje.setCodigo(request.getCodigo().trim());
        resultadoAprendizaje.setDescripcion(request.getDescripcion().trim());
        asignarRelaciones(resultadoAprendizaje, request);
        return resultadoAprendizajeMapper.toResponse(resultadoAprendizajeRepository.save(resultadoAprendizaje));
    }

    @Transactional
    public void eliminar(Long id) {
        resultadoAprendizajeRepository.delete(buscarOFallar(id));
    }

    ResultadoAprendizaje buscarOFallar(Long id) {
        return resultadoAprendizajeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resultado de aprendizaje no encontrado: " + id));
    }

    private void validar(ResultadoAprendizajeRequest request, Long id) {
        boolean duplicado = id == null
                ? resultadoAprendizajeRepository.existsByCursoIdAndCodigo(request.getIdCurso(), request.getCodigo().trim())
                : resultadoAprendizajeRepository.existsByCursoIdAndCodigoAndIdNot(request.getIdCurso(), request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de resultado de aprendizaje para esa combinacion de: curso, codigo");
        }
    }

    private void asignarRelaciones(ResultadoAprendizaje resultadoAprendizaje, ResultadoAprendizajeRequest request) {
        resultadoAprendizaje.setCurso(cursoCatalogoRepository.findById(request.getIdCurso())
                .orElseThrow(() -> new ResourceNotFoundException("Curso del catalogo no encontrado: " + request.getIdCurso())));
    }
}
