package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.PeriodoAcademicoRequest;
import pe.edu.upeu.lamb.institucional.dto.PeriodoAcademicoResponse;
import pe.edu.upeu.lamb.institucional.entity.PeriodoAcademico;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.PeriodoAcademicoMapper;
import pe.edu.upeu.lamb.institucional.repository.PeriodoAcademicoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeriodoAcademicoService {

    private final PeriodoAcademicoRepository periodoAcademicoRepository;
    private final PeriodoAcademicoMapper periodoAcademicoMapper;

    public List<PeriodoAcademicoResponse> listar() {
        return periodoAcademicoRepository.findAll().stream()
                .map(periodoAcademicoMapper::toResponse)
                .toList();
    }

    public PeriodoAcademicoResponse obtener(Long id) {
        return periodoAcademicoMapper.toResponse(buscarOFallar(id));
    }

    public PeriodoAcademicoResponse crear(PeriodoAcademicoRequest request) {
        PeriodoAcademico entity = periodoAcademicoMapper.toEntity(request);
        return periodoAcademicoMapper.toResponse(periodoAcademicoRepository.save(entity));
    }

    PeriodoAcademico buscarOFallar(Long id) {
        return periodoAcademicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PeriodoAcademico no encontrado con id: " + id));
    }
}
