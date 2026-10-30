package pe.edu.upeu.lamb.trayectoria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.trayectoria.dto.CumplimientoRequisitoRequest;
import pe.edu.upeu.lamb.trayectoria.dto.CumplimientoRequisitoResponse;
import pe.edu.upeu.lamb.trayectoria.entity.CumplimientoRequisito;
import pe.edu.upeu.lamb.trayectoria.entity.Trayectoria;
import pe.edu.upeu.lamb.trayectoria.exception.BusinessRuleException;
import pe.edu.upeu.lamb.trayectoria.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.trayectoria.mapper.AvanceRequisitosMapper;
import pe.edu.upeu.lamb.trayectoria.repository.CumplimientoRequisitoRepository;
import pe.edu.upeu.lamb.trayectoria.repository.TrayectoriaRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvanceRequisitosService {

    private final CumplimientoRequisitoRepository repo;
    private final TrayectoriaRepository trayectoriaRepository;
    private final AvanceRequisitosMapper mapper;

    @Transactional(readOnly = true)
    public List<CumplimientoRequisitoResponse> listar(Long idPersona, Long idTrayectoria) {
        List<CumplimientoRequisito> data;
        if (idTrayectoria != null) {
            data = repo.findByIdTrayectoria(idTrayectoria);
        } else if (idPersona != null) {
            data = repo.findByIdPersona(idPersona);
        } else {
            data = repo.findAll();
        }
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CumplimientoRequisitoResponse obtener(Long id) {
        return mapper.toResponse(buscar(id));
    }

    @Transactional
    public CumplimientoRequisitoResponse crear(CumplimientoRequisitoRequest r) {
        validarTrayectoria(r.idTrayectoria(), r.idPersona());
        CumplimientoRequisito c = mapper.toEntity(r);
        if (c.isCumplido() && c.getFechaCumplimiento() == null) {
            c.setFechaCumplimiento(LocalDate.now());
        }
        return mapper.toResponse(repo.save(c));
    }

    @Transactional
    public CumplimientoRequisitoResponse actualizar(Long id, CumplimientoRequisitoRequest r) {
        CumplimientoRequisito c = buscar(id);
        validarTrayectoria(r.idTrayectoria(), r.idPersona());
        mapper.update(c, r);
        if (c.isCumplido() && c.getFechaCumplimiento() == null) {
            c.setFechaCumplimiento(LocalDate.now());
        }
        return mapper.toResponse(c);
    }

    @Transactional
    public void eliminar(Long id) {
        repo.delete(buscar(id));
    }

    private void validarTrayectoria(Long idTrayectoria, Long idPersona) {
        Trayectoria t = trayectoriaRepository.findById(idTrayectoria)
                .orElseThrow(() -> new ResourceNotFoundException("Trayectoria no encontrada: " + idTrayectoria));
        if (!t.getIdPersona().equals(idPersona)) {
            throw new BusinessRuleException("La trayectoria no pertenece a la persona indicada");
        }
    }

    private CumplimientoRequisito buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cumplimiento de requisito no encontrado: " + id));
    }
}
