package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.LogTransaccionPasarelaRequest;
import pe.edu.upeu.ingresospagos.dto.LogTransaccionPasarelaResponse;
import pe.edu.upeu.ingresospagos.entity.LogTransaccionPasarela;
import pe.edu.upeu.ingresospagos.entity.Pago;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.LogTransaccionPasarelaMapper;
import pe.edu.upeu.ingresospagos.repository.LogTransaccionPasarelaRepository;
import pe.edu.upeu.ingresospagos.repository.PagoRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LogTransaccionPasarelaService {

    private final LogTransaccionPasarelaRepository logTransaccionPasarelaRepository;
    private final LogTransaccionPasarelaMapper logTransaccionPasarelaMapper;
    private final PagoRepository pagoRepository;

    public List<LogTransaccionPasarelaResponse> listar() {
        return logTransaccionPasarelaRepository.findAllConPago().stream()
                .map(logTransaccionPasarelaMapper::toResponse)
                .toList();
    }

    public LogTransaccionPasarelaResponse obtener(Long id) {
        return logTransaccionPasarelaMapper.toResponse(buscarOFallar(id));
    }

    public LogTransaccionPasarelaResponse crear(LogTransaccionPasarelaRequest request) {
        LogTransaccionPasarela logTransaccionPasarela = logTransaccionPasarelaMapper.toEntity(request);
        logTransaccionPasarela.setPago(buscarPagoOFallar(request.getPagoId()));
        return logTransaccionPasarelaMapper.toResponse(logTransaccionPasarelaRepository.save(logTransaccionPasarela));
    }

    public LogTransaccionPasarelaResponse actualizar(Long id, LogTransaccionPasarelaRequest request) {
        LogTransaccionPasarela logTransaccionPasarela = buscarOFallar(id);
        logTransaccionPasarela.setProveedor(request.getProveedor());
        logTransaccionPasarela.setReferenciaExterna(request.getReferenciaExterna());
        logTransaccionPasarela.setEstado(request.getEstado());
        logTransaccionPasarela.setPayloadRespuesta(request.getPayloadRespuesta());
        logTransaccionPasarela.setPago(buscarPagoOFallar(request.getPagoId()));
        return logTransaccionPasarelaMapper.toResponse(logTransaccionPasarelaRepository.save(logTransaccionPasarela));
    }

    public void eliminar(Long id) {
        logTransaccionPasarelaRepository.delete(buscarOFallar(id));
    }

    private LogTransaccionPasarela buscarOFallar(Long id) {
        return logTransaccionPasarelaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log de transaccion de pasarela no encontrado: " + id));
    }

    private Pago buscarPagoOFallar(Long pagoId) {
        return pagoRepository.findById(pagoId)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado: " + pagoId));
    }
}
