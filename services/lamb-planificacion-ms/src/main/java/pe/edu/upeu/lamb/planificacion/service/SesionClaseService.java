package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.SesionClaseRequest;
import pe.edu.upeu.lamb.planificacion.dto.SesionClaseResponse;
import pe.edu.upeu.lamb.planificacion.entity.SesionClase;
import pe.edu.upeu.lamb.planificacion.entity.SilaboGrupo;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.SesionClaseMapper;
import pe.edu.upeu.lamb.planificacion.repository.SesionClaseRepository;
import pe.edu.upeu.lamb.planificacion.repository.SilaboGrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SesionClaseService {

    private final SesionClaseRepository sesionClaseRepository;
    private final SilaboGrupoRepository silaboGrupoRepository;
    private final SesionClaseMapper sesionClaseMapper;

    @Transactional(readOnly = true)
    public List<SesionClaseResponse> listar(Long idSilabo) {
        List<SesionClase> lista = idSilabo == null
                ? sesionClaseRepository.findAll()
                : sesionClaseRepository.findBySilaboId(idSilabo);
        return lista.stream().map(sesionClaseMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public SesionClaseResponse obtener(Long id) {
        return sesionClaseMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public SesionClaseResponse crear(SesionClaseRequest request) {
        validar(request, null);
        SesionClase sesionClase = sesionClaseMapper.toEntity(request);
        asignarRelaciones(sesionClase, request);
        return sesionClaseMapper.toResponse(sesionClaseRepository.save(sesionClase));
    }

    @Transactional
    public SesionClaseResponse actualizar(Long id, SesionClaseRequest request) {
        SesionClase sesionClase = buscarOFallar(id);
        validar(request, id);
        sesionClase.setNumero(request.getNumero());
        sesionClase.setSemana(request.getSemana());
        sesionClase.setTema(request.getTema().trim());
        sesionClase.setIdUnidadAprendizaje(request.getIdUnidadAprendizaje());
        sesionClase.setActividades(request.getActividades());
        asignarRelaciones(sesionClase, request);
        return sesionClaseMapper.toResponse(sesionClaseRepository.save(sesionClase));
    }

    @Transactional
    public void eliminar(Long id) {
        sesionClaseRepository.delete(buscarOFallar(id));
    }

    SesionClase buscarOFallar(Long id) {
        return sesionClaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sesion de clase no encontrada: " + id));
    }

    private void validar(SesionClaseRequest request, Long id) {
        boolean duplicado = id == null
                ? sesionClaseRepository.existsBySilaboIdAndNumero(request.getIdSilabo(), request.getNumero())
                : sesionClaseRepository.existsBySilaboIdAndNumeroAndIdNot(request.getIdSilabo(), request.getNumero(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de sesion de clase para esa combinacion de: silabo, numero");
        }
    }

    private void asignarRelaciones(SesionClase sesionClase, SesionClaseRequest request) {
        sesionClase.setSilabo(silaboGrupoRepository.findById(request.getIdSilabo())
                .orElseThrow(() -> new ResourceNotFoundException("Silabo de grupo no encontrado: " + request.getIdSilabo())));
    }
}
