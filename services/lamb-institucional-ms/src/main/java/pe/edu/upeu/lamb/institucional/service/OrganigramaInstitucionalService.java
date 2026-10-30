package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.OrganigramaInstitucionalRequest;
import pe.edu.upeu.lamb.institucional.dto.OrganigramaInstitucionalResponse;
import pe.edu.upeu.lamb.institucional.entity.OrganigramaInstitucional;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.OrganigramaInstitucionalMapper;
import pe.edu.upeu.lamb.institucional.repository.OrganigramaInstitucionalRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganigramaInstitucionalService {

    private final OrganigramaInstitucionalRepository organigramaInstitucionalRepository;
    private final OrganigramaInstitucionalMapper organigramaInstitucionalMapper;

    public List<OrganigramaInstitucionalResponse> listar() {
        return organigramaInstitucionalRepository.findAll().stream()
                .map(organigramaInstitucionalMapper::toResponse)
                .toList();
    }

    public OrganigramaInstitucionalResponse obtener(Long id) {
        return organigramaInstitucionalMapper.toResponse(buscarOFallar(id));
    }

    public OrganigramaInstitucionalResponse crear(OrganigramaInstitucionalRequest request) {
        OrganigramaInstitucional entity = organigramaInstitucionalMapper.toEntity(request);
        return organigramaInstitucionalMapper.toResponse(organigramaInstitucionalRepository.save(entity));
    }

    private OrganigramaInstitucional buscarOFallar(Long id) {
        return organigramaInstitucionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organigrama institucional no encontrado: " + id));
    }
}
