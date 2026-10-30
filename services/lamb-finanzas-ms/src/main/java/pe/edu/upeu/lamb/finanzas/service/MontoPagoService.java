package pe.edu.upeu.lamb.finanzas.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.finanzas.dto.MontoPagoRequest;
import pe.edu.upeu.lamb.finanzas.dto.MontoPagoResponse;
import pe.edu.upeu.lamb.finanzas.entity.EstadoCobro;
import pe.edu.upeu.lamb.finanzas.entity.MontoPago;
import pe.edu.upeu.lamb.finanzas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.finanzas.mapper.MontoPagoMapper;
import pe.edu.upeu.lamb.finanzas.repository.EstadoCobroRepository;
import pe.edu.upeu.lamb.finanzas.repository.MontoPagoRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * Item 21 del documento: "Monto de Pago".
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MontoPagoService {

    private final MontoPagoRepository montoPagoRepository;
    private final EstadoCobroRepository estadoCobroRepository;
    private final MontoPagoMapper montoPagoMapper;

    public List<MontoPagoResponse> listar() {
        return montoPagoRepository.findAll().stream()
                .map(montoPagoMapper::toResponse)
                .toList();
    }

    public MontoPagoResponse obtener(Long id) {
        return montoPagoMapper.toResponse(buscarOFallar(id));
    }

    public MontoPagoResponse crear(MontoPagoRequest request) {
        EstadoCobro estadoCobro = estadoCobroRepository.findById(request.getIdEstadoCobro())
                .orElseThrow(() -> new ResourceNotFoundException("Estado de cobro no encontrado: " + request.getIdEstadoCobro()));

        MontoPago entity = MontoPago.builder()
                .estadoCobro(estadoCobro)
                .monto(request.getMonto())
                .fecha(request.getFecha())
                .build();

        MontoPago guardado = montoPagoRepository.save(entity);
        return montoPagoMapper.toResponse(guardado);
    }

    public MontoPagoResponse actualizar(Long id, MontoPagoRequest request) {
        MontoPago existente = buscarOFallar(id);
        EstadoCobro estadoCobro = estadoCobroRepository.findById(request.getIdEstadoCobro())
                .orElseThrow(() -> new ResourceNotFoundException("Estado de cobro no encontrado: " + request.getIdEstadoCobro()));

        MontoPago entity = MontoPago.builder()
                .estadoCobro(estadoCobro)
                .monto(request.getMonto())
                .fecha(request.getFecha())
                .build();

        MontoPago guardado = montoPagoRepository.save(Actualizacion.copiar(entity, existente));
        return montoPagoMapper.toResponse(guardado);
    }

    public void eliminar(Long id) {
        montoPagoRepository.delete(buscarOFallar(id));
    }

    private MontoPago buscarOFallar(Long id) {
        return montoPagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monto de pago no encontrado: " + id));
    }
}
