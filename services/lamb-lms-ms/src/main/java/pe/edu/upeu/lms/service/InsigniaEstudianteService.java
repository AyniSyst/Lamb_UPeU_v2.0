package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.InsigniaEstudianteRequest;
import pe.edu.upeu.lms.dto.InsigniaEstudianteResponse;
import pe.edu.upeu.lms.entity.Insignia;
import pe.edu.upeu.lms.entity.InsigniaEstudiante;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.InsigniaEstudianteMapper;
import pe.edu.upeu.lms.repository.InsigniaEstudianteRepository;
import pe.edu.upeu.lms.repository.InsigniaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InsigniaEstudianteService {

    private final InsigniaEstudianteRepository insigniaEstudianteRepository;
    private final InsigniaEstudianteMapper insigniaEstudianteMapper;
    private final InsigniaRepository insigniaRepository;

    public List<InsigniaEstudianteResponse> listar() {
        return insigniaEstudianteRepository.findAllConInsignia().stream()
                .map(insigniaEstudianteMapper::toResponse)
                .toList();
    }

    public InsigniaEstudianteResponse obtener(Long id) {
        return insigniaEstudianteMapper.toResponse(buscarOFallar(id));
    }

    public InsigniaEstudianteResponse crear(InsigniaEstudianteRequest request) {
        InsigniaEstudiante insigniaEstudiante = insigniaEstudianteMapper.toEntity(request);
        insigniaEstudiante.setInsignia(buscarInsigniaOFallar(request.getInsigniaId()));
        insigniaEstudiante.setFechaOtorgamiento(LocalDateTime.now());
        return insigniaEstudianteMapper.toResponse(insigniaEstudianteRepository.save(insigniaEstudiante));
    }

    public InsigniaEstudianteResponse actualizar(Long id, InsigniaEstudianteRequest request) {
        InsigniaEstudiante insigniaEstudiante = buscarOFallar(id);
        insigniaEstudiante.setIdEstudiante(request.getIdEstudiante());
        insigniaEstudiante.setInsignia(buscarInsigniaOFallar(request.getInsigniaId()));
        return insigniaEstudianteMapper.toResponse(insigniaEstudianteRepository.save(insigniaEstudiante));
    }

    public void eliminar(Long id) {
        insigniaEstudianteRepository.delete(buscarOFallar(id));
    }

    private InsigniaEstudiante buscarOFallar(Long id) {
        return insigniaEstudianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insignia de estudiante no encontrada: " + id));
    }

    private Insignia buscarInsigniaOFallar(Long insigniaId) {
        return insigniaRepository.findById(insigniaId)
                .orElseThrow(() -> new ResourceNotFoundException("Insignia no encontrada: " + insigniaId));
    }
}
