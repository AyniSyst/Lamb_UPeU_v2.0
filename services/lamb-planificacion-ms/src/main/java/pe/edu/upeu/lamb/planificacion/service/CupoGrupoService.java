package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.CupoGrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.CupoGrupoResponse;
import pe.edu.upeu.lamb.planificacion.entity.CupoGrupo;
import pe.edu.upeu.lamb.planificacion.entity.Grupo;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.CupoGrupoMapper;
import pe.edu.upeu.lamb.planificacion.repository.CupoGrupoRepository;
import pe.edu.upeu.lamb.planificacion.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CupoGrupoService {

    private final CupoGrupoRepository cupoGrupoRepository;
    private final GrupoRepository grupoRepository;
    private final CupoGrupoMapper cupoGrupoMapper;

    @Transactional(readOnly = true)
    public List<CupoGrupoResponse> listar(Long idGrupo) {
        List<CupoGrupo> lista = idGrupo == null
                ? cupoGrupoRepository.findAll()
                : cupoGrupoRepository.findByGrupoId(idGrupo);
        return lista.stream().map(cupoGrupoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CupoGrupoResponse obtener(Long id) {
        return cupoGrupoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CupoGrupoResponse crear(CupoGrupoRequest request) {
        validar(request, null);
        CupoGrupo cupoGrupo = cupoGrupoMapper.toEntity(request);
        asignarRelaciones(cupoGrupo, request);
        return cupoGrupoMapper.toResponse(cupoGrupoRepository.save(cupoGrupo));
    }

    @Transactional
    public CupoGrupoResponse actualizar(Long id, CupoGrupoRequest request) {
        CupoGrupo cupoGrupo = buscarOFallar(id);
        validar(request, id);
        cupoGrupo.setTipo(request.getTipo());
        cupoGrupo.setCantidad(request.getCantidad());
        asignarRelaciones(cupoGrupo, request);
        return cupoGrupoMapper.toResponse(cupoGrupoRepository.save(cupoGrupo));
    }

    @Transactional
    public void eliminar(Long id) {
        cupoGrupoRepository.delete(buscarOFallar(id));
    }

    CupoGrupo buscarOFallar(Long id) {
        return cupoGrupoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cupo de grupo no encontrado: " + id));
    }

    private void validar(CupoGrupoRequest request, Long id) {
        boolean duplicado = id == null
                ? cupoGrupoRepository.existsByGrupoIdAndTipo(request.getIdGrupo(), request.getTipo())
                : cupoGrupoRepository.existsByGrupoIdAndTipoAndIdNot(request.getIdGrupo(), request.getTipo(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de cupo de grupo para esa combinacion de: grupo, tipo");
        }
        // Regla: la suma de los cupos por tipo no puede superar el cupo maximo del grupo
        Grupo grupo = grupoRepository.findById(request.getIdGrupo())
                .orElseThrow(() -> new ResourceNotFoundException("Grupo no encontrado: " + request.getIdGrupo()));
        long repartidos = cupoGrupoRepository.sumarCantidadDelGrupo(grupo.getId(), id == null ? 0L : id);
        if (repartidos + request.getCantidad() > grupo.getCupoMaximo()) {
            throw new BusinessRuleException("El grupo " + grupo.getCodigo() + " tiene cupo " + grupo.getCupoMaximo()
                    + " y ya reparte " + repartidos + "; no alcanza para " + request.getCantidad() + " mas");
        }
    }

    private void asignarRelaciones(CupoGrupo cupoGrupo, CupoGrupoRequest request) {
        cupoGrupo.setGrupo(grupoRepository.findById(request.getIdGrupo())
                .orElseThrow(() -> new ResourceNotFoundException("Grupo no encontrado: " + request.getIdGrupo())));
    }
}
