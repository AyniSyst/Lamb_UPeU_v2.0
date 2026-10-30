package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.MetodoPagoRequest;
import pe.edu.upeu.ingresospagos.dto.MetodoPagoResponse;
import pe.edu.upeu.ingresospagos.entity.MetodoPago;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.MetodoPagoMapper;
import pe.edu.upeu.ingresospagos.repository.MetodoPagoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetodoPagoService {

    private final MetodoPagoRepository metodoPagoRepository;
    private final MetodoPagoMapper metodoPagoMapper;

    public List<MetodoPagoResponse> findAll() {
        return metodoPagoRepository.findAll().stream()
                .map(metodoPagoMapper::toResponse)
                .toList();
    }

    public MetodoPagoResponse findById(Long id) {
        return metodoPagoMapper.toResponse(obtenerPorId(id));
    }

    public MetodoPagoResponse create(MetodoPagoRequest request) {
        MetodoPago metodoPago = metodoPagoMapper.toEntity(request);
        return metodoPagoMapper.toResponse(metodoPagoRepository.save(metodoPago));
    }

    public MetodoPagoResponse update(Long id, MetodoPagoRequest request) {
        MetodoPago metodoPago = obtenerPorId(id);
        metodoPago.setNombre(request.getNombre());
        metodoPago.setActivo(request.getActivo());
        return metodoPagoMapper.toResponse(metodoPagoRepository.save(metodoPago));
    }

    public void delete(Long id) {
        metodoPagoRepository.delete(obtenerPorId(id));
    }

    private MetodoPago obtenerPorId(Long id) {
        return metodoPagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Metodo de pago no encontrado con id: " + id));
    }
}
