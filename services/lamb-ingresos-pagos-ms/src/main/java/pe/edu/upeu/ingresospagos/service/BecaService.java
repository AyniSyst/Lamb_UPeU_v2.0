package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.BecaRequest;
import pe.edu.upeu.ingresospagos.dto.BecaResponse;
import pe.edu.upeu.ingresospagos.entity.Beca;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.BecaMapper;
import pe.edu.upeu.ingresospagos.repository.BecaRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BecaService {

    private final BecaRepository becaRepository;
    private final BecaMapper becaMapper;

    public List<BecaResponse> findAll() {
        return becaRepository.findAll().stream()
                .map(becaMapper::toResponse)
                .toList();
    }

    public BecaResponse findById(Long id) {
        return becaMapper.toResponse(obtenerPorId(id));
    }

    public BecaResponse create(BecaRequest request) {
        Beca beca = becaMapper.toEntity(request);
        return becaMapper.toResponse(becaRepository.save(beca));
    }

    public BecaResponse update(Long id, BecaRequest request) {
        Beca beca = obtenerPorId(id);
        beca.setNombre(request.getNombre());
        beca.setDescripcion(request.getDescripcion());
        beca.setPorcentajeDescuento(request.getPorcentajeDescuento());
        beca.setActivo(request.getActivo());
        return becaMapper.toResponse(becaRepository.save(beca));
    }

    public void delete(Long id) {
        becaRepository.delete(obtenerPorId(id));
    }

    private Beca obtenerPorId(Long id) {
        return becaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Beca no encontrada con id: " + id));
    }
}
