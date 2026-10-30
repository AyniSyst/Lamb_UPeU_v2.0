package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.CampusRequest;
import pe.edu.upeu.lamb.institucional.dto.CampusResponse;
import pe.edu.upeu.lamb.institucional.entity.Campus;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.CampusMapper;
import pe.edu.upeu.lamb.institucional.repository.CampusRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampusService {

    private final CampusRepository campusRepository;
    private final CampusMapper campusMapper;

    public List<CampusResponse> listar() {
        return campusRepository.findAll().stream()
                .map(campusMapper::toResponse)
                .toList();
    }

    public CampusResponse obtener(Long id) {
        return campusMapper.toResponse(buscarOFallar(id));
    }

    public CampusResponse crear(CampusRequest request) {
        Campus entity = campusMapper.toEntity(request);
        return campusMapper.toResponse(campusRepository.save(entity));
    }

    private Campus buscarOFallar(Long id) {
        return campusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campus no encontrado: " + id));
    }
}
