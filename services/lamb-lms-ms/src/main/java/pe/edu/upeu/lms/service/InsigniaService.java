package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.InsigniaRequest;
import pe.edu.upeu.lms.dto.InsigniaResponse;
import pe.edu.upeu.lms.entity.Insignia;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.InsigniaMapper;
import pe.edu.upeu.lms.repository.InsigniaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsigniaService {

    private final InsigniaRepository insigniaRepository;
    private final InsigniaMapper insigniaMapper;

    public List<InsigniaResponse> listar() {
        return insigniaRepository.findAll().stream()
                .map(insigniaMapper::toResponse)
                .toList();
    }

    public InsigniaResponse obtener(Long id) {
        return insigniaMapper.toResponse(buscarOFallar(id));
    }

    public InsigniaResponse crear(InsigniaRequest request) {
        Insignia insignia = insigniaMapper.toEntity(request);
        return insigniaMapper.toResponse(insigniaRepository.save(insignia));
    }

    public InsigniaResponse actualizar(Long id, InsigniaRequest request) {
        Insignia insignia = buscarOFallar(id);
        insignia.setNombre(request.getNombre());
        insignia.setDescripcion(request.getDescripcion());
        insignia.setCriterio(request.getCriterio());
        insignia.setActivo(request.getActivo());
        return insigniaMapper.toResponse(insigniaRepository.save(insignia));
    }

    public void eliminar(Long id) {
        insigniaRepository.delete(buscarOFallar(id));
    }

    private Insignia buscarOFallar(Long id) {
        return insigniaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insignia no encontrada: " + id));
    }
}
