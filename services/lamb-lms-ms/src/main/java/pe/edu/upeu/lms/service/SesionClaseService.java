package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.SesionClaseRequest;
import pe.edu.upeu.lms.dto.SesionClaseResponse;
import pe.edu.upeu.lms.entity.SeccionCurso;
import pe.edu.upeu.lms.entity.SesionClase;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.SesionClaseMapper;
import pe.edu.upeu.lms.repository.SeccionCursoRepository;
import pe.edu.upeu.lms.repository.SesionClaseRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SesionClaseService {

    private final SesionClaseRepository sesionClaseRepository;
    private final SesionClaseMapper sesionClaseMapper;
    private final SeccionCursoRepository seccionCursoRepository;

    public List<SesionClaseResponse> listar() {
        return sesionClaseRepository.findAllConSeccionCurso().stream()
                .map(sesionClaseMapper::toResponse)
                .toList();
    }

    public SesionClaseResponse obtener(Long id) {
        return sesionClaseMapper.toResponse(buscarOFallar(id));
    }

    public SesionClaseResponse crear(SesionClaseRequest request) {
        SesionClase sesionClase = sesionClaseMapper.toEntity(request);
        sesionClase.setSeccionCurso(buscarSeccionCursoOFallar(request.getSeccionCursoId()));
        return sesionClaseMapper.toResponse(sesionClaseRepository.save(sesionClase));
    }

    public SesionClaseResponse actualizar(Long id, SesionClaseRequest request) {
        SesionClase sesionClase = buscarOFallar(id);
        sesionClase.setFecha(request.getFecha());
        sesionClase.setHoraInicio(request.getHoraInicio());
        sesionClase.setHoraFin(request.getHoraFin());
        sesionClase.setEnlaceVirtual(request.getEnlaceVirtual());
        sesionClase.setActivo(request.getActivo());
        sesionClase.setSeccionCurso(buscarSeccionCursoOFallar(request.getSeccionCursoId()));
        return sesionClaseMapper.toResponse(sesionClaseRepository.save(sesionClase));
    }

    public void eliminar(Long id) {
        sesionClaseRepository.delete(buscarOFallar(id));
    }

    private SesionClase buscarOFallar(Long id) {
        return sesionClaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sesion de clase no encontrada: " + id));
    }

    private SeccionCurso buscarSeccionCursoOFallar(Long seccionCursoId) {
        return seccionCursoRepository.findById(seccionCursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Seccion de curso no encontrada: " + seccionCursoId));
    }
}
