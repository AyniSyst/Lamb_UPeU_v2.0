package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.EventoCalendarioRequest;
import pe.edu.upeu.lamb.institucional.dto.EventoCalendarioResponse;
import pe.edu.upeu.lamb.institucional.entity.EventoCalendario;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.EventoCalendarioMapper;
import pe.edu.upeu.lamb.institucional.repository.EventoCalendarioRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventoCalendarioService {

    private final EventoCalendarioRepository eventoCalendarioRepository;
    private final EventoCalendarioMapper eventoCalendarioMapper;

    public List<EventoCalendarioResponse> listar() {
        return eventoCalendarioRepository.findAll().stream()
                .map(eventoCalendarioMapper::toResponse)
                .toList();
    }

    public EventoCalendarioResponse obtener(Long id) {
        return eventoCalendarioMapper.toResponse(buscarOFallar(id));
    }

    public EventoCalendarioResponse crear(EventoCalendarioRequest request) {
        EventoCalendario entity = eventoCalendarioMapper.toEntity(request);
        return eventoCalendarioMapper.toResponse(eventoCalendarioRepository.save(entity));
    }

    public EventoCalendarioResponse actualizar(Long id, EventoCalendarioRequest request) {
        EventoCalendario existente = buscarOFallar(id);
        EventoCalendario entity = eventoCalendarioMapper.toEntity(request);
        return eventoCalendarioMapper.toResponse(eventoCalendarioRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        eventoCalendarioRepository.delete(buscarOFallar(id));
    }

    private EventoCalendario buscarOFallar(Long id) {
        return eventoCalendarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EventoCalendario no encontrado con id " + id));
    }
}
