package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.SexoRequest;
import pe.edu.upeu.lamb.personas.dto.SexoResponse;
import pe.edu.upeu.lamb.personas.entity.Sexo;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.SexoMapper;
import pe.edu.upeu.lamb.personas.repository.SexoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SexoService {

    private final SexoRepository sexoRepository;
    private final SexoMapper sexoMapper;

    public List<SexoResponse> listar() {
        return sexoRepository.findAll().stream()
                .map(sexoMapper::toResponse)
                .toList();
    }

    public SexoResponse obtener(Long id) {
        return sexoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public SexoResponse crear(SexoRequest request) {
        Sexo entity = sexoMapper.toEntity(request);
        return sexoMapper.toResponse(sexoRepository.save(entity));
    }

    public SexoResponse actualizar(Long id, SexoRequest request) {
        Sexo existente = buscarOFallar(id);
        Sexo entity = sexoMapper.toEntity(request);
        return sexoMapper.toResponse(sexoRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        sexoRepository.delete(buscarOFallar(id));
    }

    private Sexo buscarOFallar(Long id) {
        return sexoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sexo no encontrado: " + id));
    }
}
