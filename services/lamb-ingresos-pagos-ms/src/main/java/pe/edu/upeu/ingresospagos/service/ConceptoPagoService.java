package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.ConceptoPagoRequest;
import pe.edu.upeu.ingresospagos.dto.ConceptoPagoResponse;
import pe.edu.upeu.ingresospagos.entity.ConceptoPago;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.ConceptoPagoMapper;
import pe.edu.upeu.ingresospagos.repository.ConceptoPagoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConceptoPagoService {

    private final ConceptoPagoRepository conceptoPagoRepository;
    private final ConceptoPagoMapper conceptoPagoMapper;

    public List<ConceptoPagoResponse> findAll() {
        return conceptoPagoRepository.findAll().stream()
                .map(conceptoPagoMapper::toResponse)
                .toList();
    }

    public ConceptoPagoResponse findById(Long id) {
        ConceptoPago conceptoPago = obtenerPorId(id);
        return conceptoPagoMapper.toResponse(conceptoPago);
    }

    public ConceptoPagoResponse create(ConceptoPagoRequest request) {
        ConceptoPago conceptoPago = conceptoPagoMapper.toEntity(request);
        return conceptoPagoMapper.toResponse(conceptoPagoRepository.save(conceptoPago));
    }

    public ConceptoPagoResponse update(Long id, ConceptoPagoRequest request) {
        ConceptoPago conceptoPago = obtenerPorId(id);
        conceptoPago.setNombre(request.getNombre());
        conceptoPago.setDescripcion(request.getDescripcion());
        conceptoPago.setMonto(request.getMonto());
        conceptoPago.setActivo(request.getActivo());
        return conceptoPagoMapper.toResponse(conceptoPagoRepository.save(conceptoPago));
    }

    public void delete(Long id) {
        ConceptoPago conceptoPago = obtenerPorId(id);
        conceptoPagoRepository.delete(conceptoPago);
    }

    private ConceptoPago obtenerPorId(Long id) {
        return conceptoPagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Concepto de pago no encontrado con id: " + id));
    }
}
