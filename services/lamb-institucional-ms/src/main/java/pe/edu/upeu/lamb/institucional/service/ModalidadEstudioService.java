package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.ModalidadEstudioRequest;
import pe.edu.upeu.lamb.institucional.dto.ModalidadEstudioResponse;
import pe.edu.upeu.lamb.institucional.entity.ModalidadEstudio;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.ModalidadEstudioMapper;
import pe.edu.upeu.lamb.institucional.repository.ModalidadEstudioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModalidadEstudioService {

    private final ModalidadEstudioRepository modalidadEstudioRepository;
    private final ModalidadEstudioMapper modalidadEstudioMapper;

    public List<ModalidadEstudioResponse> listar() {
        return modalidadEstudioRepository.findAll().stream()
                .map(modalidadEstudioMapper::toResponse)
                .toList();
    }

    public ModalidadEstudioResponse obtener(Long id) {
        return modalidadEstudioMapper.toResponse(buscarOFallar(id));
    }

    public ModalidadEstudioResponse crear(ModalidadEstudioRequest request) {
        ModalidadEstudio entity = modalidadEstudioMapper.toEntity(request);
        return modalidadEstudioMapper.toResponse(modalidadEstudioRepository.save(entity));
    }

    private ModalidadEstudio buscarOFallar(Long id) {
        return modalidadEstudioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ModalidadEstudio no encontrada con id: " + id));
    }
}
