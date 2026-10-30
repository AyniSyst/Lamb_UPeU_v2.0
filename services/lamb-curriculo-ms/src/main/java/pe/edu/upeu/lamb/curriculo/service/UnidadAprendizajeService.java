package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.UnidadAprendizajeRequest;
import pe.edu.upeu.lamb.curriculo.dto.UnidadAprendizajeResponse;
import pe.edu.upeu.lamb.curriculo.entity.SilaboBase;
import pe.edu.upeu.lamb.curriculo.entity.UnidadAprendizaje;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.UnidadAprendizajeMapper;
import pe.edu.upeu.lamb.curriculo.repository.SilaboBaseRepository;
import pe.edu.upeu.lamb.curriculo.repository.UnidadAprendizajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnidadAprendizajeService {

    private final UnidadAprendizajeRepository unidadAprendizajeRepository;
    private final SilaboBaseRepository silaboBaseRepository;
    private final UnidadAprendizajeMapper unidadAprendizajeMapper;

    @Transactional(readOnly = true)
    public List<UnidadAprendizajeResponse> listar(Long idSilabo) {
        List<UnidadAprendizaje> lista = idSilabo == null
                ? unidadAprendizajeRepository.findAll()
                : unidadAprendizajeRepository.findBySilaboId(idSilabo);
        return lista.stream().map(unidadAprendizajeMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public UnidadAprendizajeResponse obtener(Long id) {
        return unidadAprendizajeMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public UnidadAprendizajeResponse crear(UnidadAprendizajeRequest request) {
        validar(request, null);
        UnidadAprendizaje unidadAprendizaje = unidadAprendizajeMapper.toEntity(request);
        asignarRelaciones(unidadAprendizaje, request);
        return unidadAprendizajeMapper.toResponse(unidadAprendizajeRepository.save(unidadAprendizaje));
    }

    @Transactional
    public UnidadAprendizajeResponse actualizar(Long id, UnidadAprendizajeRequest request) {
        UnidadAprendizaje unidadAprendizaje = buscarOFallar(id);
        validar(request, id);
        unidadAprendizaje.setNumero(request.getNumero());
        unidadAprendizaje.setNombre(request.getNombre().trim());
        unidadAprendizaje.setSemanaInicio(request.getSemanaInicio());
        unidadAprendizaje.setSemanaFin(request.getSemanaFin());
        unidadAprendizaje.setContenidos(request.getContenidos());
        asignarRelaciones(unidadAprendizaje, request);
        return unidadAprendizajeMapper.toResponse(unidadAprendizajeRepository.save(unidadAprendizaje));
    }

    @Transactional
    public void eliminar(Long id) {
        unidadAprendizajeRepository.delete(buscarOFallar(id));
    }

    UnidadAprendizaje buscarOFallar(Long id) {
        return unidadAprendizajeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unidad de aprendizaje no encontrada: " + id));
    }

    private void validar(UnidadAprendizajeRequest request, Long id) {
        if (request.getSemanaFin() < request.getSemanaInicio()) {
            throw new BusinessRuleException("Semana fin no puede ser menor que semana inicio");
        }
        boolean duplicado = id == null
                ? unidadAprendizajeRepository.existsBySilaboIdAndNumero(request.getIdSilabo(), request.getNumero())
                : unidadAprendizajeRepository.existsBySilaboIdAndNumeroAndIdNot(request.getIdSilabo(), request.getNumero(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de unidad de aprendizaje para esa combinacion de: silabo, numero");
        }
    }

    private void asignarRelaciones(UnidadAprendizaje unidadAprendizaje, UnidadAprendizajeRequest request) {
        unidadAprendizaje.setSilabo(silaboBaseRepository.findById(request.getIdSilabo())
                .orElseThrow(() -> new ResourceNotFoundException("Silabo base del curso no encontrado: " + request.getIdSilabo())));
    }
}
