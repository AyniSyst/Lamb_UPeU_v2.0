package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.AsistenciaRequest;
import pe.edu.upeu.lms.dto.AsistenciaResponse;
import pe.edu.upeu.lms.entity.Asistencia;
import pe.edu.upeu.lms.entity.SesionClase;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.AsistenciaMapper;
import pe.edu.upeu.lms.repository.AsistenciaRepository;
import pe.edu.upeu.lms.repository.SesionClaseRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;
    private final AsistenciaMapper asistenciaMapper;
    private final SesionClaseRepository sesionClaseRepository;

    public List<AsistenciaResponse> listar() {
        return asistenciaRepository.findAllConSesionClase().stream()
                .map(asistenciaMapper::toResponse)
                .toList();
    }

    public AsistenciaResponse obtener(Long id) {
        return asistenciaMapper.toResponse(buscarOFallar(id));
    }

    public AsistenciaResponse crear(AsistenciaRequest request) {
        Asistencia asistencia = asistenciaMapper.toEntity(request);
        asistencia.setSesionClase(buscarSesionClaseOFallar(request.getSesionClaseId()));
        asistencia.setFechaRegistro(LocalDateTime.now());
        return asistenciaMapper.toResponse(asistenciaRepository.save(asistencia));
    }

    public AsistenciaResponse actualizar(Long id, AsistenciaRequest request) {
        Asistencia asistencia = buscarOFallar(id);
        asistencia.setIdEstudiante(request.getIdEstudiante());
        asistencia.setPresente(request.getPresente());
        asistencia.setSesionClase(buscarSesionClaseOFallar(request.getSesionClaseId()));
        return asistenciaMapper.toResponse(asistenciaRepository.save(asistencia));
    }

    public void eliminar(Long id) {
        asistenciaRepository.delete(buscarOFallar(id));
    }

    private Asistencia buscarOFallar(Long id) {
        return asistenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia no encontrada: " + id));
    }

    private SesionClase buscarSesionClaseOFallar(Long sesionClaseId) {
        return sesionClaseRepository.findById(sesionClaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Sesion de clase no encontrada: " + sesionClaseId));
    }
}
