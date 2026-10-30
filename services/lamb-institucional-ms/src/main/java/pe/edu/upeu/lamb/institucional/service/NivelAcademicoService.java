package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.NivelAcademicoRequest;
import pe.edu.upeu.lamb.institucional.dto.NivelAcademicoResponse;
import pe.edu.upeu.lamb.institucional.entity.NivelAcademico;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.NivelAcademicoMapper;
import pe.edu.upeu.lamb.institucional.repository.NivelAcademicoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NivelAcademicoService {

    private final NivelAcademicoRepository nivelAcademicoRepository;
    private final NivelAcademicoMapper nivelAcademicoMapper;

    public List<NivelAcademicoResponse> listar() {
        return nivelAcademicoRepository.findAll().stream()
                .map(nivelAcademicoMapper::toResponse)
                .toList();
    }

    public NivelAcademicoResponse obtener(Long id) {
        return nivelAcademicoMapper.toResponse(buscarOFallar(id));
    }

    public NivelAcademicoResponse crear(NivelAcademicoRequest request) {
        NivelAcademico entity = nivelAcademicoMapper.toEntity(request);
        return nivelAcademicoMapper.toResponse(nivelAcademicoRepository.save(entity));
    }

    private NivelAcademico buscarOFallar(Long id) {
        return nivelAcademicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NivelAcademico no encontrado con id: " + id));
    }
}
