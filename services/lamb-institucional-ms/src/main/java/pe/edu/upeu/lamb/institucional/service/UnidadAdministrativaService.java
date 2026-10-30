package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.UnidadAdministrativaRequest;
import pe.edu.upeu.lamb.institucional.dto.UnidadAdministrativaResponse;
import pe.edu.upeu.lamb.institucional.entity.UnidadAdministrativa;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.UnidadAdministrativaMapper;
import pe.edu.upeu.lamb.institucional.repository.UnidadAdministrativaRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UnidadAdministrativaService {

    private final UnidadAdministrativaRepository unidadAdministrativaRepository;
    private final UnidadAdministrativaMapper unidadAdministrativaMapper;

    public List<UnidadAdministrativaResponse> listar() {
        return unidadAdministrativaRepository.findAll().stream()
                .map(unidadAdministrativaMapper::toResponse)
                .toList();
    }

    public UnidadAdministrativaResponse obtener(Long id) {
        return unidadAdministrativaMapper.toResponse(buscarOFallar(id));
    }

    public UnidadAdministrativaResponse crear(UnidadAdministrativaRequest request) {
        UnidadAdministrativa entity = unidadAdministrativaMapper.toEntity(request);
        return unidadAdministrativaMapper.toResponse(unidadAdministrativaRepository.save(entity));
    }

    public UnidadAdministrativaResponse actualizar(Long id, UnidadAdministrativaRequest request) {
        UnidadAdministrativa existente = buscarOFallar(id);
        UnidadAdministrativa entity = unidadAdministrativaMapper.toEntity(request);
        return unidadAdministrativaMapper.toResponse(unidadAdministrativaRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        unidadAdministrativaRepository.delete(buscarOFallar(id));
    }

    private UnidadAdministrativa buscarOFallar(Long id) {
        return unidadAdministrativaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unidad administrativa no encontrada: " + id));
    }
}
