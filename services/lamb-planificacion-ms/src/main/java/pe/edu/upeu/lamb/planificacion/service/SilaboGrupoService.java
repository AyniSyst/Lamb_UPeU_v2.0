package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.SilaboGrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.SilaboGrupoResponse;
import pe.edu.upeu.lamb.planificacion.entity.Grupo;
import pe.edu.upeu.lamb.planificacion.entity.SilaboGrupo;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.SilaboGrupoMapper;
import pe.edu.upeu.lamb.planificacion.repository.GrupoRepository;
import pe.edu.upeu.lamb.planificacion.repository.SilaboGrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SilaboGrupoService {

    private final SilaboGrupoRepository silaboGrupoRepository;
    private final GrupoRepository grupoRepository;
    private final SilaboGrupoMapper silaboGrupoMapper;

    @Transactional(readOnly = true)
    public List<SilaboGrupoResponse> listar(Long idGrupo) {
        List<SilaboGrupo> lista = idGrupo == null
                ? silaboGrupoRepository.findAll()
                : silaboGrupoRepository.findByGrupoId(idGrupo);
        return lista.stream().map(silaboGrupoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public SilaboGrupoResponse obtener(Long id) {
        return silaboGrupoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public SilaboGrupoResponse crear(SilaboGrupoRequest request) {
        validar(request, null);
        SilaboGrupo silaboGrupo = silaboGrupoMapper.toEntity(request);
        asignarRelaciones(silaboGrupo, request);
        return silaboGrupoMapper.toResponse(silaboGrupoRepository.save(silaboGrupo));
    }

    @Transactional
    public SilaboGrupoResponse actualizar(Long id, SilaboGrupoRequest request) {
        SilaboGrupo silaboGrupo = buscarOFallar(id);
        validar(request, id);
        silaboGrupo.setIdSilaboBase(request.getIdSilaboBase());
        silaboGrupo.setMetodologia(request.getMetodologia());
        silaboGrupo.setEstado(request.getEstado());
        silaboGrupo.setFechaPresentacion(request.getFechaPresentacion());
        asignarRelaciones(silaboGrupo, request);
        return silaboGrupoMapper.toResponse(silaboGrupoRepository.save(silaboGrupo));
    }

    @Transactional
    public void eliminar(Long id) {
        silaboGrupoRepository.delete(buscarOFallar(id));
    }

    SilaboGrupo buscarOFallar(Long id) {
        return silaboGrupoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Silabo de grupo no encontrado: " + id));
    }

    private void validar(SilaboGrupoRequest request, Long id) {
        boolean duplicado = id == null
                ? silaboGrupoRepository.existsByGrupoId(request.getIdGrupo())
                : silaboGrupoRepository.existsByGrupoIdAndIdNot(request.getIdGrupo(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de silabo de grupo para esa combinacion de: grupo");
        }
    }

    private void asignarRelaciones(SilaboGrupo silaboGrupo, SilaboGrupoRequest request) {
        silaboGrupo.setGrupo(grupoRepository.findById(request.getIdGrupo())
                .orElseThrow(() -> new ResourceNotFoundException("Grupo no encontrado: " + request.getIdGrupo())));
    }
}
