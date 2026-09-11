package pe.edu.upeu.lamb.matricula.service;

import pe.edu.upeu.lamb.matricula.dto.MatriculaRequest;
import pe.edu.upeu.lamb.matricula.dto.MatriculaResponse;
import pe.edu.upeu.lamb.matricula.entity.EstadoMatricula;
import pe.edu.upeu.lamb.matricula.entity.Matricula;
import pe.edu.upeu.lamb.matricula.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.matricula.mapper.MatriculaMapper;
import pe.edu.upeu.lamb.matricula.repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final MatriculaMapper matriculaMapper;

    public List<MatriculaResponse> listar() {
        return matriculaRepository.findAll().stream()
                .map(matriculaMapper::toResponse)
                .toList();
    }

    public MatriculaResponse obtener(Long id) {
        return matriculaMapper.toResponse(buscarOFallar(id));
    }

    public MatriculaResponse crear(MatriculaRequest request) {
        Matricula matricula = matriculaMapper.toEntity(request);
        return matriculaMapper.toResponse(matriculaRepository.save(matricula));
    }

    public MatriculaResponse confirmar(Long id) {
        Matricula matricula = buscarOFallar(id);
        matricula.setEstado(EstadoMatricula.CONFIRMADA);
        return matriculaMapper.toResponse(matriculaRepository.save(matricula));
    }

    public MatriculaResponse anular(Long id) {
        Matricula matricula = buscarOFallar(id);
        matricula.setEstado(EstadoMatricula.ANULADA);
        return matriculaMapper.toResponse(matriculaRepository.save(matricula));
    }

    private Matricula buscarOFallar(Long id) {
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matricula no encontrada: " + id));
    }
}
