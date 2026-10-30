package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.entity.Ambiente;
import pe.edu.upeu.lamb.planificacion.entity.DiaSemana;
import pe.edu.upeu.lamb.planificacion.entity.Grupo;
import pe.edu.upeu.lamb.planificacion.entity.HorarioGrupo;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.repository.AmbienteRepository;
import pe.edu.upeu.lamb.planificacion.repository.AsignacionDocenteRepository;
import pe.edu.upeu.lamb.planificacion.repository.CargaLectivaRepository;
import pe.edu.upeu.lamb.planificacion.repository.HorarioGrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

/**
 * Reglas de negocio de la operacion transaccional (apertura y cambios de un grupo).
 * MANDATORY: solo se puede usar dentro de una transaccion ya abierta, porque los candados
 * (FOR UPDATE y pg_advisory_xact_lock) duran hasta el commit o rollback de quien llama.
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
public class ReglasGrupoService {

    // Para consultas que deben excluir "nada" (grupo u horario todavia sin id)
    static final Long SIN_ID = 0L;

    private final AmbienteRepository ambienteRepository;
    private final HorarioGrupoRepository horarioGrupoRepository;
    private final AsignacionDocenteRepository asignacionDocenteRepository;
    private final CargaLectivaRepository cargaLectivaRepository;

    /**
     * Bloquea la fila del ambiente (SELECT ... FOR UPDATE): otra transaccion que quiera reservar
     * el mismo ambiente espera aqui hasta que esta termine, y luego ve el horario ya guardado.
     */
    public Ambiente reservarAmbiente(Long idAmbiente, int cupoMaximo) {
        Ambiente ambiente = ambienteRepository.findByIdParaReserva(idAmbiente)
                .orElseThrow(() -> new ResourceNotFoundException("Ambiente no encontrado: " + idAmbiente));
        if (!Boolean.TRUE.equals(ambiente.getActivo())) {
            throw new BusinessRuleException("El ambiente " + ambiente.getCodigo() + " no esta activo");
        }
        if (ambiente.getAforo() < cupoMaximo) {
            throw new BusinessRuleException("El ambiente " + ambiente.getCodigo() + " tiene aforo " + ambiente.getAforo()
                    + " y no alcanza para un cupo de " + cupoMaximo);
        }
        return ambiente;
    }

    // Serializa las operaciones que tocan al mismo docente (no hay fila de docente que bloquear)
    public void bloquearDocente(Long idDocente) {
        asignacionDocenteRepository.bloquearDocente(idDocente);
    }

    public void validarRango(LocalTime horaInicio, LocalTime horaFin) {
        if (!horaFin.isAfter(horaInicio)) {
            throw new BusinessRuleException("La hora de fin (" + horaFin + ") debe ser posterior a la de inicio (" + horaInicio + ")");
        }
    }

    public void validarAmbienteLibre(Ambiente ambiente, Long idPeriodo, DiaSemana dia,
                                     LocalTime horaInicio, LocalTime horaFin, Long idHorarioExcluido) {
        List<HorarioGrupo> cruces = horarioGrupoRepository.findCrucesDeAmbiente(
                ambiente.getId(), idPeriodo, dia, horaInicio, horaFin, idHorarioExcluido);
        if (!cruces.isEmpty()) {
            HorarioGrupo cruce = cruces.get(0);
            throw new BusinessRuleException("El ambiente " + ambiente.getCodigo() + " ya esta ocupado el " + dia + " de "
                    + cruce.getHoraInicio() + " a " + cruce.getHoraFin() + " por el grupo " + cruce.getGrupo().getId());
        }
    }

    // Dentro del mismo grupo tampoco puede haber dos horarios que se pisen (el docente estaria en dos aulas)
    public void validarSinCruceEnGrupo(Grupo grupo, DiaSemana dia, LocalTime horaInicio, LocalTime horaFin, Long idHorarioExcluido) {
        boolean cruza = grupo.getHorarios().stream()
                .filter(h -> Boolean.TRUE.equals(h.getActivo()) && !idHorarioExcluido.equals(h.getId()))
                .anyMatch(h -> h.getDiaSemana() == dia && h.getHoraInicio().isBefore(horaFin) && h.getHoraFin().isAfter(horaInicio));
        if (cruza) {
            throw new BusinessRuleException("El grupo ya tiene otro horario el " + dia + " que se cruza con " + horaInicio + "-" + horaFin);
        }
    }

    public void validarDocenteLibre(Long idDocente, Long idPeriodo, Long idGrupo, DiaSemana dia,
                                    LocalTime horaInicio, LocalTime horaFin) {
        List<HorarioGrupo> cruces = horarioGrupoRepository.findCrucesDeDocente(
                idDocente, idPeriodo, dia, horaInicio, horaFin, idGrupo);
        if (!cruces.isEmpty()) {
            HorarioGrupo cruce = cruces.get(0);
            throw new BusinessRuleException("El docente " + idDocente + " ya dicta el " + dia + " de " + cruce.getHoraInicio()
                    + " a " + cruce.getHoraFin() + " en el grupo " + cruce.getGrupo().getId());
        }
    }

    // Si el docente tiene carga lectiva registrada en el periodo, no se puede pasar de ese tope
    public void validarCargaLectiva(Long idDocente, Long idPeriodo, int horasNuevas, Long idAsignacionExcluida) {
        cargaLectivaRepository.findByIdDocenteAndPeriodoId(idDocente, idPeriodo).ifPresent(carga -> {
            long asignadas = asignacionDocenteRepository.sumarHorasDelPeriodo(idDocente, idPeriodo, idAsignacionExcluida);
            if (asignadas + horasNuevas > carga.getHorasMaximas()) {
                throw new BusinessRuleException("El docente " + idDocente + " ya tiene " + asignadas + " h asignadas; con "
                        + horasNuevas + " h mas supera su carga lectiva maxima de " + carga.getHorasMaximas() + " h");
            }
        });
    }
}
