package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.ParametroInstitucionalRequest;
import pe.edu.upeu.lamb.institucional.dto.ParametroInstitucionalResponse;
import pe.edu.upeu.lamb.institucional.entity.ParametroInstitucional;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.ParametroInstitucionalMapper;
import pe.edu.upeu.lamb.institucional.repository.ParametroInstitucionalRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ParametroInstitucionalService {

    private final ParametroInstitucionalRepository parametroInstitucionalRepository;
    private final ParametroInstitucionalMapper parametroInstitucionalMapper;

    public List<ParametroInstitucionalResponse> listar() {
        return parametroInstitucionalRepository.findAll().stream()
                .map(parametroInstitucionalMapper::toResponse)
                .toList();
    }

    public ParametroInstitucionalResponse obtener(Long id) {
        return parametroInstitucionalMapper.toResponse(buscarOFallar(id));
    }

    public ParametroInstitucionalResponse crear(ParametroInstitucionalRequest request) {
        ParametroInstitucional entity = parametroInstitucionalMapper.toEntity(request);
        return parametroInstitucionalMapper.toResponse(parametroInstitucionalRepository.save(entity));
    }

    public ParametroInstitucionalResponse actualizar(Long id, ParametroInstitucionalRequest request) {
        ParametroInstitucional existente = buscarOFallar(id);
        ParametroInstitucional entity = parametroInstitucionalMapper.toEntity(request);
        return parametroInstitucionalMapper.toResponse(parametroInstitucionalRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        parametroInstitucionalRepository.delete(buscarOFallar(id));
    }

    private ParametroInstitucional buscarOFallar(Long id) {
        return parametroInstitucionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ParametroInstitucional no encontrado con id " + id));
    }
}
