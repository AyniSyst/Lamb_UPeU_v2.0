package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.GrupoHabilitadoEquivalenciaRequest;
import pe.edu.upeu.lamb.planificacion.dto.GrupoHabilitadoEquivalenciaResponse;
import pe.edu.upeu.lamb.planificacion.entity.EquivalenciaCurso;
import pe.edu.upeu.lamb.planificacion.entity.Grupo;
import pe.edu.upeu.lamb.planificacion.entity.GrupoHabilitadoEquivalencia;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.GrupoHabilitadoEquivalenciaMapper;
import pe.edu.upeu.lamb.planificacion.repository.EquivalenciaCursoRepository;
import pe.edu.upeu.lamb.planificacion.repository.GrupoHabilitadoEquivalenciaRepository;
import pe.edu.upeu.lamb.planificacion.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrupoHabilitadoEquivalenciaService {

    private final GrupoHabilitadoEquivalenciaRepository grupoHabilitadoEquivalenciaRepository;
    private final EquivalenciaCursoRepository equivalenciaCursoRepository;
    private final GrupoRepository grupoRepository;
    private final GrupoHabilitadoEquivalenciaMapper grupoHabilitadoEquivalenciaMapper;

    @Transactional(readOnly = true)
    public List<GrupoHabilitadoEquivalenciaResponse> listar(Long idEquivalencia) {
        List<GrupoHabilitadoEquivalencia> lista = idEquivalencia == null
                ? grupoHabilitadoEquivalenciaRepository.findAll()
                : grupoHabilitadoEquivalenciaRepository.findByEquivalenciaId(idEquivalencia);
        return lista.stream().map(grupoHabilitadoEquivalenciaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public GrupoHabilitadoEquivalenciaResponse obtener(Long id) {
        return grupoHabilitadoEquivalenciaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public GrupoHabilitadoEquivalenciaResponse crear(GrupoHabilitadoEquivalenciaRequest request) {
        validar(request, null);
        GrupoHabilitadoEquivalencia grupoHabilitadoEquivalencia = grupoHabilitadoEquivalenciaMapper.toEntity(request);
        asignarRelaciones(grupoHabilitadoEquivalencia, request);
        grupoHabilitadoEquivalencia.setCuposDisponibles(request.getCuposHabilitados());
        return grupoHabilitadoEquivalenciaMapper.toResponse(grupoHabilitadoEquivalenciaRepository.save(grupoHabilitadoEquivalencia));
    }

    @Transactional
    public GrupoHabilitadoEquivalenciaResponse actualizar(Long id, GrupoHabilitadoEquivalenciaRequest request) {
        GrupoHabilitadoEquivalencia grupoHabilitadoEquivalencia = buscarOFallar(id);
        validar(request, id);
        // Los cupos ya usados por solicitudes aprobadas se conservan
        int usados = grupoHabilitadoEquivalencia.getCuposHabilitados() - grupoHabilitadoEquivalencia.getCuposDisponibles();
        if (request.getCuposHabilitados() < usados) {
            throw new BusinessRuleException("Ya se usaron " + usados + " cupos por equivalencia; no se puede habilitar menos");
        }
        grupoHabilitadoEquivalencia.setCuposHabilitados(request.getCuposHabilitados());
        grupoHabilitadoEquivalencia.setCuposDisponibles(request.getCuposHabilitados() - usados);
        asignarRelaciones(grupoHabilitadoEquivalencia, request);
        return grupoHabilitadoEquivalenciaMapper.toResponse(grupoHabilitadoEquivalenciaRepository.save(grupoHabilitadoEquivalencia));
    }

    @Transactional
    public void eliminar(Long id) {
        grupoHabilitadoEquivalenciaRepository.delete(buscarOFallar(id));
    }

    GrupoHabilitadoEquivalencia buscarOFallar(Long id) {
        return grupoHabilitadoEquivalenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grupo habilitado por equivalencia no encontrado: " + id));
    }

    private void validar(GrupoHabilitadoEquivalenciaRequest request, Long id) {
        boolean duplicado = id == null
                ? grupoHabilitadoEquivalenciaRepository.existsByEquivalenciaIdAndGrupoId(request.getIdEquivalencia(), request.getIdGrupo())
                : grupoHabilitadoEquivalenciaRepository.existsByEquivalenciaIdAndGrupoIdAndIdNot(request.getIdEquivalencia(), request.getIdGrupo(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de grupo habilitado por equivalencia para esa combinacion de: equivalencia, grupo");
        }
        EquivalenciaCurso equivalencia = equivalenciaCursoRepository.findById(request.getIdEquivalencia())
                .orElseThrow(() -> new ResourceNotFoundException("Equivalencia o variacion de curso no encontrada: " + request.getIdEquivalencia()));
        Grupo grupo = grupoRepository.findById(request.getIdGrupo())
                .orElseThrow(() -> new ResourceNotFoundException("Grupo no encontrado: " + request.getIdGrupo()));
        // El grupo habilitado debe dictar el curso equivalente, no cualquier otro
        if (!grupo.getCurso().getId().equals(equivalencia.getCursoEquivalente().getId())) {
            throw new BusinessRuleException("El grupo " + grupo.getCodigo() + " dicta " + grupo.getCurso().getCodigo()
                    + " y la equivalencia apunta a " + equivalencia.getCursoEquivalente().getCodigo());
        }
        if (request.getCuposHabilitados() > grupo.getCupoMaximo()) {
            throw new BusinessRuleException("No se pueden habilitar mas cupos (" + request.getCuposHabilitados()
                    + ") que el cupo maximo del grupo (" + grupo.getCupoMaximo() + ")");
        }
    }

    private void asignarRelaciones(GrupoHabilitadoEquivalencia grupoHabilitadoEquivalencia, GrupoHabilitadoEquivalenciaRequest request) {
        grupoHabilitadoEquivalencia.setEquivalencia(equivalenciaCursoRepository.findById(request.getIdEquivalencia())
                .orElseThrow(() -> new ResourceNotFoundException("Equivalencia o variacion de curso no encontrada: " + request.getIdEquivalencia())));
        grupoHabilitadoEquivalencia.setGrupo(grupoRepository.findById(request.getIdGrupo())
                .orElseThrow(() -> new ResourceNotFoundException("Grupo no encontrado: " + request.getIdGrupo())));
    }
}
