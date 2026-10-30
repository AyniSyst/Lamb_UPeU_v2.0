package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.GrupoTallerRequest;
import pe.edu.upeu.lamb.planificacion.dto.GrupoTallerResponse;
import pe.edu.upeu.lamb.planificacion.entity.Curso;
import pe.edu.upeu.lamb.planificacion.entity.GrupoTaller;
import pe.edu.upeu.lamb.planificacion.entity.PeriodoAcademico;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.GrupoTallerMapper;
import pe.edu.upeu.lamb.planificacion.repository.CursoRepository;
import pe.edu.upeu.lamb.planificacion.repository.GrupoTallerRepository;
import pe.edu.upeu.lamb.planificacion.repository.PeriodoAcademicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrupoTallerService {

    private final GrupoTallerRepository grupoTallerRepository;
    private final PeriodoAcademicoRepository periodoAcademicoRepository;
    private final CursoRepository cursoRepository;
    private final GrupoTallerMapper grupoTallerMapper;

    @Transactional(readOnly = true)
    public List<GrupoTallerResponse> listar(Long idPeriodo) {
        List<GrupoTaller> lista = idPeriodo == null
                ? grupoTallerRepository.findAll()
                : grupoTallerRepository.findByPeriodoId(idPeriodo);
        return lista.stream().map(grupoTallerMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public GrupoTallerResponse obtener(Long id) {
        return grupoTallerMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public GrupoTallerResponse crear(GrupoTallerRequest request) {
        validar(request, null);
        GrupoTaller grupoTaller = grupoTallerMapper.toEntity(request);
        asignarRelaciones(grupoTaller, request);
        return grupoTallerMapper.toResponse(grupoTallerRepository.save(grupoTaller));
    }

    @Transactional
    public GrupoTallerResponse actualizar(Long id, GrupoTallerRequest request) {
        GrupoTaller grupoTaller = buscarOFallar(id);
        validar(request, id);
        grupoTaller.setCodigo(request.getCodigo().trim());
        grupoTaller.setNombre(request.getNombre().trim());
        grupoTaller.setCupoMaximo(request.getCupoMaximo());
        grupoTaller.getIdProgramas().clear();
        grupoTaller.getIdProgramas().addAll(request.getIdProgramas());
        asignarRelaciones(grupoTaller, request);
        return grupoTallerMapper.toResponse(grupoTallerRepository.save(grupoTaller));
    }

    @Transactional
    public void eliminar(Long id) {
        grupoTallerRepository.delete(buscarOFallar(id));
    }

    GrupoTaller buscarOFallar(Long id) {
        return grupoTallerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grupo de talleres no encontrado: " + id));
    }

    private void validar(GrupoTallerRequest request, Long id) {
        boolean duplicado = id == null
                ? grupoTallerRepository.existsByPeriodoIdAndCodigo(request.getIdPeriodo(), request.getCodigo().trim())
                : grupoTallerRepository.existsByPeriodoIdAndCodigoAndIdNot(request.getIdPeriodo(), request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de grupo de talleres para esa combinacion de: periodo, codigo");
        }
    }

    private void asignarRelaciones(GrupoTaller grupoTaller, GrupoTallerRequest request) {
        grupoTaller.setPeriodo(periodoAcademicoRepository.findById(request.getIdPeriodo())
                .orElseThrow(() -> new ResourceNotFoundException("Periodo academico no encontrado: " + request.getIdPeriodo())));
        grupoTaller.setCurso(cursoRepository.findById(request.getIdCurso())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + request.getIdCurso())));
    }
}
