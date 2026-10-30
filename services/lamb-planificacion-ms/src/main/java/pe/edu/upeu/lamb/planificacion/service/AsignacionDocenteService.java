package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.AsignacionDocenteRequest;
import pe.edu.upeu.lamb.planificacion.dto.AsignacionDocenteResponse;
import pe.edu.upeu.lamb.planificacion.entity.AsignacionDocente;
import pe.edu.upeu.lamb.planificacion.entity.Grupo;
import pe.edu.upeu.lamb.planificacion.entity.HorarioGrupo;
import pe.edu.upeu.lamb.planificacion.entity.RolDocente;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.AsignacionDocenteMapper;
import pe.edu.upeu.lamb.planificacion.repository.AsignacionDocenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CRUD 20 · Asignacion docente a grupo (detalle Grupo x Docente) fuera de la apertura.
 * Aplica las mismas reglas y candados que la apertura del grupo.
 */
@Service
@RequiredArgsConstructor
public class AsignacionDocenteService {

    private final AsignacionDocenteRepository asignacionDocenteRepository;
    private final AsignacionDocenteMapper asignacionDocenteMapper;
    private final GrupoService grupoService;
    private final ReglasGrupoService reglas;

    @Transactional(readOnly = true)
    public List<AsignacionDocenteResponse> listar(Long idGrupo) {
        List<AsignacionDocente> lista = idGrupo == null
                ? asignacionDocenteRepository.findAll()
                : asignacionDocenteRepository.findByGrupoId(idGrupo);
        return lista.stream().map(asignacionDocenteMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public AsignacionDocenteResponse obtener(Long id) {
        return asignacionDocenteMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public AsignacionDocenteResponse crear(AsignacionDocenteRequest request) {
        Grupo grupo = grupoService.buscarOFallar(request.getIdGrupo());
        AsignacionDocente asignacion = AsignacionDocente.builder().grupo(grupo).build();
        asignar(asignacion, request.getIdDocente(), request.getRol(), request.getHorasSemanales());
        grupo.getDocentes().add(asignacion);
        return asignacionDocenteMapper.toResponse(asignacionDocenteRepository.saveAndFlush(asignacion));
    }

    @Transactional
    public AsignacionDocenteResponse actualizar(Long id, AsignacionDocenteRequest request) {
        AsignacionDocente asignacion = buscarOFallar(id);
        if (!asignacion.getGrupo().getId().equals(request.getIdGrupo())) {
            throw new BusinessRuleException("Una asignacion no se mueve a otro grupo: eliminala y creala en el grupo destino");
        }
        reasignar(asignacion, request.getIdDocente(), request.getRol(), request.getHorasSemanales());
        return asignacionDocenteMapper.toResponse(asignacion);
    }

    @Transactional
    public void eliminar(Long id) {
        AsignacionDocente asignacion = buscarOFallar(id);
        grupoService.validarNoAnulado(asignacion.getGrupo());
        asignacion.getGrupo().getDocentes().remove(asignacion);
    }

    // Lo usan el PUT y la aprobacion de la solicitud de cambio de docente (CRUD 30)
    @Transactional
    public void reasignar(AsignacionDocente asignacion, Long idDocente, RolDocente rol, int horasSemanales) {
        asignar(asignacion, idDocente, rol, horasSemanales);
        asignacionDocenteRepository.saveAndFlush(asignacion);
    }

    AsignacionDocente buscarOFallar(Long id) {
        return asignacionDocenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asignacion docente no encontrada: " + id));
    }

    private void asignar(AsignacionDocente asignacion, Long idDocente, RolDocente rol, int horasSemanales) {
        Grupo grupo = asignacion.getGrupo();
        grupoService.validarNoAnulado(grupo);
        Long idExcluido = asignacion.getId() == null ? ReglasGrupoService.SIN_ID : asignacion.getId();
        Long idPeriodo = grupo.getPeriodo().getId();
        reglas.bloquearDocente(idDocente);

        for (AsignacionDocente otra : grupo.getDocentes()) {
            if (idExcluido.equals(otra.getId())) {
                continue;
            }
            if (otra.getIdDocente().equals(idDocente)) {
                throw new BusinessRuleException("El docente " + idDocente + " ya esta asignado al grupo " + grupo.getId());
            }
            if (rol == RolDocente.TITULAR && otra.getRol() == RolDocente.TITULAR) {
                throw new BusinessRuleException("El grupo " + grupo.getId() + " ya tiene un docente TITULAR");
            }
        }
        for (HorarioGrupo h : grupo.getHorarios()) {
            if (Boolean.TRUE.equals(h.getActivo())) {
                reglas.validarDocenteLibre(idDocente, idPeriodo, grupo.getId(), h.getDiaSemana(), h.getHoraInicio(), h.getHoraFin());
            }
        }
        reglas.validarCargaLectiva(idDocente, idPeriodo, horasSemanales, idExcluido);
        asignacion.setIdDocente(idDocente);
        asignacion.setRol(rol);
        asignacion.setHorasSemanales(horasSemanales);
    }
}
