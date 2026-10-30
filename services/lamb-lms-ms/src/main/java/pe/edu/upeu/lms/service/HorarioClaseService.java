package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.HorarioClaseRequest;
import pe.edu.upeu.lms.dto.HorarioClaseResponse;
import pe.edu.upeu.lms.entity.HorarioClase;
import pe.edu.upeu.lms.entity.SeccionCurso;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.HorarioClaseMapper;
import pe.edu.upeu.lms.repository.HorarioClaseRepository;
import pe.edu.upeu.lms.repository.SeccionCursoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioClaseService {

    private final HorarioClaseRepository horarioClaseRepository;
    private final HorarioClaseMapper horarioClaseMapper;
    private final SeccionCursoRepository seccionCursoRepository;

    public List<HorarioClaseResponse> listar() {
        return horarioClaseRepository.findAllConSeccionCurso().stream()
                .map(horarioClaseMapper::toResponse)
                .toList();
    }

    public HorarioClaseResponse obtener(Long id) {
        return horarioClaseMapper.toResponse(buscarOFallar(id));
    }

    public HorarioClaseResponse crear(HorarioClaseRequest request) {
        HorarioClase horarioClase = horarioClaseMapper.toEntity(request);
        horarioClase.setSeccionCurso(buscarSeccionCursoOFallar(request.getSeccionCursoId()));
        return horarioClaseMapper.toResponse(horarioClaseRepository.save(horarioClase));
    }

    public HorarioClaseResponse actualizar(Long id, HorarioClaseRequest request) {
        HorarioClase horarioClase = buscarOFallar(id);
        horarioClase.setDiaSemana(request.getDiaSemana());
        horarioClase.setHoraInicio(request.getHoraInicio());
        horarioClase.setHoraFin(request.getHoraFin());
        horarioClase.setSeccionCurso(buscarSeccionCursoOFallar(request.getSeccionCursoId()));
        return horarioClaseMapper.toResponse(horarioClaseRepository.save(horarioClase));
    }

    public void eliminar(Long id) {
        horarioClaseRepository.delete(buscarOFallar(id));
    }

    private HorarioClase buscarOFallar(Long id) {
        return horarioClaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horario de clase no encontrado: " + id));
    }

    private SeccionCurso buscarSeccionCursoOFallar(Long seccionCursoId) {
        return seccionCursoRepository.findById(seccionCursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Seccion de curso no encontrada: " + seccionCursoId));
    }
}
