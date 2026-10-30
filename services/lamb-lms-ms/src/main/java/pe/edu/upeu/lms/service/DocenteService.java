package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.DocenteRequest;
import pe.edu.upeu.lms.dto.DocenteResponse;
import pe.edu.upeu.lms.entity.Docente;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.DocenteMapper;
import pe.edu.upeu.lms.repository.DocenteRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DocenteService {

    private final DocenteRepository docenteRepository;
    private final DocenteMapper docenteMapper;

    public List<DocenteResponse> listar() {
        return docenteRepository.findAll().stream()
                .map(docenteMapper::toResponse)
                .toList();
    }

    public DocenteResponse obtener(Long id) {
        return docenteMapper.toResponse(buscarOFallar(id));
    }

    public DocenteResponse crear(DocenteRequest request) {
        Docente docente = docenteMapper.toEntity(request);
        return docenteMapper.toResponse(docenteRepository.save(docente));
    }

    public DocenteResponse actualizar(Long id, DocenteRequest request) {
        Docente docente = buscarOFallar(id);
        docente.setNombres(request.getNombres());
        docente.setApellidos(request.getApellidos());
        docente.setEmail(request.getEmail());
        docente.setEspecialidad(request.getEspecialidad());
        docente.setActivo(request.getActivo());
        return docenteMapper.toResponse(docenteRepository.save(docente));
    }

    public void eliminar(Long id) {
        docenteRepository.delete(buscarOFallar(id));
    }

    private Docente buscarOFallar(Long id) {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado: " + id));
    }
}
