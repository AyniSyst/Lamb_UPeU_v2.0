package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.TarifarioProgramaRequest;
import pe.edu.upeu.ingresospagos.dto.TarifarioProgramaResponse;
import pe.edu.upeu.ingresospagos.entity.ConceptoPago;
import pe.edu.upeu.ingresospagos.entity.TarifarioPrograma;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.TarifarioProgramaMapper;
import pe.edu.upeu.ingresospagos.repository.ConceptoPagoRepository;
import pe.edu.upeu.ingresospagos.repository.TarifarioProgramaRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TarifarioProgramaService {

    private final TarifarioProgramaRepository tarifarioProgramaRepository;
    private final TarifarioProgramaMapper tarifarioProgramaMapper;
    private final ConceptoPagoRepository conceptoPagoRepository;

    public List<TarifarioProgramaResponse> listar() {
        return tarifarioProgramaRepository.findAllConConceptoPago().stream()
                .map(tarifarioProgramaMapper::toResponse)
                .toList();
    }

    public TarifarioProgramaResponse obtener(Long id) {
        return tarifarioProgramaMapper.toResponse(buscarOFallar(id));
    }

    public TarifarioProgramaResponse crear(TarifarioProgramaRequest request) {
        TarifarioPrograma tarifarioPrograma = tarifarioProgramaMapper.toEntity(request);
        tarifarioPrograma.setConceptoPago(buscarConceptoPagoOFallar(request.getConceptoPagoId()));
        return tarifarioProgramaMapper.toResponse(tarifarioProgramaRepository.save(tarifarioPrograma));
    }

    public TarifarioProgramaResponse actualizar(Long id, TarifarioProgramaRequest request) {
        TarifarioPrograma tarifarioPrograma = buscarOFallar(id);
        tarifarioPrograma.setPrograma(request.getPrograma());
        tarifarioPrograma.setMonto(request.getMonto());
        tarifarioPrograma.setVigenteDesde(request.getVigenteDesde());
        tarifarioPrograma.setVigenteHasta(request.getVigenteHasta());
        tarifarioPrograma.setActivo(request.getActivo());
        tarifarioPrograma.setConceptoPago(buscarConceptoPagoOFallar(request.getConceptoPagoId()));
        return tarifarioProgramaMapper.toResponse(tarifarioProgramaRepository.save(tarifarioPrograma));
    }

    public void eliminar(Long id) {
        tarifarioProgramaRepository.delete(buscarOFallar(id));
    }

    private TarifarioPrograma buscarOFallar(Long id) {
        return tarifarioProgramaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarifario de programa no encontrado: " + id));
    }

    private ConceptoPago buscarConceptoPagoOFallar(Long conceptoPagoId) {
        return conceptoPagoRepository.findById(conceptoPagoId)
                .orElseThrow(() -> new ResourceNotFoundException("Concepto de pago no encontrado: " + conceptoPagoId));
    }
}
