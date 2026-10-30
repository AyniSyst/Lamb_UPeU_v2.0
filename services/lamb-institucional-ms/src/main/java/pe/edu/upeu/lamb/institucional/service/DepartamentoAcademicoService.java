package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.DepartamentoAcademicoRequest;
import pe.edu.upeu.lamb.institucional.dto.DepartamentoAcademicoResponse;
import pe.edu.upeu.lamb.institucional.entity.DepartamentoAcademico;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.DepartamentoAcademicoMapper;
import pe.edu.upeu.lamb.institucional.repository.DepartamentoAcademicoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartamentoAcademicoService {

    private final DepartamentoAcademicoRepository departamentoAcademicoRepository;
    private final DepartamentoAcademicoMapper departamentoAcademicoMapper;

    public List<DepartamentoAcademicoResponse> listar() {
        return departamentoAcademicoRepository.findAll().stream()
                .map(departamentoAcademicoMapper::toResponse)
                .toList();
    }

    public DepartamentoAcademicoResponse obtener(Long id) {
        return departamentoAcademicoMapper.toResponse(buscarOFallar(id));
    }

    public DepartamentoAcademicoResponse crear(DepartamentoAcademicoRequest request) {
        DepartamentoAcademico entity = departamentoAcademicoMapper.toEntity(request);
        return departamentoAcademicoMapper.toResponse(departamentoAcademicoRepository.save(entity));
    }

    private DepartamentoAcademico buscarOFallar(Long id) {
        return departamentoAcademicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento academico no encontrado: " + id));
    }
}
