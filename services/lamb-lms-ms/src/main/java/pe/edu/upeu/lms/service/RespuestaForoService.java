package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.RespuestaForoRequest;
import pe.edu.upeu.lms.dto.RespuestaForoResponse;
import pe.edu.upeu.lms.entity.ForoHilo;
import pe.edu.upeu.lms.entity.RespuestaForo;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.RespuestaForoMapper;
import pe.edu.upeu.lms.repository.ForoHiloRepository;
import pe.edu.upeu.lms.repository.RespuestaForoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RespuestaForoService {

    private final RespuestaForoRepository respuestaForoRepository;
    private final RespuestaForoMapper respuestaForoMapper;
    private final ForoHiloRepository foroHiloRepository;

    public List<RespuestaForoResponse> listar() {
        return respuestaForoRepository.findAllConForoHilo().stream()
                .map(respuestaForoMapper::toResponse)
                .toList();
    }

    public RespuestaForoResponse obtener(Long id) {
        return respuestaForoMapper.toResponse(buscarOFallar(id));
    }

    public RespuestaForoResponse crear(RespuestaForoRequest request) {
        RespuestaForo respuestaForo = respuestaForoMapper.toEntity(request);
        respuestaForo.setForoHilo(buscarForoHiloOFallar(request.getForoHiloId()));
        respuestaForo.setFecha(LocalDateTime.now());
        return respuestaForoMapper.toResponse(respuestaForoRepository.save(respuestaForo));
    }

    public RespuestaForoResponse actualizar(Long id, RespuestaForoRequest request) {
        RespuestaForo respuestaForo = buscarOFallar(id);
        respuestaForo.setIdEstudiante(request.getIdEstudiante());
        respuestaForo.setMensaje(request.getMensaje());
        respuestaForo.setForoHilo(buscarForoHiloOFallar(request.getForoHiloId()));
        return respuestaForoMapper.toResponse(respuestaForoRepository.save(respuestaForo));
    }

    public void eliminar(Long id) {
        respuestaForoRepository.delete(buscarOFallar(id));
    }

    private RespuestaForo buscarOFallar(Long id) {
        return respuestaForoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Respuesta de foro no encontrada: " + id));
    }

    private ForoHilo buscarForoHiloOFallar(Long foroHiloId) {
        return foroHiloRepository.findById(foroHiloId)
                .orElseThrow(() -> new ResourceNotFoundException("Foro no encontrado: " + foroHiloId));
    }
}
