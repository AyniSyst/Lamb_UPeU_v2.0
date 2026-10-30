package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.VerificacionContactoRequest;
import pe.edu.upeu.lamb.personas.dto.VerificacionContactoResponse;
import pe.edu.upeu.lamb.personas.entity.VerificacionContacto;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.VerificacionContactoMapper;
import pe.edu.upeu.lamb.personas.repository.VerificacionContactoRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VerificacionContactoService {

    private final VerificacionContactoRepository verificacionContactoRepository;
    private final VerificacionContactoMapper verificacionContactoMapper;

    public List<VerificacionContactoResponse> listar() {
        return verificacionContactoRepository.findAll().stream()
                .map(verificacionContactoMapper::toResponse)
                .toList();
    }

    public VerificacionContactoResponse obtener(Long id) {
        return verificacionContactoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public VerificacionContactoResponse crear(VerificacionContactoRequest request) {
        VerificacionContacto entity = verificacionContactoMapper.toEntity(request);
        entity.setFechaVerificacion(Instant.now());
        return verificacionContactoMapper.toResponse(verificacionContactoRepository.save(entity));
    }

    private VerificacionContacto buscarOFallar(Long id) {
        return verificacionContactoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Verificacion de contacto no encontrada: " + id));
    }
}
