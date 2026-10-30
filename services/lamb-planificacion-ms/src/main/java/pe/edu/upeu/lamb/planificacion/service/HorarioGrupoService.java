package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.HorarioGrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.HorarioGrupoResponse;
import pe.edu.upeu.lamb.planificacion.entity.Ambiente;
import pe.edu.upeu.lamb.planificacion.entity.AsignacionDocente;
import pe.edu.upeu.lamb.planificacion.entity.DiaSemana;
import pe.edu.upeu.lamb.planificacion.entity.Grupo;
import pe.edu.upeu.lamb.planificacion.entity.HorarioGrupo;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.HorarioGrupoMapper;
import pe.edu.upeu.lamb.planificacion.repository.HorarioGrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

/**
 * CRUD 17 · Horario de grupo (detalle Grupo x Ambiente) fuera de la apertura.
 * Aplica las mismas reglas y candados que la apertura del grupo.
 */
@Service
@RequiredArgsConstructor
public class HorarioGrupoService {

    private final HorarioGrupoRepository horarioGrupoRepository;
    private final HorarioGrupoMapper horarioGrupoMapper;
    private final GrupoService grupoService;
    private final ReglasGrupoService reglas;

    @Transactional(readOnly = true)
    public List<HorarioGrupoResponse> listar(Long idGrupo) {
        List<HorarioGrupo> lista = idGrupo == null
                ? horarioGrupoRepository.findAll()
                : horarioGrupoRepository.findByGrupoId(idGrupo);
        return lista.stream().map(horarioGrupoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public HorarioGrupoResponse obtener(Long id) {
        return horarioGrupoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public HorarioGrupoResponse crear(HorarioGrupoRequest request) {
        Grupo grupo = grupoService.buscarOFallar(request.getIdGrupo());
        grupoService.validarNoAnulado(grupo);
        HorarioGrupo horario = HorarioGrupo.builder()
                .grupo(grupo)
                .idPeriodo(grupo.getPeriodo().getId())
                .activo(true)
                .build();
        programar(horario, request.getIdAmbiente(), request.getDiaSemana(), request.getHoraInicio(), request.getHoraFin());
        grupo.getHorarios().add(horario);
        return horarioGrupoMapper.toResponse(horarioGrupoRepository.saveAndFlush(horario));
    }

    @Transactional
    public HorarioGrupoResponse actualizar(Long id, HorarioGrupoRequest request) {
        HorarioGrupo horario = buscarOFallar(id);
        if (!horario.getGrupo().getId().equals(request.getIdGrupo())) {
            throw new BusinessRuleException("Un horario no se mueve a otro grupo: eliminalo y crealo en el grupo destino");
        }
        reprogramar(horario, request.getIdAmbiente(), request.getDiaSemana(), request.getHoraInicio(), request.getHoraFin());
        return horarioGrupoMapper.toResponse(horario);
    }

    @Transactional
    public void eliminar(Long id) {
        HorarioGrupo horario = buscarOFallar(id);
        Grupo grupo = horario.getGrupo();
        grupoService.validarNoAnulado(grupo);
        long activos = grupo.getHorarios().stream().filter(h -> Boolean.TRUE.equals(h.getActivo())).count();
        if (activos <= 1) {
            throw new BusinessRuleException("El grupo " + grupo.getId() + " debe conservar al menos un horario");
        }
        grupo.getHorarios().remove(horario);
    }

    /**
     * Cambia ambiente, dia u horas de un horario existente. Lo usan el PUT y la aprobacion
     * de las solicitudes de cambio de horario (CRUD 31) y de cambio de aula (CRUD 32).
     */
    @Transactional
    public void reprogramar(HorarioGrupo horario, Long idAmbiente, DiaSemana dia, LocalTime horaInicio, LocalTime horaFin) {
        grupoService.validarNoAnulado(horario.getGrupo());
        programar(horario, idAmbiente, dia, horaInicio, horaFin);
        horarioGrupoRepository.saveAndFlush(horario);
    }

    HorarioGrupo buscarOFallar(Long id) {
        return horarioGrupoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horario de grupo no encontrado: " + id));
    }

    private void programar(HorarioGrupo horario, Long idAmbiente, DiaSemana dia, LocalTime horaInicio, LocalTime horaFin) {
        Grupo grupo = horario.getGrupo();
        Long idExcluido = horario.getId() == null ? ReglasGrupoService.SIN_ID : horario.getId();
        reglas.validarRango(horaInicio, horaFin);
        Ambiente ambiente = reglas.reservarAmbiente(idAmbiente, grupo.getCupoMaximo());
        grupo.getDocentes().stream().map(AsignacionDocente::getIdDocente).sorted().forEach(reglas::bloquearDocente);

        reglas.validarSinCruceEnGrupo(grupo, dia, horaInicio, horaFin, idExcluido);
        reglas.validarAmbienteLibre(ambiente, horario.getIdPeriodo(), dia, horaInicio, horaFin, idExcluido);
        for (AsignacionDocente docente : grupo.getDocentes()) {
            reglas.validarDocenteLibre(docente.getIdDocente(), horario.getIdPeriodo(), grupo.getId(), dia, horaInicio, horaFin);
        }
        horario.setAmbiente(ambiente);
        horario.setDiaSemana(dia);
        horario.setHoraInicio(horaInicio);
        horario.setHoraFin(horaFin);
    }
}
