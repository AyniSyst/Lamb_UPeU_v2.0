package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.TipoDireccionRequest;
import pe.edu.upeu.lamb.personas.dto.TipoDireccionResponse;
import pe.edu.upeu.lamb.personas.entity.TipoDireccion;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.TipoDireccionMapper;
import pe.edu.upeu.lamb.personas.repository.TipoDireccionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoDireccionService {

    private final TipoDireccionRepository tipoDireccionRepository;
    private final TipoDireccionMapper tipoDireccionMapper;

    public List<TipoDireccionResponse> listar() {
        return tipoDireccionRepository.findAll().stream()
                .map(tipoDireccionMapper::toResponse)
                .toList();
    }

    public TipoDireccionResponse obtener(Long id) {
        return tipoDireccionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public TipoDireccionResponse crear(TipoDireccionRequest request) {
        TipoDireccion entity = tipoDireccionMapper.toEntity(request);
        return tipoDireccionMapper.toResponse(tipoDireccionRepository.save(entity));
    }

    private TipoDireccion buscarOFallar(Long id) {
        return tipoDireccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de direccion no encontrado: " + id));
    }
}
