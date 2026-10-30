package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.CajaRequest;
import pe.edu.upeu.ingresospagos.dto.CajaResponse;
import pe.edu.upeu.ingresospagos.entity.Caja;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.CajaMapper;
import pe.edu.upeu.ingresospagos.repository.CajaRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CajaService {

    private final CajaRepository cajaRepository;
    private final CajaMapper cajaMapper;

    public List<CajaResponse> findAll() {
        return cajaRepository.findAll().stream()
                .map(cajaMapper::toResponse)
                .toList();
    }

    public CajaResponse findById(Long id) {
        return cajaMapper.toResponse(obtenerPorId(id));
    }

    public CajaResponse create(CajaRequest request) {
        Caja caja = cajaMapper.toEntity(request);
        return cajaMapper.toResponse(cajaRepository.save(caja));
    }

    public CajaResponse update(Long id, CajaRequest request) {
        Caja caja = obtenerPorId(id);
        caja.setNombre(request.getNombre());
        caja.setUbicacion(request.getUbicacion());
        caja.setActivo(request.getActivo());
        return cajaMapper.toResponse(cajaRepository.save(caja));
    }

    public void delete(Long id) {
        cajaRepository.delete(obtenerPorId(id));
    }

    private Caja obtenerPorId(Long id) {
        return cajaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Caja no encontrada con id: " + id));
    }
}
