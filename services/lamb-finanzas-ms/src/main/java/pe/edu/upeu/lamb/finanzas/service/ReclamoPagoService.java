package pe.edu.upeu.lamb.finanzas.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.finanzas.dto.ReclamoPagoRequest;
import pe.edu.upeu.lamb.finanzas.dto.ReclamoPagoResponse;
import pe.edu.upeu.lamb.finanzas.entity.Pago;
import pe.edu.upeu.lamb.finanzas.entity.ReclamoPago;
import pe.edu.upeu.lamb.finanzas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.finanzas.mapper.ReclamoPagoMapper;
import pe.edu.upeu.lamb.finanzas.repository.PagoRepository;
import pe.edu.upeu.lamb.finanzas.repository.ReclamoPagoRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReclamoPagoService {

    private final ReclamoPagoRepository reclamoPagoRepository;
    private final PagoRepository pagoRepository;
    private final ReclamoPagoMapper reclamoPagoMapper;

    public List<ReclamoPagoResponse> listar() {
        return reclamoPagoRepository.findAll().stream()
                .map(reclamoPagoMapper::toResponse)
                .toList();
    }

    public ReclamoPagoResponse obtener(Long id) {
        return reclamoPagoMapper.toResponse(buscarOFallar(id));
    }

    public ReclamoPagoResponse crear(ReclamoPagoRequest request) {
        Pago pago = pagoRepository.findById(request.getIdPago())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el pago con id " + request.getIdPago()));

        ReclamoPago entity = ReclamoPago.builder()
                .pago(pago)
                .descripcion(request.getDescripcion())
                .resuelto(request.isResuelto())
                .fecha(request.getFecha())
                .build();

        return reclamoPagoMapper.toResponse(reclamoPagoRepository.save(entity));
    }

    public ReclamoPagoResponse actualizar(Long id, ReclamoPagoRequest request) {
        ReclamoPago existente = buscarOFallar(id);
        Pago pago = pagoRepository.findById(request.getIdPago())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el pago con id " + request.getIdPago()));

        ReclamoPago entity = ReclamoPago.builder()
                .pago(pago)
                .descripcion(request.getDescripcion())
                .resuelto(request.isResuelto())
                .fecha(request.getFecha())
                .build();

        return reclamoPagoMapper.toResponse(reclamoPagoRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        reclamoPagoRepository.delete(buscarOFallar(id));
    }

    private ReclamoPago buscarOFallar(Long id) {
        return reclamoPagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el reclamo de pago con id " + id));
    }
}
