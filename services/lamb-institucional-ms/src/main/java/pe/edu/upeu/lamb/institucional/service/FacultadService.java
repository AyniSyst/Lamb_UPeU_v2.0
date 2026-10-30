package pe.edu.upeu.lamb.institucional.service;

import pe.edu.upeu.lamb.institucional.dto.FacultadRequest;
import pe.edu.upeu.lamb.institucional.dto.FacultadResponse;
import pe.edu.upeu.lamb.institucional.entity.Facultad;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.FacultadMapper;
import pe.edu.upeu.lamb.institucional.repository.FacultadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FacultadService {

    private final FacultadRepository facultadRepository;
    private final FacultadMapper facultadMapper;

    public List<FacultadResponse> listar() {
        return facultadRepository.findAll().stream()
                .map(facultadMapper::toResponse)
                .toList();
    }

    public FacultadResponse obtener(Long id) {
        return facultadMapper.toResponse(buscarOFallar(id));
    }

    public FacultadResponse crear(FacultadRequest request) {
        Facultad facultad = facultadMapper.toEntity(request);
        return facultadMapper.toResponse(facultadRepository.save(facultad));
    }

    public FacultadResponse actualizar(Long id, FacultadRequest request) {
        Facultad facultad = buscarOFallar(id);
        facultad.setNombre(request.getNombre());
        facultad.setDecano(request.getDecano());
        return facultadMapper.toResponse(facultadRepository.save(facultad));
    }

    public void eliminar(Long id) {
        facultadRepository.delete(buscarOFallar(id));
    }

    Facultad buscarOFallar(Long id) {
        return facultadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facultad no encontrada: " + id));
    }
}
