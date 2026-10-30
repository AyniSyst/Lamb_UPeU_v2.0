package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.RedSocialRequest;
import pe.edu.upeu.lamb.personas.dto.RedSocialResponse;
import pe.edu.upeu.lamb.personas.entity.RedSocial;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.RedSocialMapper;
import pe.edu.upeu.lamb.personas.repository.RedSocialRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RedSocialService {

    private final RedSocialRepository redSocialRepository;
    private final RedSocialMapper redSocialMapper;

    public List<RedSocialResponse> listar() {
        return redSocialRepository.findAll().stream()
                .map(redSocialMapper::toResponse)
                .toList();
    }

    public RedSocialResponse obtener(Long id) {
        return redSocialMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public RedSocialResponse crear(RedSocialRequest request) {
        RedSocial entity = redSocialMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return redSocialMapper.toResponse(redSocialRepository.save(entity));
    }

    public RedSocialResponse actualizar(Long id, RedSocialRequest request) {
        RedSocial existente = buscarOFallar(id);
        RedSocial entity = redSocialMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return redSocialMapper.toResponse(redSocialRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        redSocialRepository.delete(buscarOFallar(id));
    }

    private RedSocial buscarOFallar(Long id) {
        return redSocialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Red social no encontrada: " + id));
    }
}
