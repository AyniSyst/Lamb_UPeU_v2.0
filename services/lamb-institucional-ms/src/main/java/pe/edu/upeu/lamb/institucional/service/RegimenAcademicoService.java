package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.RegimenAcademicoRequest;
import pe.edu.upeu.lamb.institucional.dto.RegimenAcademicoResponse;
import pe.edu.upeu.lamb.institucional.entity.RegimenAcademico;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.RegimenAcademicoMapper;
import pe.edu.upeu.lamb.institucional.repository.RegimenAcademicoRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegimenAcademicoService {

    private final RegimenAcademicoRepository regimenAcademicoRepository;
    private final RegimenAcademicoMapper regimenAcademicoMapper;

    public List<RegimenAcademicoResponse> listar() {
        return regimenAcademicoRepository.findAll().stream()
                .map(regimenAcademicoMapper::toResponse)
                .toList();
    }

    public RegimenAcademicoResponse obtener(Long id) {
        return regimenAcademicoMapper.toResponse(buscarOFallar(id));
    }

    public RegimenAcademicoResponse crear(RegimenAcademicoRequest request) {
        RegimenAcademico entity = regimenAcademicoMapper.toEntity(request);
        return regimenAcademicoMapper.toResponse(regimenAcademicoRepository.save(entity));
    }

    public RegimenAcademicoResponse actualizar(Long id, RegimenAcademicoRequest request) {
        RegimenAcademico existente = buscarOFallar(id);
        RegimenAcademico entity = regimenAcademicoMapper.toEntity(request);
        return regimenAcademicoMapper.toResponse(regimenAcademicoRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        regimenAcademicoRepository.delete(buscarOFallar(id));
    }

    private RegimenAcademico buscarOFallar(Long id) {
        return regimenAcademicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RegimenAcademico no encontrado con id: " + id));
    }
}
