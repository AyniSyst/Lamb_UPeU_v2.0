package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.DatoSocioeconomicoRequest;
import pe.edu.upeu.lamb.personas.dto.DatoSocioeconomicoResponse;
import pe.edu.upeu.lamb.personas.entity.DatoSocioeconomico;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.DatoSocioeconomicoMapper;
import pe.edu.upeu.lamb.personas.repository.DatoSocioeconomicoRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatoSocioeconomicoService {

    private final DatoSocioeconomicoRepository datoSocioeconomicoRepository;
    private final DatoSocioeconomicoMapper datoSocioeconomicoMapper;

    public List<DatoSocioeconomicoResponse> listar() {
        return datoSocioeconomicoRepository.findAll().stream()
                .map(datoSocioeconomicoMapper::toResponse)
                .toList();
    }

    public DatoSocioeconomicoResponse obtener(Long id) {
        return datoSocioeconomicoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public DatoSocioeconomicoResponse crear(DatoSocioeconomicoRequest request) {
        DatoSocioeconomico entity = datoSocioeconomicoMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return datoSocioeconomicoMapper.toResponse(datoSocioeconomicoRepository.save(entity));
    }

    public DatoSocioeconomicoResponse actualizar(Long id, DatoSocioeconomicoRequest request) {
        DatoSocioeconomico existente = buscarOFallar(id);
        DatoSocioeconomico entity = datoSocioeconomicoMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return datoSocioeconomicoMapper.toResponse(datoSocioeconomicoRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        datoSocioeconomicoRepository.delete(buscarOFallar(id));
    }

    private DatoSocioeconomico buscarOFallar(Long id) {
        return datoSocioeconomicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dato socioeconomico no encontrado: " + id));
    }
}
