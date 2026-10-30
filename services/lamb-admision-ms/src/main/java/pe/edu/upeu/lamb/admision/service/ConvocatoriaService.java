package pe.edu.upeu.lamb.admision.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.admision.dto.ConvocatoriaRequest;
import pe.edu.upeu.lamb.admision.dto.ConvocatoriaResponse;
import pe.edu.upeu.lamb.admision.entity.Convocatoria;
import pe.edu.upeu.lamb.admision.exception.BusinessRuleException;
import pe.edu.upeu.lamb.admision.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.admision.mapper.ConvocatoriaMapper;
import pe.edu.upeu.lamb.admision.repository.ConvocatoriaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConvocatoriaService {

    private final ConvocatoriaRepository repo;
    private final ConvocatoriaMapper mapper;

    @Transactional(readOnly = true)
    public List<ConvocatoriaResponse> listar(String periodo) {
        List<Convocatoria> data = periodo != null ? repo.findByPeriodo(periodo) : repo.findAll();
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ConvocatoriaResponse obtener(Long id) {
        return mapper.toResponse(buscar(id));
    }

    @Transactional
    public ConvocatoriaResponse crear(ConvocatoriaRequest r) {
        validar(r, null);
        return mapper.toResponse(repo.save(mapper.toEntity(r)));
    }

    @Transactional
    public ConvocatoriaResponse actualizar(Long id, ConvocatoriaRequest r) {
        Convocatoria c = buscar(id);
        validar(r, id);
        c.setCodigo(r.getCodigo());
        c.setNombre(r.getNombre());
        c.setPeriodo(r.getPeriodo());
        c.setFechaInicio(r.getFechaInicio());
        c.setFechaFin(r.getFechaFin());
        c.setFechaExamen(r.getFechaExamen());
        c.setVacantesTotales(r.getVacantesTotales());
        c.setObservacion(r.getObservacion());
        return mapper.toResponse(c);
    }

    @Transactional
    public void eliminar(Long id) {
        repo.delete(buscar(id));
    }

    private void validar(ConvocatoriaRequest r, Long id) {
        if (r.getFechaFin().isBefore(r.getFechaInicio())) {
            throw new BusinessRuleException("La fecha fin no puede ser anterior a la fecha inicio");
        }
        boolean dup = id == null ? repo.existsByCodigo(r.getCodigo())
                : repo.existsByCodigoAndIdNot(r.getCodigo(), id);
        if (dup) {
            throw new BusinessRuleException("Ya existe una convocatoria con el codigo " + r.getCodigo());
        }
    }

    private Convocatoria buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convocatoria no encontrada: " + id));
    }
}
