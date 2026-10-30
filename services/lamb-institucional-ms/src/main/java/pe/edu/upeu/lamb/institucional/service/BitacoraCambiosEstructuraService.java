package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.BitacoraCambiosEstructuraRequest;
import pe.edu.upeu.lamb.institucional.dto.BitacoraCambiosEstructuraResponse;
import pe.edu.upeu.lamb.institucional.entity.BitacoraCambiosEstructura;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.BitacoraCambiosEstructuraMapper;
import pe.edu.upeu.lamb.institucional.repository.BitacoraCambiosEstructuraRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BitacoraCambiosEstructuraService {

    private final BitacoraCambiosEstructuraRepository bitacoraCambiosEstructuraRepository;
    private final BitacoraCambiosEstructuraMapper bitacoraCambiosEstructuraMapper;

    public List<BitacoraCambiosEstructuraResponse> listar() {
        return bitacoraCambiosEstructuraRepository.findAll().stream()
                .map(bitacoraCambiosEstructuraMapper::toResponse)
                .toList();
    }

    public BitacoraCambiosEstructuraResponse obtener(Long id) {
        return bitacoraCambiosEstructuraMapper.toResponse(buscarOFallar(id));
    }

    public BitacoraCambiosEstructuraResponse crear(BitacoraCambiosEstructuraRequest request) {
        BitacoraCambiosEstructura entity = bitacoraCambiosEstructuraMapper.toEntity(request);
        return bitacoraCambiosEstructuraMapper.toResponse(bitacoraCambiosEstructuraRepository.save(entity));
    }

    public BitacoraCambiosEstructuraResponse actualizar(Long id, BitacoraCambiosEstructuraRequest request) {
        BitacoraCambiosEstructura existente = buscarOFallar(id);
        BitacoraCambiosEstructura entity = bitacoraCambiosEstructuraMapper.toEntity(request);
        return bitacoraCambiosEstructuraMapper.toResponse(bitacoraCambiosEstructuraRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        bitacoraCambiosEstructuraRepository.delete(buscarOFallar(id));
    }

    private BitacoraCambiosEstructura buscarOFallar(Long id) {
        return bitacoraCambiosEstructuraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BitacoraCambiosEstructura no encontrado con id " + id));
    }
}
