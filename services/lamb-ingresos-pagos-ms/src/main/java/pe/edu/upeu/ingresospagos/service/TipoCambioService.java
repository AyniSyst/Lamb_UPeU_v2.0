package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.TipoCambioRequest;
import pe.edu.upeu.ingresospagos.dto.TipoCambioResponse;
import pe.edu.upeu.ingresospagos.entity.TipoCambio;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.TipoCambioMapper;
import pe.edu.upeu.ingresospagos.repository.TipoCambioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoCambioService {

    private final TipoCambioRepository tipoCambioRepository;
    private final TipoCambioMapper tipoCambioMapper;

    public List<TipoCambioResponse> findAll() {
        return tipoCambioRepository.findAll().stream()
                .map(tipoCambioMapper::toResponse)
                .toList();
    }

    public TipoCambioResponse findById(Long id) {
        return tipoCambioMapper.toResponse(obtenerPorId(id));
    }

    public TipoCambioResponse create(TipoCambioRequest request) {
        TipoCambio tipoCambio = tipoCambioMapper.toEntity(request);
        return tipoCambioMapper.toResponse(tipoCambioRepository.save(tipoCambio));
    }

    public TipoCambioResponse update(Long id, TipoCambioRequest request) {
        TipoCambio tipoCambio = obtenerPorId(id);
        tipoCambio.setFecha(request.getFecha());
        tipoCambio.setMonedaOrigen(request.getMonedaOrigen());
        tipoCambio.setMonedaDestino(request.getMonedaDestino());
        tipoCambio.setValor(request.getValor());
        return tipoCambioMapper.toResponse(tipoCambioRepository.save(tipoCambio));
    }

    public void delete(Long id) {
        tipoCambioRepository.delete(obtenerPorId(id));
    }

    private TipoCambio obtenerPorId(Long id) {
        return tipoCambioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de cambio no encontrado con id: " + id));
    }
}
