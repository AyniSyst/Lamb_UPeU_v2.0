package pe.edu.upeu.lamb.personas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.personas.dto.ColegioProcedenciaRequest;
import pe.edu.upeu.lamb.personas.dto.ColegioProcedenciaResponse;
import pe.edu.upeu.lamb.personas.entity.ColegioProcedencia;
import pe.edu.upeu.lamb.personas.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.personas.mapper.ColegioProcedenciaMapper;
import pe.edu.upeu.lamb.personas.repository.ColegioProcedenciaRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ColegioProcedenciaService {

    private final ColegioProcedenciaRepository colegioProcedenciaRepository;
    private final ColegioProcedenciaMapper colegioProcedenciaMapper;

    public List<ColegioProcedenciaResponse> listar() {
        return colegioProcedenciaRepository.findAll().stream()
                .map(colegioProcedenciaMapper::toResponse)
                .toList();
    }

    public ColegioProcedenciaResponse obtener(Long id) {
        return colegioProcedenciaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ColegioProcedenciaResponse crear(ColegioProcedenciaRequest request) {
        ColegioProcedencia entity = colegioProcedenciaMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return colegioProcedenciaMapper.toResponse(colegioProcedenciaRepository.save(entity));
    }

    public ColegioProcedenciaResponse actualizar(Long id, ColegioProcedenciaRequest request) {
        ColegioProcedencia existente = buscarOFallar(id);
        ColegioProcedencia entity = colegioProcedenciaMapper.toEntity(request);
        entity.setFechaRegistro(Instant.now());
        return colegioProcedenciaMapper.toResponse(colegioProcedenciaRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        colegioProcedenciaRepository.delete(buscarOFallar(id));
    }

    private ColegioProcedencia buscarOFallar(Long id) {
        return colegioProcedenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Colegio de procedencia no encontrado: " + id));
    }
}
