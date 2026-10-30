package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.BitacoraAuditoriaPagoRequest;
import pe.edu.upeu.ingresospagos.dto.BitacoraAuditoriaPagoResponse;
import pe.edu.upeu.ingresospagos.entity.BitacoraAuditoriaPago;
import pe.edu.upeu.ingresospagos.entity.Pago;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.BitacoraAuditoriaPagoMapper;
import pe.edu.upeu.ingresospagos.repository.BitacoraAuditoriaPagoRepository;
import pe.edu.upeu.ingresospagos.repository.PagoRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BitacoraAuditoriaPagoService {

    private final BitacoraAuditoriaPagoRepository bitacoraAuditoriaPagoRepository;
    private final BitacoraAuditoriaPagoMapper bitacoraAuditoriaPagoMapper;
    private final PagoRepository pagoRepository;

    public List<BitacoraAuditoriaPagoResponse> listar() {
        return bitacoraAuditoriaPagoRepository.findAllConPago().stream()
                .map(bitacoraAuditoriaPagoMapper::toResponse)
                .toList();
    }

    public BitacoraAuditoriaPagoResponse obtener(Long id) {
        return bitacoraAuditoriaPagoMapper.toResponse(buscarOFallar(id));
    }

    public BitacoraAuditoriaPagoResponse crear(BitacoraAuditoriaPagoRequest request) {
        BitacoraAuditoriaPago bitacoraAuditoriaPago = bitacoraAuditoriaPagoMapper.toEntity(request);
        bitacoraAuditoriaPago.setPago(buscarPagoOFallar(request.getPagoId()));
        return bitacoraAuditoriaPagoMapper.toResponse(bitacoraAuditoriaPagoRepository.save(bitacoraAuditoriaPago));
    }

    public BitacoraAuditoriaPagoResponse actualizar(Long id, BitacoraAuditoriaPagoRequest request) {
        BitacoraAuditoriaPago bitacoraAuditoriaPago = buscarOFallar(id);
        bitacoraAuditoriaPago.setAccion(request.getAccion());
        bitacoraAuditoriaPago.setIdUsuario(request.getIdUsuario());
        bitacoraAuditoriaPago.setObservaciones(request.getObservaciones());
        bitacoraAuditoriaPago.setPago(buscarPagoOFallar(request.getPagoId()));
        return bitacoraAuditoriaPagoMapper.toResponse(bitacoraAuditoriaPagoRepository.save(bitacoraAuditoriaPago));
    }

    public void eliminar(Long id) {
        bitacoraAuditoriaPagoRepository.delete(buscarOFallar(id));
    }

    private BitacoraAuditoriaPago buscarOFallar(Long id) {
        return bitacoraAuditoriaPagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bitacora de auditoria no encontrada: " + id));
    }

    private Pago buscarPagoOFallar(Long pagoId) {
        return pagoRepository.findById(pagoId)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado: " + pagoId));
    }
}
