package pe.edu.upeu.lamb.institucional.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.institucional.dto.ParametroSedeEscuelaRequest;
import pe.edu.upeu.lamb.institucional.dto.ParametroSedeEscuelaResponse;
import pe.edu.upeu.lamb.institucional.entity.ParametroSedeEscuela;
import pe.edu.upeu.lamb.institucional.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.institucional.mapper.ParametroSedeEscuelaMapper;
import pe.edu.upeu.lamb.institucional.repository.ParametroSedeEscuelaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParametroSedeEscuelaService {

    private final ParametroSedeEscuelaRepository parametroSedeEscuelaRepository;
    private final ParametroSedeEscuelaMapper parametroSedeEscuelaMapper;

    public List<ParametroSedeEscuelaResponse> listar() {
        return parametroSedeEscuelaRepository.findAll().stream()
                .map(parametroSedeEscuelaMapper::toResponse)
                .toList();
    }

    public ParametroSedeEscuelaResponse obtener(Long id) {
        return parametroSedeEscuelaMapper.toResponse(buscarOFallar(id));
    }

    public ParametroSedeEscuelaResponse crear(ParametroSedeEscuelaRequest request) {
        ParametroSedeEscuela entity = parametroSedeEscuelaMapper.toEntity(request);
        return parametroSedeEscuelaMapper.toResponse(parametroSedeEscuelaRepository.save(entity));
    }

    private ParametroSedeEscuela buscarOFallar(Long id) {
        return parametroSedeEscuelaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ParametroSedeEscuela no encontrado con id " + id));
    }
}
