package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.DatoPersonalRequest;
import pe.edu.upeu.lamb.personas.dto.DatoPersonalResponse;
import pe.edu.upeu.lamb.personas.entity.DatoPersonal;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.DatoPersonalMapper;
import pe.edu.upeu.lamb.personas.repository.DatoPersonalRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DatoPersonalService {

    private final DatoPersonalRepository datoPersonalRepository;
    private final DatoPersonalMapper datoPersonalMapper;

    public List<DatoPersonalResponse> listar() {
        return datoPersonalRepository.findAll().stream()
                .map(datoPersonalMapper::toResponse)
                .toList();
    }

    public DatoPersonalResponse obtener(Long id) {
        return datoPersonalMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public DatoPersonalResponse crear(DatoPersonalRequest request) {
        DatoPersonal entity = datoPersonalMapper.toEntity(request);
        return datoPersonalMapper.toResponse(datoPersonalRepository.save(entity));
    }

    public DatoPersonalResponse actualizar(Long id, DatoPersonalRequest request) {
        DatoPersonal existente = buscarOFallar(id);
        DatoPersonal entity = datoPersonalMapper.toEntity(request);
        return datoPersonalMapper.toResponse(datoPersonalRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        datoPersonalRepository.delete(buscarOFallar(id));
    }

    private DatoPersonal buscarOFallar(Long id) {
        return datoPersonalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dato personal no encontrado: " + id));
    }
}
