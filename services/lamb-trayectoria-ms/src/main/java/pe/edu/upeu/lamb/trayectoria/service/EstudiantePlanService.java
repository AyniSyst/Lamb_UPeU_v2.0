package pe.edu.upeu.lamb.trayectoria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.trayectoria.dto.CambioAcademicoRequest;
import pe.edu.upeu.lamb.trayectoria.dto.CambioAcademicoResponse;
import pe.edu.upeu.lamb.trayectoria.entity.CambioAcademico;
import pe.edu.upeu.lamb.trayectoria.entity.TipoCambioAcademico;
import pe.edu.upeu.lamb.trayectoria.entity.Trayectoria;
import pe.edu.upeu.lamb.trayectoria.exception.BusinessRuleException;
import pe.edu.upeu.lamb.trayectoria.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.trayectoria.mapper.EstudiantePlanMapper;
import pe.edu.upeu.lamb.trayectoria.repository.CambioAcademicoRepository;
import pe.edu.upeu.lamb.trayectoria.repository.TrayectoriaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudiantePlanService {

    private final CambioAcademicoRepository repo;
    private final TrayectoriaRepository trayectoriaRepository;
    private final EstudiantePlanMapper mapper;

    @Transactional(readOnly = true)
    public List<CambioAcademicoResponse> listar(Long idPersona) {
        List<CambioAcademico> data = idPersona != null
                ? repo.findByIdPersonaOrderByFechaCambioDesc(idPersona)
                : repo.findAll();
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CambioAcademicoResponse obtener(Long id) {
        return mapper.toResponse(buscar(id));
    }

    @Transactional
    public CambioAcademicoResponse crear(CambioAcademicoRequest r) {
        Trayectoria trayectoria = validarTrayectoria(r);
        CambioAcademico cambio = mapper.toEntity(r);
        aplicarCambio(trayectoria, r);
        return mapper.toResponse(repo.save(cambio));
    }

    @Transactional
    public CambioAcademicoResponse actualizar(Long id, CambioAcademicoRequest r) {
        CambioAcademico cambio = buscar(id);
        Trayectoria trayectoria = validarTrayectoria(r);
        mapper.update(cambio, r);
        aplicarCambio(trayectoria, r);
        return mapper.toResponse(cambio);
    }

    @Transactional
    public void eliminar(Long id) {
        repo.delete(buscar(id));
    }

    private Trayectoria validarTrayectoria(CambioAcademicoRequest r) {
        Trayectoria trayectoria = trayectoriaRepository.findById(r.idTrayectoria())
                .orElseThrow(() -> new ResourceNotFoundException("Trayectoria no encontrada: " + r.idTrayectoria()));
        if (!trayectoria.getIdPersona().equals(r.idPersona())) {
            throw new BusinessRuleException("La trayectoria no pertenece a la persona indicada");
        }
        return trayectoria;
    }

    private void aplicarCambio(Trayectoria trayectoria, CambioAcademicoRequest r) {
        trayectoria.setEstado(r.estadoNuevo());
        if (r.tipoCambio() == TipoCambioAcademico.CAMBIO_PLAN && r.idPlanDestino() != null) {
            trayectoria.setIdPlan(r.idPlanDestino());
        }
        if (r.tipoCambio() == TipoCambioAcademico.TRASLADO_INTERNO) {
            if (r.idEscuelaDestino() != null) {
                trayectoria.setIdEscuela(r.idEscuelaDestino());
            }
            if (r.idSedeDestino() != null) {
                trayectoria.setIdSede(r.idSedeDestino());
            }
        }
    }

    private CambioAcademico buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cambio academico no encontrado: " + id));
    }
}
