package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.TipoInstitucionRequest;
import pe.edu.upeu.lamb.institucional.dto.TipoInstitucionResponse;
import pe.edu.upeu.lamb.institucional.entity.TipoInstitucion;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.TipoInstitucionMapper;
import pe.edu.upeu.lamb.institucional.repository.TipoInstitucionRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TipoInstitucionService {

    private final TipoInstitucionRepository tipoInstitucionRepository;
    private final TipoInstitucionMapper tipoInstitucionMapper;

    public List<TipoInstitucionResponse> listar() {
        return tipoInstitucionRepository.findAll().stream()
                .map(tipoInstitucionMapper::toResponse)
                .toList();
    }

    public TipoInstitucionResponse obtener(Long id) {
        return tipoInstitucionMapper.toResponse(buscarOFallar(id));
    }

    public TipoInstitucionResponse crear(TipoInstitucionRequest request) {
        TipoInstitucion entity = tipoInstitucionMapper.toEntity(request);
        return tipoInstitucionMapper.toResponse(tipoInstitucionRepository.save(entity));
    }

    public TipoInstitucionResponse actualizar(Long id, TipoInstitucionRequest request) {
        TipoInstitucion existente = buscarOFallar(id);
        TipoInstitucion entity = tipoInstitucionMapper.toEntity(request);
        return tipoInstitucionMapper.toResponse(tipoInstitucionRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        tipoInstitucionRepository.delete(buscarOFallar(id));
    }

    private TipoInstitucion buscarOFallar(Long id) {
        return tipoInstitucionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de institucion no encontrado: " + id));
    }
}
