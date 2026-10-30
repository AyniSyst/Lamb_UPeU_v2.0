package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.TituloProfesionalRequest;
import pe.edu.upeu.lamb.curriculo.dto.TituloProfesionalResponse;
import pe.edu.upeu.lamb.curriculo.entity.TituloProfesional;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.TituloProfesionalMapper;
import pe.edu.upeu.lamb.curriculo.repository.TituloProfesionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TituloProfesionalService {

    private final TituloProfesionalRepository tituloProfesionalRepository;
    private final TituloProfesionalMapper tituloProfesionalMapper;

    @Transactional(readOnly = true)
    public List<TituloProfesionalResponse> listar() {
        return tituloProfesionalRepository.findAll().stream()
                .map(tituloProfesionalMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public TituloProfesionalResponse obtener(Long id) {
        return tituloProfesionalMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public TituloProfesionalResponse crear(TituloProfesionalRequest request) {
        validar(request, null);
        TituloProfesional tituloProfesional = tituloProfesionalMapper.toEntity(request);
        return tituloProfesionalMapper.toResponse(tituloProfesionalRepository.save(tituloProfesional));
    }

    @Transactional
    public TituloProfesionalResponse actualizar(Long id, TituloProfesionalRequest request) {
        TituloProfesional tituloProfesional = buscarOFallar(id);
        validar(request, id);
        tituloProfesional.setIdPrograma(request.getIdPrograma());
        tituloProfesional.setDenominacion(request.getDenominacion().trim());
        tituloProfesional.setResolucion(request.getResolucion());
        return tituloProfesionalMapper.toResponse(tituloProfesionalRepository.save(tituloProfesional));
    }

    @Transactional
    public void eliminar(Long id) {
        tituloProfesionalRepository.delete(buscarOFallar(id));
    }

    TituloProfesional buscarOFallar(Long id) {
        return tituloProfesionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Titulo profesional no encontrado: " + id));
    }

    private void validar(TituloProfesionalRequest request, Long id) {
        boolean duplicado = id == null
                ? tituloProfesionalRepository.existsByIdPrograma(request.getIdPrograma())
                : tituloProfesionalRepository.existsByIdProgramaAndIdNot(request.getIdPrograma(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de titulo profesional para esa combinacion de: programa");
        }
    }
}
