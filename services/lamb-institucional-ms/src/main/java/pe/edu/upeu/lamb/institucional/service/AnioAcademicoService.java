package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.AnioAcademicoRequest;
import pe.edu.upeu.lamb.institucional.dto.AnioAcademicoResponse;
import pe.edu.upeu.lamb.institucional.entity.AnioAcademico;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.AnioAcademicoMapper;
import pe.edu.upeu.lamb.institucional.repository.AnioAcademicoRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnioAcademicoService {

    private final AnioAcademicoRepository anioAcademicoRepository;
    private final AnioAcademicoMapper anioAcademicoMapper;

    public List<AnioAcademicoResponse> listar() {
        return anioAcademicoRepository.findAll().stream()
                .map(anioAcademicoMapper::toResponse)
                .toList();
    }

    public AnioAcademicoResponse obtener(Long id) {
        return anioAcademicoMapper.toResponse(buscarOFallar(id));
    }

    public AnioAcademicoResponse crear(AnioAcademicoRequest request) {
        AnioAcademico entity = anioAcademicoMapper.toEntity(request);
        return anioAcademicoMapper.toResponse(anioAcademicoRepository.save(entity));
    }

    public AnioAcademicoResponse actualizar(Long id, AnioAcademicoRequest request) {
        AnioAcademico existente = buscarOFallar(id);
        AnioAcademico entity = anioAcademicoMapper.toEntity(request);
        return anioAcademicoMapper.toResponse(anioAcademicoRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        anioAcademicoRepository.delete(buscarOFallar(id));
    }

    private AnioAcademico buscarOFallar(Long id) {
        return anioAcademicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AnioAcademico no encontrado con id: " + id));
    }
}
