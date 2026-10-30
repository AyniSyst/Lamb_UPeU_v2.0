package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.EntidadFinancieraRequest;
import pe.edu.upeu.ingresospagos.dto.EntidadFinancieraResponse;
import pe.edu.upeu.ingresospagos.entity.EntidadFinanciera;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.EntidadFinancieraMapper;
import pe.edu.upeu.ingresospagos.repository.EntidadFinancieraRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntidadFinancieraService {

    private final EntidadFinancieraRepository entidadFinancieraRepository;
    private final EntidadFinancieraMapper entidadFinancieraMapper;

    public List<EntidadFinancieraResponse> findAll() {
        return entidadFinancieraRepository.findAll().stream()
                .map(entidadFinancieraMapper::toResponse)
                .toList();
    }

    public EntidadFinancieraResponse findById(Long id) {
        return entidadFinancieraMapper.toResponse(obtenerPorId(id));
    }

    public EntidadFinancieraResponse create(EntidadFinancieraRequest request) {
        EntidadFinanciera entidadFinanciera = entidadFinancieraMapper.toEntity(request);
        return entidadFinancieraMapper.toResponse(entidadFinancieraRepository.save(entidadFinanciera));
    }

    public EntidadFinancieraResponse update(Long id, EntidadFinancieraRequest request) {
        EntidadFinanciera entidadFinanciera = obtenerPorId(id);
        entidadFinanciera.setNombre(request.getNombre());
        entidadFinanciera.setCodigo(request.getCodigo());
        entidadFinanciera.setActivo(request.getActivo());
        return entidadFinancieraMapper.toResponse(entidadFinancieraRepository.save(entidadFinanciera));
    }

    public void delete(Long id) {
        entidadFinancieraRepository.delete(obtenerPorId(id));
    }

    private EntidadFinanciera obtenerPorId(Long id) {
        return entidadFinancieraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entidad financiera no encontrada con id: " + id));
    }
}
