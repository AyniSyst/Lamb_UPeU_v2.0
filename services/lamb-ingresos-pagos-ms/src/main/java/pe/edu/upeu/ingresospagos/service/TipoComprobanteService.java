package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.TipoComprobanteRequest;
import pe.edu.upeu.ingresospagos.dto.TipoComprobanteResponse;
import pe.edu.upeu.ingresospagos.entity.TipoComprobante;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.TipoComprobanteMapper;
import pe.edu.upeu.ingresospagos.repository.TipoComprobanteRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TipoComprobanteService {

    private final TipoComprobanteRepository tipoComprobanteRepository;
    private final TipoComprobanteMapper tipoComprobanteMapper;

    public List<TipoComprobanteResponse> findAll() {
        return tipoComprobanteRepository.findAll().stream()
                .map(tipoComprobanteMapper::toResponse)
                .toList();
    }

    public TipoComprobanteResponse findById(Long id) {
        return tipoComprobanteMapper.toResponse(obtenerPorId(id));
    }

    public TipoComprobanteResponse create(TipoComprobanteRequest request) {
        TipoComprobante tipoComprobante = tipoComprobanteMapper.toEntity(request);
        return tipoComprobanteMapper.toResponse(tipoComprobanteRepository.save(tipoComprobante));
    }

    public TipoComprobanteResponse update(Long id, TipoComprobanteRequest request) {
        TipoComprobante tipoComprobante = obtenerPorId(id);
        tipoComprobante.setCodigo(request.getCodigo());
        tipoComprobante.setNombre(request.getNombre());
        tipoComprobante.setActivo(request.getActivo());
        return tipoComprobanteMapper.toResponse(tipoComprobanteRepository.save(tipoComprobante));
    }

    public void delete(Long id) {
        tipoComprobanteRepository.delete(obtenerPorId(id));
    }

    private TipoComprobante obtenerPorId(Long id) {
        return tipoComprobanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de comprobante no encontrado con id: " + id));
    }
}
