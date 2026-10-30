package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.TipoCursoRequest;
import pe.edu.upeu.lamb.curriculo.dto.TipoCursoResponse;
import pe.edu.upeu.lamb.curriculo.entity.TipoCurso;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.TipoCursoMapper;
import pe.edu.upeu.lamb.curriculo.repository.TipoCursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoCursoService {

    private final TipoCursoRepository tipoCursoRepository;
    private final TipoCursoMapper tipoCursoMapper;

    @Transactional(readOnly = true)
    public List<TipoCursoResponse> listar() {
        return tipoCursoRepository.findAll().stream()
                .map(tipoCursoMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public TipoCursoResponse obtener(Long id) {
        return tipoCursoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public TipoCursoResponse crear(TipoCursoRequest request) {
        validar(request, null);
        TipoCurso tipoCurso = tipoCursoMapper.toEntity(request);
        return tipoCursoMapper.toResponse(tipoCursoRepository.save(tipoCurso));
    }

    @Transactional
    public TipoCursoResponse actualizar(Long id, TipoCursoRequest request) {
        TipoCurso tipoCurso = buscarOFallar(id);
        validar(request, id);
        tipoCurso.setCodigo(request.getCodigo().trim());
        tipoCurso.setNombre(request.getNombre().trim());
        tipoCurso.setDescripcion(request.getDescripcion());
        return tipoCursoMapper.toResponse(tipoCursoRepository.save(tipoCurso));
    }

    @Transactional
    public void eliminar(Long id) {
        tipoCursoRepository.delete(buscarOFallar(id));
    }

    TipoCurso buscarOFallar(Long id) {
        return tipoCursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de curso no encontrado: " + id));
    }

    private void validar(TipoCursoRequest request, Long id) {
        boolean duplicado = id == null
                ? tipoCursoRepository.existsByCodigo(request.getCodigo().trim())
                : tipoCursoRepository.existsByCodigoAndIdNot(request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de tipo de curso para esa combinacion de: codigo");
        }
    }
}
