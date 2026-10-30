package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.ModalidadCursoRequest;
import pe.edu.upeu.lms.dto.ModalidadCursoResponse;
import pe.edu.upeu.lms.entity.ModalidadCurso;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.ModalidadCursoMapper;
import pe.edu.upeu.lms.repository.ModalidadCursoRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ModalidadCursoService {

    private final ModalidadCursoRepository modalidadCursoRepository;
    private final ModalidadCursoMapper modalidadCursoMapper;

    public List<ModalidadCursoResponse> listar() {
        return modalidadCursoRepository.findAll().stream()
                .map(modalidadCursoMapper::toResponse)
                .toList();
    }

    public ModalidadCursoResponse obtener(Long id) {
        return modalidadCursoMapper.toResponse(buscarOFallar(id));
    }

    public ModalidadCursoResponse crear(ModalidadCursoRequest request) {
        ModalidadCurso modalidadCurso = modalidadCursoMapper.toEntity(request);
        return modalidadCursoMapper.toResponse(modalidadCursoRepository.save(modalidadCurso));
    }

    public ModalidadCursoResponse actualizar(Long id, ModalidadCursoRequest request) {
        ModalidadCurso modalidadCurso = buscarOFallar(id);
        modalidadCurso.setNombre(request.getNombre());
        modalidadCurso.setDescripcion(request.getDescripcion());
        modalidadCurso.setActivo(request.getActivo());
        return modalidadCursoMapper.toResponse(modalidadCursoRepository.save(modalidadCurso));
    }

    public void eliminar(Long id) {
        modalidadCursoRepository.delete(buscarOFallar(id));
    }

    private ModalidadCurso buscarOFallar(Long id) {
        return modalidadCursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Modalidad de curso no encontrada: " + id));
    }
}
