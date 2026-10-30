package pe.edu.upeu.lamb.matricula.service;

import pe.edu.upeu.lamb.matricula.dto.AmpliacionCupoRequest;
import pe.edu.upeu.lamb.matricula.dto.AmpliacionCupoResponse;
import pe.edu.upeu.lamb.matricula.entity.AmpliacionCupo;
import pe.edu.upeu.lamb.matricula.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.matricula.mapper.AmpliacionCupoMapper;
import pe.edu.upeu.lamb.matricula.repository.AmpliacionCupoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AmpliacionCupoService {

    private final AmpliacionCupoRepository ampliacionCupoRepository;
    private final AmpliacionCupoMapper ampliacionCupoMapper;

    public List<AmpliacionCupoResponse> listar() {
        return ampliacionCupoRepository.findAll().stream()
                .map(ampliacionCupoMapper::toResponse)
                .toList();
    }

    public AmpliacionCupoResponse obtener(Long id) {
        return ampliacionCupoMapper.toResponse(buscarOFallar(id));
    }

    public AmpliacionCupoResponse crear(AmpliacionCupoRequest request) {
        AmpliacionCupo entity = ampliacionCupoMapper.toEntity(request);
        return ampliacionCupoMapper.toResponse(ampliacionCupoRepository.save(entity));
    }

    public AmpliacionCupoResponse actualizar(Long id, AmpliacionCupoRequest request) {
        AmpliacionCupo existente = buscarOFallar(id);
        AmpliacionCupo entity = ampliacionCupoMapper.toEntity(request);
        return ampliacionCupoMapper.toResponse(ampliacionCupoRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        ampliacionCupoRepository.delete(buscarOFallar(id));
    }

    private AmpliacionCupo buscarOFallar(Long id) {
        return ampliacionCupoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ampliacion de cupo no encontrada: " + id));
    }
}
