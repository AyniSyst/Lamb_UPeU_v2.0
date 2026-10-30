package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.EntregaTareaRequest;
import pe.edu.upeu.lms.dto.EntregaTareaResponse;
import pe.edu.upeu.lms.entity.EntregaTarea;
import pe.edu.upeu.lms.entity.Tarea;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.EntregaTareaMapper;
import pe.edu.upeu.lms.repository.EntregaTareaRepository;
import pe.edu.upeu.lms.repository.TareaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntregaTareaService {

    private final EntregaTareaRepository entregaTareaRepository;
    private final EntregaTareaMapper entregaTareaMapper;
    private final TareaRepository tareaRepository;

    public List<EntregaTareaResponse> listar() {
        return entregaTareaRepository.findAllConTarea().stream()
                .map(entregaTareaMapper::toResponse)
                .toList();
    }

    public EntregaTareaResponse obtener(Long id) {
        return entregaTareaMapper.toResponse(buscarOFallar(id));
    }

    public EntregaTareaResponse crear(EntregaTareaRequest request) {
        EntregaTarea entregaTarea = entregaTareaMapper.toEntity(request);
        entregaTarea.setTarea(buscarTareaOFallar(request.getTareaId()));
        entregaTarea.setFechaEntrega(LocalDateTime.now());
        return entregaTareaMapper.toResponse(entregaTareaRepository.save(entregaTarea));
    }

    public EntregaTareaResponse actualizar(Long id, EntregaTareaRequest request) {
        EntregaTarea entregaTarea = buscarOFallar(id);
        entregaTarea.setIdEstudiante(request.getIdEstudiante());
        entregaTarea.setUrlArchivo(request.getUrlArchivo());
        entregaTarea.setNota(request.getNota());
        entregaTarea.setTarea(buscarTareaOFallar(request.getTareaId()));
        return entregaTareaMapper.toResponse(entregaTareaRepository.save(entregaTarea));
    }

    public void eliminar(Long id) {
        entregaTareaRepository.delete(buscarOFallar(id));
    }

    private EntregaTarea buscarOFallar(Long id) {
        return entregaTareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entrega de tarea no encontrada: " + id));
    }

    private Tarea buscarTareaOFallar(Long tareaId) {
        return tareaRepository.findById(tareaId)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada: " + tareaId));
    }
}
