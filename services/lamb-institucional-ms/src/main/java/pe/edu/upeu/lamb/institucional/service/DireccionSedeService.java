package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.DireccionSedeRequest;
import pe.edu.upeu.lamb.institucional.dto.DireccionSedeResponse;
import pe.edu.upeu.lamb.institucional.entity.DireccionSede;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.DireccionSedeMapper;
import pe.edu.upeu.lamb.institucional.repository.DireccionSedeRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DireccionSedeService {

    private final DireccionSedeRepository direccionSedeRepository;
    private final DireccionSedeMapper direccionSedeMapper;

    public List<DireccionSedeResponse> listar() {
        return direccionSedeRepository.findAll().stream()
                .map(direccionSedeMapper::toResponse)
                .toList();
    }

    public DireccionSedeResponse obtener(Long id) {
        return direccionSedeMapper.toResponse(buscarOFallar(id));
    }

    public DireccionSedeResponse crear(DireccionSedeRequest request) {
        DireccionSede entity = direccionSedeMapper.toEntity(request);
        return direccionSedeMapper.toResponse(direccionSedeRepository.save(entity));
    }

    public DireccionSedeResponse actualizar(Long id, DireccionSedeRequest request) {
        DireccionSede existente = buscarOFallar(id);
        DireccionSede entity = direccionSedeMapper.toEntity(request);
        return direccionSedeMapper.toResponse(direccionSedeRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        direccionSedeRepository.delete(buscarOFallar(id));
    }

    private DireccionSede buscarOFallar(Long id) {
        return direccionSedeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Direccion de sede no encontrada: " + id));
    }
}
