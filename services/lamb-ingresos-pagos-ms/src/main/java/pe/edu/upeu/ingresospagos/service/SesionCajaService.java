package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.SesionCajaRequest;
import pe.edu.upeu.ingresospagos.dto.SesionCajaResponse;
import pe.edu.upeu.ingresospagos.entity.Caja;
import pe.edu.upeu.ingresospagos.entity.SesionCaja;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.SesionCajaMapper;
import pe.edu.upeu.ingresospagos.repository.CajaRepository;
import pe.edu.upeu.ingresospagos.repository.SesionCajaRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SesionCajaService {

    private final SesionCajaRepository sesionCajaRepository;
    private final SesionCajaMapper sesionCajaMapper;
    private final CajaRepository cajaRepository;

    public List<SesionCajaResponse> listar() {
        return sesionCajaRepository.findAllConCaja().stream()
                .map(sesionCajaMapper::toResponse)
                .toList();
    }

    public SesionCajaResponse obtener(Long id) {
        return sesionCajaMapper.toResponse(buscarOFallar(id));
    }

    public SesionCajaResponse crear(SesionCajaRequest request) {
        SesionCaja sesionCaja = sesionCajaMapper.toEntity(request);
        sesionCaja.setCaja(buscarCajaOFallar(request.getCajaId()));
        return sesionCajaMapper.toResponse(sesionCajaRepository.save(sesionCaja));
    }

    public SesionCajaResponse actualizar(Long id, SesionCajaRequest request) {
        SesionCaja sesionCaja = buscarOFallar(id);
        sesionCaja.setIdCajero(request.getIdCajero());
        sesionCaja.setFechaApertura(request.getFechaApertura());
        sesionCaja.setFechaCierre(request.getFechaCierre());
        sesionCaja.setMontoInicial(request.getMontoInicial());
        sesionCaja.setMontoFinal(request.getMontoFinal());
        sesionCaja.setCaja(buscarCajaOFallar(request.getCajaId()));
        return sesionCajaMapper.toResponse(sesionCajaRepository.save(sesionCaja));
    }

    public void eliminar(Long id) {
        sesionCajaRepository.delete(buscarOFallar(id));
    }

    private SesionCaja buscarOFallar(Long id) {
        return sesionCajaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sesion de caja no encontrada: " + id));
    }

    private Caja buscarCajaOFallar(Long cajaId) {
        return cajaRepository.findById(cajaId)
                .orElseThrow(() -> new ResourceNotFoundException("Caja no encontrada: " + cajaId));
    }
}
