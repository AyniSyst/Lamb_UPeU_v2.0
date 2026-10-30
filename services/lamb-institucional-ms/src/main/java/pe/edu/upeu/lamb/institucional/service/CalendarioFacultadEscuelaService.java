package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.CalendarioFacultadEscuelaRequest;
import pe.edu.upeu.lamb.institucional.dto.CalendarioFacultadEscuelaResponse;
import pe.edu.upeu.lamb.institucional.entity.CalendarioFacultadEscuela;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.CalendarioFacultadEscuelaMapper;
import pe.edu.upeu.lamb.institucional.repository.CalendarioFacultadEscuelaRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CalendarioFacultadEscuelaService {

    private final CalendarioFacultadEscuelaRepository calendarioFacultadEscuelaRepository;
    private final CalendarioFacultadEscuelaMapper calendarioFacultadEscuelaMapper;

    public List<CalendarioFacultadEscuelaResponse> listar() {
        return calendarioFacultadEscuelaRepository.findAll().stream()
                .map(calendarioFacultadEscuelaMapper::toResponse)
                .toList();
    }

    public CalendarioFacultadEscuelaResponse obtener(Long id) {
        return calendarioFacultadEscuelaMapper.toResponse(buscarOFallar(id));
    }

    public CalendarioFacultadEscuelaResponse crear(CalendarioFacultadEscuelaRequest request) {
        CalendarioFacultadEscuela entity = calendarioFacultadEscuelaMapper.toEntity(request);
        return calendarioFacultadEscuelaMapper.toResponse(calendarioFacultadEscuelaRepository.save(entity));
    }

    public CalendarioFacultadEscuelaResponse actualizar(Long id, CalendarioFacultadEscuelaRequest request) {
        CalendarioFacultadEscuela existente = buscarOFallar(id);
        CalendarioFacultadEscuela entity = calendarioFacultadEscuelaMapper.toEntity(request);
        return calendarioFacultadEscuelaMapper.toResponse(calendarioFacultadEscuelaRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        calendarioFacultadEscuelaRepository.delete(buscarOFallar(id));
    }

    private CalendarioFacultadEscuela buscarOFallar(Long id) {
        return calendarioFacultadEscuelaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CalendarioFacultadEscuela no encontrado con id " + id));
    }
}
