package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.AmpliacionCierrePeriodoRequest;
import pe.edu.upeu.lamb.institucional.dto.AmpliacionCierrePeriodoResponse;
import pe.edu.upeu.lamb.institucional.entity.AmpliacionCierrePeriodo;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.AmpliacionCierrePeriodoMapper;
import pe.edu.upeu.lamb.institucional.repository.AmpliacionCierrePeriodoRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AmpliacionCierrePeriodoService {

    private final AmpliacionCierrePeriodoRepository ampliacionCierrePeriodoRepository;
    private final AmpliacionCierrePeriodoMapper ampliacionCierrePeriodoMapper;

    public List<AmpliacionCierrePeriodoResponse> listar() {
        return ampliacionCierrePeriodoRepository.findAll().stream()
                .map(ampliacionCierrePeriodoMapper::toResponse)
                .toList();
    }

    public AmpliacionCierrePeriodoResponse obtener(Long id) {
        return ampliacionCierrePeriodoMapper.toResponse(buscarOFallar(id));
    }

    public AmpliacionCierrePeriodoResponse crear(AmpliacionCierrePeriodoRequest request) {
        AmpliacionCierrePeriodo entity = ampliacionCierrePeriodoMapper.toEntity(request);
        return ampliacionCierrePeriodoMapper.toResponse(ampliacionCierrePeriodoRepository.save(entity));
    }

    public AmpliacionCierrePeriodoResponse actualizar(Long id, AmpliacionCierrePeriodoRequest request) {
        AmpliacionCierrePeriodo existente = buscarOFallar(id);
        AmpliacionCierrePeriodo entity = ampliacionCierrePeriodoMapper.toEntity(request);
        return ampliacionCierrePeriodoMapper.toResponse(ampliacionCierrePeriodoRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        ampliacionCierrePeriodoRepository.delete(buscarOFallar(id));
    }

    private AmpliacionCierrePeriodo buscarOFallar(Long id) {
        return ampliacionCierrePeriodoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AmpliacionCierrePeriodo no encontrado con id: " + id));
    }
}
