package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.SerieComprobanteRequest;
import pe.edu.upeu.ingresospagos.dto.SerieComprobanteResponse;
import pe.edu.upeu.ingresospagos.entity.SerieComprobante;
import pe.edu.upeu.ingresospagos.entity.TipoComprobante;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.SerieComprobanteMapper;
import pe.edu.upeu.ingresospagos.repository.SerieComprobanteRepository;
import pe.edu.upeu.ingresospagos.repository.TipoComprobanteRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SerieComprobanteService {

    private final SerieComprobanteRepository serieComprobanteRepository;
    private final SerieComprobanteMapper serieComprobanteMapper;
    private final TipoComprobanteRepository tipoComprobanteRepository;

    public List<SerieComprobanteResponse> listar() {
        return serieComprobanteRepository.findAllConTipoComprobante().stream()
                .map(serieComprobanteMapper::toResponse)
                .toList();
    }

    public SerieComprobanteResponse obtener(Long id) {
        return serieComprobanteMapper.toResponse(buscarOFallar(id));
    }

    public SerieComprobanteResponse crear(SerieComprobanteRequest request) {
        SerieComprobante serieComprobante = serieComprobanteMapper.toEntity(request);
        serieComprobante.setTipoComprobante(buscarTipoComprobanteOFallar(request.getTipoComprobanteId()));
        return serieComprobanteMapper.toResponse(serieComprobanteRepository.save(serieComprobante));
    }

    public SerieComprobanteResponse actualizar(Long id, SerieComprobanteRequest request) {
        SerieComprobante serieComprobante = buscarOFallar(id);
        serieComprobante.setSerie(request.getSerie());
        serieComprobante.setCorrelativoActual(request.getCorrelativoActual());
        serieComprobante.setActivo(request.getActivo());
        serieComprobante.setTipoComprobante(buscarTipoComprobanteOFallar(request.getTipoComprobanteId()));
        return serieComprobanteMapper.toResponse(serieComprobanteRepository.save(serieComprobante));
    }

    public void eliminar(Long id) {
        serieComprobanteRepository.delete(buscarOFallar(id));
    }

    private SerieComprobante buscarOFallar(Long id) {
        return serieComprobanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serie de comprobante no encontrada: " + id));
    }

    private TipoComprobante buscarTipoComprobanteOFallar(Long tipoComprobanteId) {
        return tipoComprobanteRepository.findById(tipoComprobanteId)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de comprobante no encontrado: " + tipoComprobanteId));
    }
}
