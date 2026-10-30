package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.AulaVirtualGrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.AulaVirtualGrupoResponse;
import pe.edu.upeu.lamb.planificacion.entity.AulaVirtualGrupo;
import pe.edu.upeu.lamb.planificacion.entity.Grupo;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.AulaVirtualGrupoMapper;
import pe.edu.upeu.lamb.planificacion.repository.AulaVirtualGrupoRepository;
import pe.edu.upeu.lamb.planificacion.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AulaVirtualGrupoService {

    private final AulaVirtualGrupoRepository aulaVirtualGrupoRepository;
    private final GrupoRepository grupoRepository;
    private final AulaVirtualGrupoMapper aulaVirtualGrupoMapper;

    @Transactional(readOnly = true)
    public List<AulaVirtualGrupoResponse> listar(Long idGrupo) {
        List<AulaVirtualGrupo> lista = idGrupo == null
                ? aulaVirtualGrupoRepository.findAll()
                : aulaVirtualGrupoRepository.findByGrupoId(idGrupo);
        return lista.stream().map(aulaVirtualGrupoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public AulaVirtualGrupoResponse obtener(Long id) {
        return aulaVirtualGrupoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public AulaVirtualGrupoResponse crear(AulaVirtualGrupoRequest request) {
        validar(request, null);
        AulaVirtualGrupo aulaVirtualGrupo = aulaVirtualGrupoMapper.toEntity(request);
        asignarRelaciones(aulaVirtualGrupo, request);
        return aulaVirtualGrupoMapper.toResponse(aulaVirtualGrupoRepository.save(aulaVirtualGrupo));
    }

    @Transactional
    public AulaVirtualGrupoResponse actualizar(Long id, AulaVirtualGrupoRequest request) {
        AulaVirtualGrupo aulaVirtualGrupo = buscarOFallar(id);
        validar(request, id);
        aulaVirtualGrupo.setPlataforma(request.getPlataforma());
        aulaVirtualGrupo.setEnlace(request.getEnlace().trim());
        aulaVirtualGrupo.setCodigoAcceso(request.getCodigoAcceso());
        asignarRelaciones(aulaVirtualGrupo, request);
        return aulaVirtualGrupoMapper.toResponse(aulaVirtualGrupoRepository.save(aulaVirtualGrupo));
    }

    @Transactional
    public void eliminar(Long id) {
        aulaVirtualGrupoRepository.delete(buscarOFallar(id));
    }

    AulaVirtualGrupo buscarOFallar(Long id) {
        return aulaVirtualGrupoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula virtual del grupo no encontrada: " + id));
    }

    private void validar(AulaVirtualGrupoRequest request, Long id) {
        boolean duplicado = id == null
                ? aulaVirtualGrupoRepository.existsByGrupoId(request.getIdGrupo())
                : aulaVirtualGrupoRepository.existsByGrupoIdAndIdNot(request.getIdGrupo(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de aula virtual del grupo para esa combinacion de: grupo");
        }
    }

    private void asignarRelaciones(AulaVirtualGrupo aulaVirtualGrupo, AulaVirtualGrupoRequest request) {
        aulaVirtualGrupo.setGrupo(grupoRepository.findById(request.getIdGrupo())
                .orElseThrow(() -> new ResourceNotFoundException("Grupo no encontrado: " + request.getIdGrupo())));
    }
}
