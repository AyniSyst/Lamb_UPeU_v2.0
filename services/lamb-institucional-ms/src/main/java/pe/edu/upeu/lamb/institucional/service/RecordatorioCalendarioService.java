package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.RecordatorioCalendarioRequest;
import pe.edu.upeu.lamb.institucional.dto.RecordatorioCalendarioResponse;
import pe.edu.upeu.lamb.institucional.entity.RecordatorioCalendario;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.RecordatorioCalendarioMapper;
import pe.edu.upeu.lamb.institucional.repository.RecordatorioCalendarioRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordatorioCalendarioService {

    private final RecordatorioCalendarioRepository recordatorioCalendarioRepository;
    private final RecordatorioCalendarioMapper recordatorioCalendarioMapper;

    public List<RecordatorioCalendarioResponse> listar() {
        return recordatorioCalendarioRepository.findAll().stream()
                .map(recordatorioCalendarioMapper::toResponse)
                .toList();
    }

    public RecordatorioCalendarioResponse obtener(Long id) {
        return recordatorioCalendarioMapper.toResponse(buscarOFallar(id));
    }

    public RecordatorioCalendarioResponse crear(RecordatorioCalendarioRequest request) {
        RecordatorioCalendario entity = recordatorioCalendarioMapper.toEntity(request);
        return recordatorioCalendarioMapper.toResponse(recordatorioCalendarioRepository.save(entity));
    }

    public RecordatorioCalendarioResponse actualizar(Long id, RecordatorioCalendarioRequest request) {
        RecordatorioCalendario existente = buscarOFallar(id);
        RecordatorioCalendario entity = recordatorioCalendarioMapper.toEntity(request);
        return recordatorioCalendarioMapper.toResponse(recordatorioCalendarioRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        recordatorioCalendarioRepository.delete(buscarOFallar(id));
    }

    private RecordatorioCalendario buscarOFallar(Long id) {
        return recordatorioCalendarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RecordatorioCalendario no encontrado con id " + id));
    }
}
