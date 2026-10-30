package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.BecaAsignadaRequest;
import pe.edu.upeu.ingresospagos.dto.BecaAsignadaResponse;
import pe.edu.upeu.ingresospagos.entity.Beca;
import pe.edu.upeu.ingresospagos.entity.BecaAsignada;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.BecaAsignadaMapper;
import pe.edu.upeu.ingresospagos.repository.BecaAsignadaRepository;
import pe.edu.upeu.ingresospagos.repository.BecaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BecaAsignadaService {

    private final BecaAsignadaRepository becaAsignadaRepository;
    private final BecaAsignadaMapper becaAsignadaMapper;
    private final BecaRepository becaRepository;

    public List<BecaAsignadaResponse> listar() {
        return becaAsignadaRepository.findAllConBeca().stream()
                .map(becaAsignadaMapper::toResponse)
                .toList();
    }

    public BecaAsignadaResponse obtener(Long id) {
        return becaAsignadaMapper.toResponse(buscarOFallar(id));
    }

    public BecaAsignadaResponse crear(BecaAsignadaRequest request) {
        BecaAsignada becaAsignada = becaAsignadaMapper.toEntity(request);
        becaAsignada.setBeca(buscarBecaOFallar(request.getBecaId()));
        return becaAsignadaMapper.toResponse(becaAsignadaRepository.save(becaAsignada));
    }

    public BecaAsignadaResponse actualizar(Long id, BecaAsignadaRequest request) {
        BecaAsignada becaAsignada = buscarOFallar(id);
        becaAsignada.setIdEstudiante(request.getIdEstudiante());
        becaAsignada.setActivo(request.getActivo());
        becaAsignada.setBeca(buscarBecaOFallar(request.getBecaId()));
        return becaAsignadaMapper.toResponse(becaAsignadaRepository.save(becaAsignada));
    }

    public void eliminar(Long id) {
        becaAsignadaRepository.delete(buscarOFallar(id));
    }

    private BecaAsignada buscarOFallar(Long id) {
        return becaAsignadaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Beca asignada no encontrada: " + id));
    }

    private Beca buscarBecaOFallar(Long becaId) {
        return becaRepository.findById(becaId)
                .orElseThrow(() -> new ResourceNotFoundException("Beca no encontrada: " + becaId));
    }
}
