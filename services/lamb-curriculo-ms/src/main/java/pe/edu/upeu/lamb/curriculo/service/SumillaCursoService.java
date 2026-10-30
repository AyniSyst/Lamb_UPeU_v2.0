package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.SumillaCursoRequest;
import pe.edu.upeu.lamb.curriculo.dto.SumillaCursoResponse;
import pe.edu.upeu.lamb.curriculo.entity.CursoCatalogo;
import pe.edu.upeu.lamb.curriculo.entity.SumillaCurso;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.SumillaCursoMapper;
import pe.edu.upeu.lamb.curriculo.repository.CursoCatalogoRepository;
import pe.edu.upeu.lamb.curriculo.repository.SumillaCursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SumillaCursoService {

    private final SumillaCursoRepository sumillaCursoRepository;
    private final CursoCatalogoRepository cursoCatalogoRepository;
    private final SumillaCursoMapper sumillaCursoMapper;

    @Transactional(readOnly = true)
    public List<SumillaCursoResponse> listar(Long idCurso) {
        List<SumillaCurso> lista = idCurso == null
                ? sumillaCursoRepository.findAll()
                : sumillaCursoRepository.findByCursoId(idCurso);
        return lista.stream().map(sumillaCursoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public SumillaCursoResponse obtener(Long id) {
        return sumillaCursoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public SumillaCursoResponse crear(SumillaCursoRequest request) {
        validar(request, null);
        SumillaCurso sumillaCurso = sumillaCursoMapper.toEntity(request);
        asignarRelaciones(sumillaCurso, request);
        return sumillaCursoMapper.toResponse(sumillaCursoRepository.save(sumillaCurso));
    }

    @Transactional
    public SumillaCursoResponse actualizar(Long id, SumillaCursoRequest request) {
        SumillaCurso sumillaCurso = buscarOFallar(id);
        validar(request, id);
        sumillaCurso.setNaturaleza(request.getNaturaleza().trim());
        sumillaCurso.setProposito(request.getProposito().trim());
        sumillaCurso.setContenido(request.getContenido().trim());
        asignarRelaciones(sumillaCurso, request);
        return sumillaCursoMapper.toResponse(sumillaCursoRepository.save(sumillaCurso));
    }

    @Transactional
    public void eliminar(Long id) {
        sumillaCursoRepository.delete(buscarOFallar(id));
    }

    SumillaCurso buscarOFallar(Long id) {
        return sumillaCursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sumilla del curso no encontrada: " + id));
    }

    private void validar(SumillaCursoRequest request, Long id) {
        boolean duplicado = id == null
                ? sumillaCursoRepository.existsByCursoId(request.getIdCurso())
                : sumillaCursoRepository.existsByCursoIdAndIdNot(request.getIdCurso(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de sumilla del curso para esa combinacion de: curso");
        }
    }

    private void asignarRelaciones(SumillaCurso sumillaCurso, SumillaCursoRequest request) {
        sumillaCurso.setCurso(cursoCatalogoRepository.findById(request.getIdCurso())
                .orElseThrow(() -> new ResourceNotFoundException("Curso del catalogo no encontrado: " + request.getIdCurso())));
    }
}
