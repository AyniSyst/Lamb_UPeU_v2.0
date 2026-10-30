package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.PreguntaBancoRequest;
import pe.edu.upeu.lms.dto.PreguntaBancoResponse;
import pe.edu.upeu.lms.entity.PreguntaBanco;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.PreguntaBancoMapper;
import pe.edu.upeu.lms.repository.PreguntaBancoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreguntaBancoService {

    private final PreguntaBancoRepository preguntaBancoRepository;
    private final PreguntaBancoMapper preguntaBancoMapper;

    public List<PreguntaBancoResponse> listar() {
        return preguntaBancoRepository.findAll().stream()
                .map(preguntaBancoMapper::toResponse)
                .toList();
    }

    public PreguntaBancoResponse obtener(Long id) {
        return preguntaBancoMapper.toResponse(buscarOFallar(id));
    }

    public PreguntaBancoResponse crear(PreguntaBancoRequest request) {
        PreguntaBanco preguntaBanco = preguntaBancoMapper.toEntity(request);
        return preguntaBancoMapper.toResponse(preguntaBancoRepository.save(preguntaBanco));
    }

    public PreguntaBancoResponse actualizar(Long id, PreguntaBancoRequest request) {
        PreguntaBanco preguntaBanco = buscarOFallar(id);
        preguntaBanco.setEnunciado(request.getEnunciado());
        preguntaBanco.setTipo(request.getTipo());
        preguntaBanco.setPuntajeSugerido(request.getPuntajeSugerido());
        preguntaBanco.setCategoria(request.getCategoria());
        preguntaBanco.setActivo(request.getActivo());
        return preguntaBancoMapper.toResponse(preguntaBancoRepository.save(preguntaBanco));
    }

    public void eliminar(Long id) {
        preguntaBancoRepository.delete(buscarOFallar(id));
    }

    private PreguntaBanco buscarOFallar(Long id) {
        return preguntaBancoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta de banco no encontrada: " + id));
    }
}
