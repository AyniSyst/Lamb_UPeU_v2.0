package pe.edu.upeu.lamb.finanzas.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.finanzas.dto.DocumentoPagoRequest;
import pe.edu.upeu.lamb.finanzas.dto.DocumentoPagoResponse;
import pe.edu.upeu.lamb.finanzas.entity.DocumentoPago;
import pe.edu.upeu.lamb.finanzas.entity.Pago;
import pe.edu.upeu.lamb.finanzas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.finanzas.mapper.DocumentoPagoMapper;
import pe.edu.upeu.lamb.finanzas.repository.DocumentoPagoRepository;
import pe.edu.upeu.lamb.finanzas.repository.PagoRepository;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DocumentoPagoService {

    private final DocumentoPagoRepository documentoPagoRepository;
    private final PagoRepository pagoRepository;
    private final DocumentoPagoMapper documentoPagoMapper;

    public List<DocumentoPagoResponse> listar() {
        return documentoPagoRepository.findAll().stream()
                .map(documentoPagoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public DocumentoPagoResponse obtener(Long id) {
        return documentoPagoMapper.toResponse(buscarOFallar(id));
    }

    public DocumentoPagoResponse crear(DocumentoPagoRequest request) {
        Pago pago = pagoRepository.findById(request.getIdPago())
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con id: " + request.getIdPago()));

        DocumentoPago entity = DocumentoPago.builder()
                .pago(pago)
                .tipo(request.getTipo())
                .serie(request.getSerie())
                .correlativo(request.getCorrelativo())
                .monto(request.getMonto())
                .fechaEmision(request.getFechaEmision())
                .build();

        DocumentoPago guardado = documentoPagoRepository.save(entity);
        log.info("Documento de pago creado con id: {}", guardado.getId());
        return documentoPagoMapper.toResponse(guardado);
    }

    public DocumentoPagoResponse actualizar(Long id, DocumentoPagoRequest request) {
        DocumentoPago existente = buscarOFallar(id);
        Pago pago = pagoRepository.findById(request.getIdPago())
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con id: " + request.getIdPago()));

        DocumentoPago entity = DocumentoPago.builder()
                .pago(pago)
                .tipo(request.getTipo())
                .serie(request.getSerie())
                .correlativo(request.getCorrelativo())
                .monto(request.getMonto())
                .fechaEmision(request.getFechaEmision())
                .build();

        DocumentoPago guardado = documentoPagoRepository.save(Actualizacion.copiar(entity, existente));
        log.info("Documento de pago creado con id: {}", guardado.getId());
        return documentoPagoMapper.toResponse(guardado);
    }

    public void eliminar(Long id) {
        documentoPagoRepository.delete(buscarOFallar(id));
    }

    private DocumentoPago buscarOFallar(Long id) {
        return documentoPagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Documento de pago no encontrado con id: " + id));
    }
}
