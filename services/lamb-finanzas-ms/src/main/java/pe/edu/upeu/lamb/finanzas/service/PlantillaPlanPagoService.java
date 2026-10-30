package pe.edu.upeu.lamb.finanzas.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.finanzas.dto.PlantillaPlanPagoRequest;
import pe.edu.upeu.lamb.finanzas.dto.PlantillaPlanPagoResponse;
import pe.edu.upeu.lamb.finanzas.entity.PlantillaPlanPago;
import pe.edu.upeu.lamb.finanzas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.finanzas.mapper.PlantillaPlanPagoMapper;
import pe.edu.upeu.lamb.finanzas.repository.PlantillaPlanPagoRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * Lote F2b - Calendario y planes (item 14 del documento): Plantilla de plan de pagos
 * (contado, cuotas, pago anticipado).
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PlantillaPlanPagoService {

    private final PlantillaPlanPagoRepository plantillaPlanPagoRepository;
    private final PlantillaPlanPagoMapper plantillaPlanPagoMapper;

    public List<PlantillaPlanPagoResponse> listar() {
        return plantillaPlanPagoRepository.findAll().stream()
                .map(plantillaPlanPagoMapper::toResponse)
                .toList();
    }

    public PlantillaPlanPagoResponse obtener(Long id) {
        return plantillaPlanPagoMapper.toResponse(buscarOFallar(id));
    }

    public PlantillaPlanPagoResponse crear(PlantillaPlanPagoRequest request) {
        PlantillaPlanPago entity = plantillaPlanPagoMapper.toEntity(request);
        PlantillaPlanPago guardado = plantillaPlanPagoRepository.save(entity);
        return plantillaPlanPagoMapper.toResponse(guardado);
    }

    public PlantillaPlanPagoResponse actualizar(Long id, PlantillaPlanPagoRequest request) {
        PlantillaPlanPago existente = buscarOFallar(id);
        PlantillaPlanPago entity = plantillaPlanPagoMapper.toEntity(request);
        PlantillaPlanPago guardado = plantillaPlanPagoRepository.save(Actualizacion.copiar(entity, existente));
        return plantillaPlanPagoMapper.toResponse(guardado);
    }

    public void eliminar(Long id) {
        plantillaPlanPagoRepository.delete(buscarOFallar(id));
    }

    private PlantillaPlanPago buscarOFallar(Long id) {
        return plantillaPlanPagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plantilla de plan de pago no encontrada: " + id));
    }
}
