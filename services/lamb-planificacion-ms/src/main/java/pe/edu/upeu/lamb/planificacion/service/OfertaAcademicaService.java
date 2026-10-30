package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.OfertaAcademicaRequest;
import pe.edu.upeu.lamb.planificacion.dto.OfertaAcademicaResponse;
import pe.edu.upeu.lamb.planificacion.entity.Curso;
import pe.edu.upeu.lamb.planificacion.entity.OfertaAcademica;
import pe.edu.upeu.lamb.planificacion.entity.PeriodoAcademico;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.OfertaAcademicaMapper;
import pe.edu.upeu.lamb.planificacion.repository.CursoRepository;
import pe.edu.upeu.lamb.planificacion.repository.OfertaAcademicaRepository;
import pe.edu.upeu.lamb.planificacion.repository.PeriodoAcademicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfertaAcademicaService {

    private final OfertaAcademicaRepository ofertaAcademicaRepository;
    private final PeriodoAcademicoRepository periodoAcademicoRepository;
    private final CursoRepository cursoRepository;
    private final OfertaAcademicaMapper ofertaAcademicaMapper;

    @Transactional(readOnly = true)
    public List<OfertaAcademicaResponse> listar(Long idPeriodo) {
        List<OfertaAcademica> lista = idPeriodo == null
                ? ofertaAcademicaRepository.findAll()
                : ofertaAcademicaRepository.findByPeriodoId(idPeriodo);
        return lista.stream().map(ofertaAcademicaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public OfertaAcademicaResponse obtener(Long id) {
        return ofertaAcademicaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public OfertaAcademicaResponse crear(OfertaAcademicaRequest request) {
        validar(request, null);
        OfertaAcademica ofertaAcademica = ofertaAcademicaMapper.toEntity(request);
        asignarRelaciones(ofertaAcademica, request);
        return ofertaAcademicaMapper.toResponse(ofertaAcademicaRepository.save(ofertaAcademica));
    }

    @Transactional
    public OfertaAcademicaResponse actualizar(Long id, OfertaAcademicaRequest request) {
        OfertaAcademica ofertaAcademica = buscarOFallar(id);
        validar(request, id);
        ofertaAcademica.setIdPrograma(request.getIdPrograma());
        ofertaAcademica.setCiclo(request.getCiclo());
        ofertaAcademica.setEstado(request.getEstado());
        asignarRelaciones(ofertaAcademica, request);
        return ofertaAcademicaMapper.toResponse(ofertaAcademicaRepository.save(ofertaAcademica));
    }

    @Transactional
    public void eliminar(Long id) {
        ofertaAcademicaRepository.delete(buscarOFallar(id));
    }

    OfertaAcademica buscarOFallar(Long id) {
        return ofertaAcademicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Oferta academica no encontrada: " + id));
    }

    private void validar(OfertaAcademicaRequest request, Long id) {
        boolean duplicado = id == null
                ? ofertaAcademicaRepository.existsByPeriodoIdAndIdProgramaAndCursoId(request.getIdPeriodo(), request.getIdPrograma(), request.getIdCurso())
                : ofertaAcademicaRepository.existsByPeriodoIdAndIdProgramaAndCursoIdAndIdNot(request.getIdPeriodo(), request.getIdPrograma(), request.getIdCurso(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de oferta academica para esa combinacion de: periodo, programa, curso");
        }
    }

    private void asignarRelaciones(OfertaAcademica ofertaAcademica, OfertaAcademicaRequest request) {
        ofertaAcademica.setPeriodo(periodoAcademicoRepository.findById(request.getIdPeriodo())
                .orElseThrow(() -> new ResourceNotFoundException("Periodo academico no encontrado: " + request.getIdPeriodo())));
        ofertaAcademica.setCurso(cursoRepository.findById(request.getIdCurso())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + request.getIdCurso())));
    }
}
