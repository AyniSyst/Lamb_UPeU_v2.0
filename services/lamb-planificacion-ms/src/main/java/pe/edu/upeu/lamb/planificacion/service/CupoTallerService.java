package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.CupoTallerRequest;
import pe.edu.upeu.lamb.planificacion.dto.CupoTallerResponse;
import pe.edu.upeu.lamb.planificacion.entity.CupoTaller;
import pe.edu.upeu.lamb.planificacion.entity.GrupoTaller;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.CupoTallerMapper;
import pe.edu.upeu.lamb.planificacion.repository.CupoTallerRepository;
import pe.edu.upeu.lamb.planificacion.repository.GrupoTallerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CupoTallerService {

    private final CupoTallerRepository cupoTallerRepository;
    private final GrupoTallerRepository grupoTallerRepository;
    private final CupoTallerMapper cupoTallerMapper;

    @Transactional(readOnly = true)
    public List<CupoTallerResponse> listar(Long idGrupoTaller) {
        List<CupoTaller> lista = idGrupoTaller == null
                ? cupoTallerRepository.findAll()
                : cupoTallerRepository.findByGrupoTallerId(idGrupoTaller);
        return lista.stream().map(cupoTallerMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CupoTallerResponse obtener(Long id) {
        return cupoTallerMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CupoTallerResponse crear(CupoTallerRequest request) {
        validar(request, null);
        CupoTaller cupoTaller = cupoTallerMapper.toEntity(request);
        asignarRelaciones(cupoTaller, request);
        return cupoTallerMapper.toResponse(cupoTallerRepository.save(cupoTaller));
    }

    @Transactional
    public CupoTallerResponse actualizar(Long id, CupoTallerRequest request) {
        CupoTaller cupoTaller = buscarOFallar(id);
        validar(request, id);
        cupoTaller.setIdPrograma(request.getIdPrograma());
        cupoTaller.setCantidad(request.getCantidad());
        asignarRelaciones(cupoTaller, request);
        return cupoTallerMapper.toResponse(cupoTallerRepository.save(cupoTaller));
    }

    @Transactional
    public void eliminar(Long id) {
        cupoTallerRepository.delete(buscarOFallar(id));
    }

    CupoTaller buscarOFallar(Long id) {
        return cupoTallerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cupo de taller no encontrado: " + id));
    }

    private void validar(CupoTallerRequest request, Long id) {
        boolean duplicado = id == null
                ? cupoTallerRepository.existsByGrupoTallerIdAndIdPrograma(request.getIdGrupoTaller(), request.getIdPrograma())
                : cupoTallerRepository.existsByGrupoTallerIdAndIdProgramaAndIdNot(request.getIdGrupoTaller(), request.getIdPrograma(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de cupo de taller para esa combinacion de: grupo taller, programa");
        }
        GrupoTaller taller = grupoTallerRepository.findById(request.getIdGrupoTaller())
                .orElseThrow(() -> new ResourceNotFoundException("Grupo de talleres no encontrado: " + request.getIdGrupoTaller()));
        if (!taller.getIdProgramas().contains(request.getIdPrograma())) {
            throw new BusinessRuleException("El programa " + request.getIdPrograma() + " no participa del taller " + taller.getCodigo());
        }
        long repartidos = cupoTallerRepository.sumarCantidadDelTaller(taller.getId(), id == null ? 0L : id);
        if (repartidos + request.getCantidad() > taller.getCupoMaximo()) {
            throw new BusinessRuleException("El taller " + taller.getCodigo() + " tiene cupo " + taller.getCupoMaximo()
                    + " y ya reparte " + repartidos + "; no alcanza para " + request.getCantidad() + " mas");
        }
    }

    private void asignarRelaciones(CupoTaller cupoTaller, CupoTallerRequest request) {
        cupoTaller.setGrupoTaller(grupoTallerRepository.findById(request.getIdGrupoTaller())
                .orElseThrow(() -> new ResourceNotFoundException("Grupo de talleres no encontrado: " + request.getIdGrupoTaller())));
    }
}
