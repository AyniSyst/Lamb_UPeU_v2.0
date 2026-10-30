package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.ConvenioInstitucionalRequest;
import pe.edu.upeu.lamb.institucional.dto.ConvenioInstitucionalResponse;
import pe.edu.upeu.lamb.institucional.entity.ConvenioInstitucional;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.ConvenioInstitucionalMapper;
import pe.edu.upeu.lamb.institucional.repository.ConvenioInstitucionalRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConvenioInstitucionalService {

    private final ConvenioInstitucionalRepository convenioInstitucionalRepository;
    private final ConvenioInstitucionalMapper convenioInstitucionalMapper;

    public List<ConvenioInstitucionalResponse> listar() {
        return convenioInstitucionalRepository.findAll().stream()
                .map(convenioInstitucionalMapper::toResponse)
                .toList();
    }

    public ConvenioInstitucionalResponse obtener(Long id) {
        return convenioInstitucionalMapper.toResponse(buscarOFallar(id));
    }

    public ConvenioInstitucionalResponse crear(ConvenioInstitucionalRequest request) {
        ConvenioInstitucional entity = convenioInstitucionalMapper.toEntity(request);
        return convenioInstitucionalMapper.toResponse(convenioInstitucionalRepository.save(entity));
    }

    private ConvenioInstitucional buscarOFallar(Long id) {
        return convenioInstitucionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convenio institucional no encontrado: " + id));
    }
}
