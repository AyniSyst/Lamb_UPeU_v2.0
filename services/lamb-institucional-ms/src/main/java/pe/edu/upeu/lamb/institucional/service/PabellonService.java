package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.PabellonRequest;
import pe.edu.upeu.lamb.institucional.dto.PabellonResponse;
import pe.edu.upeu.lamb.institucional.entity.Pabellon;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.PabellonMapper;
import pe.edu.upeu.lamb.institucional.repository.PabellonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PabellonService {

    private final PabellonRepository pabellonRepository;
    private final PabellonMapper pabellonMapper;

    public List<PabellonResponse> listar() {
        return pabellonRepository.findAll().stream()
                .map(pabellonMapper::toResponse)
                .toList();
    }

    public PabellonResponse obtener(Long id) {
        return pabellonMapper.toResponse(buscarOFallar(id));
    }

    public PabellonResponse crear(PabellonRequest request) {
        Pabellon entity = pabellonMapper.toEntity(request);
        return pabellonMapper.toResponse(pabellonRepository.save(entity));
    }

    private Pabellon buscarOFallar(Long id) {
        return pabellonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pabellon no encontrado: " + id));
    }
}
