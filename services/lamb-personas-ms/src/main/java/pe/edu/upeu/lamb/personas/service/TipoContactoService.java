package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.TipoContactoRequest;
import pe.edu.upeu.lamb.personas.dto.TipoContactoResponse;
import pe.edu.upeu.lamb.personas.entity.TipoContacto;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.TipoContactoMapper;
import pe.edu.upeu.lamb.personas.repository.TipoContactoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoContactoService {

    private final TipoContactoRepository tipoContactoRepository;
    private final TipoContactoMapper tipoContactoMapper;

    public List<TipoContactoResponse> listar() {
        return tipoContactoRepository.findAll().stream()
                .map(tipoContactoMapper::toResponse)
                .toList();
    }

    public TipoContactoResponse obtener(Long id) {
        return tipoContactoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public TipoContactoResponse crear(TipoContactoRequest request) {
        TipoContacto entity = tipoContactoMapper.toEntity(request);
        return tipoContactoMapper.toResponse(tipoContactoRepository.save(entity));
    }

    private TipoContacto buscarOFallar(Long id) {
        return tipoContactoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de contacto no encontrado: " + id));
    }
}
