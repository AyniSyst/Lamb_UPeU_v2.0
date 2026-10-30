package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.LenguaIdiomaRequest;
import pe.edu.upeu.lamb.personas.dto.LenguaIdiomaResponse;
import pe.edu.upeu.lamb.personas.entity.LenguaIdioma;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.LenguaIdiomaMapper;
import pe.edu.upeu.lamb.personas.repository.LenguaIdiomaRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LenguaIdiomaService {

    private final LenguaIdiomaRepository lenguaIdiomaRepository;
    private final LenguaIdiomaMapper lenguaIdiomaMapper;

    public List<LenguaIdiomaResponse> listar() {
        return lenguaIdiomaRepository.findAll().stream()
                .map(lenguaIdiomaMapper::toResponse)
                .toList();
    }

    public LenguaIdiomaResponse obtener(Long id) {
        return lenguaIdiomaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public LenguaIdiomaResponse crear(LenguaIdiomaRequest request) {
        LenguaIdioma entity = lenguaIdiomaMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return lenguaIdiomaMapper.toResponse(lenguaIdiomaRepository.save(entity));
    }

    public LenguaIdiomaResponse actualizar(Long id, LenguaIdiomaRequest request) {
        LenguaIdioma existente = buscarOFallar(id);
        LenguaIdioma entity = lenguaIdiomaMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return lenguaIdiomaMapper.toResponse(lenguaIdiomaRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        lenguaIdiomaRepository.delete(buscarOFallar(id));
    }

    private LenguaIdioma buscarOFallar(Long id) {
        return lenguaIdiomaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lengua/idioma no encontrada: " + id));
    }
}
