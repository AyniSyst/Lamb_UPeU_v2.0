package pe.edu.upeu.lamb.finanzas.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.finanzas.dto.EstadoCobroRequest;
import pe.edu.upeu.lamb.finanzas.dto.EstadoCobroResponse;
import pe.edu.upeu.lamb.finanzas.entity.CuentaEstudiante;
import pe.edu.upeu.lamb.finanzas.entity.EstadoCobro;
import pe.edu.upeu.lamb.finanzas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.finanzas.mapper.EstadoCobroMapper;
import pe.edu.upeu.lamb.finanzas.repository.CuentaEstudianteRepository;
import pe.edu.upeu.lamb.finanzas.repository.EstadoCobroRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * Lote Cobros (item 17 del documento): Estado de cobro (con deuda, sin deuda).
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EstadoCobroService {

    private final EstadoCobroRepository estadoCobroRepository;
    private final CuentaEstudianteRepository cuentaEstudianteRepository;
    private final EstadoCobroMapper estadoCobroMapper;

    public List<EstadoCobroResponse> listar() {
        return estadoCobroRepository.findAll().stream()
                .map(estadoCobroMapper::toResponse)
                .toList();
    }

    public EstadoCobroResponse obtener(Long id) {
        return estadoCobroMapper.toResponse(buscarOFallar(id));
    }

    public EstadoCobroResponse crear(EstadoCobroRequest request) {
        CuentaEstudiante cuentaEstudiante = cuentaEstudianteRepository.findById(request.getIdCuentaEstudiante())
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta de estudiante no encontrada: " + request.getIdCuentaEstudiante()));

        EstadoCobro entity = EstadoCobro.builder()
                .cuentaEstudiante(cuentaEstudiante)
                .estado(request.getEstado())
                .fecha(request.getFecha())
                .build();

        EstadoCobro guardado = estadoCobroRepository.save(entity);
        return estadoCobroMapper.toResponse(guardado);
    }

    public EstadoCobroResponse actualizar(Long id, EstadoCobroRequest request) {
        EstadoCobro existente = buscarOFallar(id);
        CuentaEstudiante cuentaEstudiante = cuentaEstudianteRepository.findById(request.getIdCuentaEstudiante())
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta de estudiante no encontrada: " + request.getIdCuentaEstudiante()));

        EstadoCobro entity = EstadoCobro.builder()
                .cuentaEstudiante(cuentaEstudiante)
                .estado(request.getEstado())
                .fecha(request.getFecha())
                .build();

        EstadoCobro guardado = estadoCobroRepository.save(Actualizacion.copiar(entity, existente));
        return estadoCobroMapper.toResponse(guardado);
    }

    public void eliminar(Long id) {
        estadoCobroRepository.delete(buscarOFallar(id));
    }

    private EstadoCobro buscarOFallar(Long id) {
        return estadoCobroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado de cobro no encontrado: " + id));
    }
}
