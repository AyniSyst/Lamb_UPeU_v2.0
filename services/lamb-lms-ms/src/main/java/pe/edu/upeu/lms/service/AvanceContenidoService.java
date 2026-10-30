package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.AvanceContenidoRequest;
import pe.edu.upeu.lms.dto.AvanceContenidoResponse;
import pe.edu.upeu.lms.entity.AvanceContenido;
import pe.edu.upeu.lms.entity.Contenido;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.AvanceContenidoMapper;
import pe.edu.upeu.lms.repository.AvanceContenidoRepository;
import pe.edu.upeu.lms.repository.ContenidoRepository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AvanceContenidoService {

    private final AvanceContenidoRepository avanceContenidoRepository;
    private final AvanceContenidoMapper avanceContenidoMapper;
    private final ContenidoRepository contenidoRepository;

    public List<AvanceContenidoResponse> listar() {
        return avanceContenidoRepository.findAllConContenido().stream()
                .map(avanceContenidoMapper::toResponse)
                .toList();
    }

    public AvanceContenidoResponse obtener(Long id) {
        return avanceContenidoMapper.toResponse(buscarOFallar(id));
    }

    public AvanceContenidoResponse crear(AvanceContenidoRequest request) {
        AvanceContenido avanceContenido = avanceContenidoMapper.toEntity(request);
        avanceContenido.setContenido(buscarContenidoOFallar(request.getContenidoId()));
        avanceContenido.setFechaActualizacion(LocalDateTime.now());
        return avanceContenidoMapper.toResponse(avanceContenidoRepository.save(avanceContenido));
    }

    public AvanceContenidoResponse actualizar(Long id, AvanceContenidoRequest request) {
        AvanceContenido avanceContenido = buscarOFallar(id);
        avanceContenido.setIdEstudiante(request.getIdEstudiante());
        avanceContenido.setConsumido(request.getConsumido());
        avanceContenido.setContenido(buscarContenidoOFallar(request.getContenidoId()));
        avanceContenido.setFechaActualizacion(LocalDateTime.now());
        return avanceContenidoMapper.toResponse(avanceContenidoRepository.save(avanceContenido));
    }

    public void eliminar(Long id) {
        avanceContenidoRepository.delete(buscarOFallar(id));
    }

    private AvanceContenido buscarOFallar(Long id) {
        return avanceContenidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avance de contenido no encontrado: " + id));
    }

    private Contenido buscarContenidoOFallar(Long contenidoId) {
        return contenidoRepository.findById(contenidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Contenido no encontrado: " + contenidoId));
    }
}
