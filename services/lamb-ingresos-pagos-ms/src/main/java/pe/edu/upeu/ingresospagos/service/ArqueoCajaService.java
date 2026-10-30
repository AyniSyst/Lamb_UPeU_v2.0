package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.ArqueoCajaRequest;
import pe.edu.upeu.ingresospagos.dto.ArqueoCajaResponse;
import pe.edu.upeu.ingresospagos.entity.ArqueoCaja;
import pe.edu.upeu.ingresospagos.entity.SesionCaja;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.ArqueoCajaMapper;
import pe.edu.upeu.ingresospagos.repository.ArqueoCajaRepository;
import pe.edu.upeu.ingresospagos.repository.SesionCajaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArqueoCajaService {

    private final ArqueoCajaRepository arqueoCajaRepository;
    private final ArqueoCajaMapper arqueoCajaMapper;
    private final SesionCajaRepository sesionCajaRepository;

    public List<ArqueoCajaResponse> listar() {
        return arqueoCajaRepository.findAllConSesionCaja().stream()
                .map(arqueoCajaMapper::toResponse)
                .toList();
    }

    public ArqueoCajaResponse obtener(Long id) {
        return arqueoCajaMapper.toResponse(buscarOFallar(id));
    }

    public ArqueoCajaResponse crear(ArqueoCajaRequest request) {
        ArqueoCaja arqueoCaja = arqueoCajaMapper.toEntity(request);
        arqueoCaja.setSesionCaja(buscarSesionCajaOFallar(request.getSesionCajaId()));
        arqueoCaja.setDiferencia(request.getMontoContado().subtract(request.getMontoSistema()));
        return arqueoCajaMapper.toResponse(arqueoCajaRepository.save(arqueoCaja));
    }

    public ArqueoCajaResponse actualizar(Long id, ArqueoCajaRequest request) {
        ArqueoCaja arqueoCaja = buscarOFallar(id);
        arqueoCaja.setMontoContado(request.getMontoContado());
        arqueoCaja.setMontoSistema(request.getMontoSistema());
        arqueoCaja.setDiferencia(request.getMontoContado().subtract(request.getMontoSistema()));
        arqueoCaja.setObservaciones(request.getObservaciones());
        arqueoCaja.setSesionCaja(buscarSesionCajaOFallar(request.getSesionCajaId()));
        return arqueoCajaMapper.toResponse(arqueoCajaRepository.save(arqueoCaja));
    }

    public void eliminar(Long id) {
        arqueoCajaRepository.delete(buscarOFallar(id));
    }

    private ArqueoCaja buscarOFallar(Long id) {
        return arqueoCajaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arqueo de caja no encontrado: " + id));
    }

    private SesionCaja buscarSesionCajaOFallar(Long sesionCajaId) {
        return sesionCajaRepository.findById(sesionCajaId)
                .orElseThrow(() -> new ResourceNotFoundException("Sesion de caja no encontrada: " + sesionCajaId));
    }
}
