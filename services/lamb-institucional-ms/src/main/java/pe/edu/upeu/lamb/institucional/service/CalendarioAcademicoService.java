package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.CalendarioAcademicoRequest;
import pe.edu.upeu.lamb.institucional.dto.CalendarioAcademicoResponse;
import pe.edu.upeu.lamb.institucional.entity.CalendarioAcademico;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.CalendarioAcademicoMapper;
import pe.edu.upeu.lamb.institucional.repository.CalendarioAcademicoRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CalendarioAcademicoService {

    private final CalendarioAcademicoRepository calendarioAcademicoRepository;
    private final CalendarioAcademicoMapper calendarioAcademicoMapper;

    public List<CalendarioAcademicoResponse> listar() {
        return calendarioAcademicoRepository.findAll().stream()
                .map(calendarioAcademicoMapper::toResponse)
                .toList();
    }

    public CalendarioAcademicoResponse obtener(Long id) {
        return calendarioAcademicoMapper.toResponse(buscarOFallar(id));
    }

    public CalendarioAcademicoResponse crear(CalendarioAcademicoRequest request) {
        CalendarioAcademico entity = calendarioAcademicoMapper.toEntity(request);
        return calendarioAcademicoMapper.toResponse(calendarioAcademicoRepository.save(entity));
    }

    public CalendarioAcademicoResponse actualizar(Long id, CalendarioAcademicoRequest request) {
        CalendarioAcademico existente = buscarOFallar(id);
        CalendarioAcademico entity = calendarioAcademicoMapper.toEntity(request);
        return calendarioAcademicoMapper.toResponse(calendarioAcademicoRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        calendarioAcademicoRepository.delete(buscarOFallar(id));
    }

    private CalendarioAcademico buscarOFallar(Long id) {
        return calendarioAcademicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CalendarioAcademico no encontrado con id " + id));
    }
}
