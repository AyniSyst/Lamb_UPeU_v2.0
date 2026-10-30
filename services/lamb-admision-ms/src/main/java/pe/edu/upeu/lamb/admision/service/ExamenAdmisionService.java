package pe.edu.upeu.lamb.admision.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.admision.dto.ExamenAdmisionRequest;
import pe.edu.upeu.lamb.admision.dto.ExamenAdmisionResponse;
import pe.edu.upeu.lamb.admision.entity.*;
import pe.edu.upeu.lamb.admision.exception.BusinessRuleException;
import pe.edu.upeu.lamb.admision.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.admision.mapper.ExamenAdmisionMapper;
import pe.edu.upeu.lamb.admision.repository.ConvocatoriaRepository;
import pe.edu.upeu.lamb.admision.repository.ExamenAdmisionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamenAdmisionService {

    private final ExamenAdmisionRepository repo;
    private final ConvocatoriaRepository convocatoriaRepo;
    private final ExamenAdmisionMapper mapper;

    @Transactional(readOnly = true)
    public List<ExamenAdmisionResponse> listar(Long idConvocatoria) {
        List<ExamenAdmision> data = idConvocatoria != null
                ? repo.findByConvocatoriaId(idConvocatoria)
                : repo.findAll();
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ExamenAdmisionResponse obtener(Long id) {
        return mapper.toResponse(buscar(id));
    }

    @Transactional
    public ExamenAdmisionResponse crear(ExamenAdmisionRequest r) {
        if (repo.existsByCodigo(r.getCodigo())) {
            throw new BusinessRuleException("Ya existe un examen con codigo " + r.getCodigo());
        }
        Convocatoria conv = convocatoriaRepo.findById(r.getIdConvocatoria())
                .orElseThrow(() -> new ResourceNotFoundException("Convocatoria no encontrada: " + r.getIdConvocatoria()));

        ExamenAdmision e = ExamenAdmision.builder()
                .convocatoria(conv)
                .codigo(r.getCodigo())
                .nombre(r.getNombre())
                .fechaExamen(r.getFechaExamen())
                .duracionMinutos(r.getDuracionMinutos())
                .idAula(r.getIdAula())
                .aforoMaximo(r.getAforoMaximo())
                .aforoAsignado(0)
                .estado(EstadoExamen.PROGRAMADO)
                .build();
        return mapper.toResponse(repo.save(e));
    }

    @Transactional
    public ExamenAdmisionResponse actualizar(Long id, ExamenAdmisionRequest r) {
        ExamenAdmision e = buscar(id);
        if (repo.existsByCodigoAndIdNot(r.getCodigo(), id)) {
            throw new BusinessRuleException("Ya existe otro examen con codigo " + r.getCodigo());
        }
        if (r.getAforoMaximo() < e.getAforoAsignado()) {
            throw new BusinessRuleException("El aforo maximo no puede ser menor al aforo asignado (" + e.getAforoAsignado() + ")");
        }
        e.setCodigo(r.getCodigo());
        e.setNombre(r.getNombre());
        e.setFechaExamen(r.getFechaExamen());
        e.setDuracionMinutos(r.getDuracionMinutos());
        e.setIdAula(r.getIdAula());
        e.setAforoMaximo(r.getAforoMaximo());
        return mapper.toResponse(e);
    }

    @Transactional
    public void eliminar(Long id) {
        ExamenAdmision e = buscar(id);
        if (e.getEstado() != EstadoExamen.PROGRAMADO) {
            throw new BusinessRuleException("Solo se puede eliminar un examen PROGRAMADO");
        }
        repo.delete(e);
    }

    private ExamenAdmision buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Examen no encontrado: " + id));
    }
}
