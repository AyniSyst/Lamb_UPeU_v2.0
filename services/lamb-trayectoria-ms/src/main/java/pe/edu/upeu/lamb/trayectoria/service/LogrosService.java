package pe.edu.upeu.lamb.trayectoria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.trayectoria.dto.ReconocimientoRequest;
import pe.edu.upeu.lamb.trayectoria.dto.ReconocimientoResponse;
import pe.edu.upeu.lamb.trayectoria.entity.Reconocimiento;
import pe.edu.upeu.lamb.trayectoria.entity.Trayectoria;
import pe.edu.upeu.lamb.trayectoria.exception.BusinessRuleException;
import pe.edu.upeu.lamb.trayectoria.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.trayectoria.mapper.LogrosMapper;
import pe.edu.upeu.lamb.trayectoria.repository.ReconocimientoRepository;
import pe.edu.upeu.lamb.trayectoria.repository.TrayectoriaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogrosService {

    private final ReconocimientoRepository repo;
    private final TrayectoriaRepository trayectoriaRepository;
    private final LogrosMapper mapper;

    @Transactional(readOnly = true)
    public List<ReconocimientoResponse> listar(Long idPersona) {
        List<Reconocimiento> data = idPersona != null
                ? repo.findByIdPersonaOrderByFechaRegistroDesc(idPersona)
                : repo.findAll();
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ReconocimientoResponse obtener(Long id) {
        return mapper.toResponse(buscar(id));
    }

    @Transactional
    public ReconocimientoResponse crear(ReconocimientoRequest r) {
        validarTrayectoria(r.idTrayectoria(), r.idPersona());
        Reconocimiento c = mapper.toEntity(r);
        if (c.getFechaRegistro() == null) {
            c.setFechaRegistro(LocalDateTime.now());
        }
        return mapper.toResponse(repo.save(c));
    }

    @Transactional
    public ReconocimientoResponse actualizar(Long id, ReconocimientoRequest r) {
        Reconocimiento c = buscar(id);
        validarTrayectoria(r.idTrayectoria(), r.idPersona());
        LocalDateTime fechaAnterior = c.getFechaRegistro();
        mapper.update(c, r);
        if (c.getFechaRegistro() == null) {
            c.setFechaRegistro(fechaAnterior == null ? LocalDateTime.now() : fechaAnterior);
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

    private Reconocimiento buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reconocimiento no encontrado: " + id));
    }
}
