package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.RecargoMoraRequest;
import pe.edu.upeu.ingresospagos.dto.RecargoMoraResponse;
import pe.edu.upeu.ingresospagos.entity.RecargoMora;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.RecargoMoraMapper;
import pe.edu.upeu.ingresospagos.repository.RecargoMoraRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecargoMoraService {

    private final RecargoMoraRepository recargoMoraRepository;
    private final RecargoMoraMapper recargoMoraMapper;

    public List<RecargoMoraResponse> findAll() {
        return recargoMoraRepository.findAll().stream()
                .map(recargoMoraMapper::toResponse)
                .toList();
    }

    public RecargoMoraResponse findById(Long id) {
        return recargoMoraMapper.toResponse(obtenerPorId(id));
    }

    public RecargoMoraResponse create(RecargoMoraRequest request) {
        RecargoMora recargoMora = recargoMoraMapper.toEntity(request);
        return recargoMoraMapper.toResponse(recargoMoraRepository.save(recargoMora));
    }

    public RecargoMoraResponse update(Long id, RecargoMoraRequest request) {
        RecargoMora recargoMora = obtenerPorId(id);
        recargoMora.setNombre(request.getNombre());
        recargoMora.setTipoCalculo(request.getTipoCalculo());
        recargoMora.setValor(request.getValor());
        recargoMora.setDiasGracia(request.getDiasGracia());
        recargoMora.setActivo(request.getActivo());
        return recargoMoraMapper.toResponse(recargoMoraRepository.save(recargoMora));
    }

    public void delete(Long id) {
        recargoMoraRepository.delete(obtenerPorId(id));
    }

    private RecargoMora obtenerPorId(Long id) {
        return recargoMoraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recargo por mora no encontrado con id: " + id));
    }
}
