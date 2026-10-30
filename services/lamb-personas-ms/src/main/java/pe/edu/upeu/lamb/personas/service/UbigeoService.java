package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.UbigeoRequest;
import pe.edu.upeu.lamb.personas.dto.UbigeoResponse;
import pe.edu.upeu.lamb.personas.entity.Ubigeo;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.UbigeoMapper;
import pe.edu.upeu.lamb.personas.repository.UbigeoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UbigeoService {

    private final UbigeoRepository ubigeoRepository;
    private final UbigeoMapper ubigeoMapper;

    public List<UbigeoResponse> listar() {
        return ubigeoRepository.findAll().stream()
                .map(ubigeoMapper::toResponse)
                .toList();
    }

    public UbigeoResponse obtener(Long id) {
        return ubigeoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public UbigeoResponse crear(UbigeoRequest request) {
        Ubigeo entity = ubigeoMapper.toEntity(request);
        return ubigeoMapper.toResponse(ubigeoRepository.save(entity));
    }

    public UbigeoResponse actualizar(Long id, UbigeoRequest request) {
        Ubigeo existente = buscarOFallar(id);
        Ubigeo entity = ubigeoMapper.toEntity(request);
        return ubigeoMapper.toResponse(ubigeoRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        ubigeoRepository.delete(buscarOFallar(id));
    }

    private Ubigeo buscarOFallar(Long id) {
        return ubigeoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ubigeo no encontrado: " + id));
    }
}
