package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.CambioUnidadAcademicaRequest;
import pe.edu.upeu.lamb.institucional.dto.CambioUnidadAcademicaResponse;
import pe.edu.upeu.lamb.institucional.entity.CambioUnidadAcademica;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.CambioUnidadAcademicaMapper;
import pe.edu.upeu.lamb.institucional.repository.CambioUnidadAcademicaRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CambioUnidadAcademicaService {

    private final CambioUnidadAcademicaRepository cambioUnidadAcademicaRepository;
    private final CambioUnidadAcademicaMapper cambioUnidadAcademicaMapper;

    public List<CambioUnidadAcademicaResponse> listar() {
        return cambioUnidadAcademicaRepository.findAll().stream()
                .map(cambioUnidadAcademicaMapper::toResponse)
                .toList();
    }

    public CambioUnidadAcademicaResponse obtener(Long id) {
        return cambioUnidadAcademicaMapper.toResponse(buscarOFallar(id));
    }

    public CambioUnidadAcademicaResponse crear(CambioUnidadAcademicaRequest request) {
        CambioUnidadAcademica entity = cambioUnidadAcademicaMapper.toEntity(request);
        return cambioUnidadAcademicaMapper.toResponse(cambioUnidadAcademicaRepository.save(entity));
    }

    public CambioUnidadAcademicaResponse actualizar(Long id, CambioUnidadAcademicaRequest request) {
        CambioUnidadAcademica existente = buscarOFallar(id);
        CambioUnidadAcademica entity = cambioUnidadAcademicaMapper.toEntity(request);
        return cambioUnidadAcademicaMapper.toResponse(cambioUnidadAcademicaRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        cambioUnidadAcademicaRepository.delete(buscarOFallar(id));
    }

    private CambioUnidadAcademica buscarOFallar(Long id) {
        return cambioUnidadAcademicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cambio de unidad academica no encontrado: " + id));
    }
}
