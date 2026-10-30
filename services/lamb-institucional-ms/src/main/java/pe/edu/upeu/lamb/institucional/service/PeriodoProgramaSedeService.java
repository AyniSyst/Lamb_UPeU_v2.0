package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.PeriodoProgramaSedeRequest;
import pe.edu.upeu.lamb.institucional.dto.PeriodoProgramaSedeResponse;
import pe.edu.upeu.lamb.institucional.entity.PeriodoProgramaSede;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.PeriodoProgramaSedeMapper;
import pe.edu.upeu.lamb.institucional.repository.PeriodoProgramaSedeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeriodoProgramaSedeService {

    private final PeriodoProgramaSedeRepository periodoProgramaSedeRepository;
    private final PeriodoProgramaSedeMapper periodoProgramaSedeMapper;

    public List<PeriodoProgramaSedeResponse> listar() {
        return periodoProgramaSedeRepository.findAll().stream()
                .map(periodoProgramaSedeMapper::toResponse)
                .toList();
    }

    public PeriodoProgramaSedeResponse obtener(Long id) {
        return periodoProgramaSedeMapper.toResponse(buscarOFallar(id));
    }

    public PeriodoProgramaSedeResponse crear(PeriodoProgramaSedeRequest request) {
        PeriodoProgramaSede entity = periodoProgramaSedeMapper.toEntity(request);
        return periodoProgramaSedeMapper.toResponse(periodoProgramaSedeRepository.save(entity));
    }

    private PeriodoProgramaSede buscarOFallar(Long id) {
        return periodoProgramaSedeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PeriodoProgramaSede no encontrado con id: " + id));
    }
}
