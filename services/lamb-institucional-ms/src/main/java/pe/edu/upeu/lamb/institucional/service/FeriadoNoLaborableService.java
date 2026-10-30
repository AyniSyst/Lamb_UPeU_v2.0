package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.FeriadoNoLaborableRequest;
import pe.edu.upeu.lamb.institucional.dto.FeriadoNoLaborableResponse;
import pe.edu.upeu.lamb.institucional.entity.FeriadoNoLaborable;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.FeriadoNoLaborableMapper;
import pe.edu.upeu.lamb.institucional.repository.FeriadoNoLaborableRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeriadoNoLaborableService {

    private final FeriadoNoLaborableRepository feriadoNoLaborableRepository;
    private final FeriadoNoLaborableMapper feriadoNoLaborableMapper;

    public List<FeriadoNoLaborableResponse> listar() {
        return feriadoNoLaborableRepository.findAll().stream()
                .map(feriadoNoLaborableMapper::toResponse)
                .toList();
    }

    public FeriadoNoLaborableResponse obtener(Long id) {
        return feriadoNoLaborableMapper.toResponse(buscarOFallar(id));
    }

    public FeriadoNoLaborableResponse crear(FeriadoNoLaborableRequest request) {
        FeriadoNoLaborable entity = feriadoNoLaborableMapper.toEntity(request);
        return feriadoNoLaborableMapper.toResponse(feriadoNoLaborableRepository.save(entity));
    }

    private FeriadoNoLaborable buscarOFallar(Long id) {
        return feriadoNoLaborableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FeriadoNoLaborable no encontrado con id " + id));
    }
}
