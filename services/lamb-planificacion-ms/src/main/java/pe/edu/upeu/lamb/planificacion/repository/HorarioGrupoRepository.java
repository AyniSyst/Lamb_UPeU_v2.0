package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.DiaSemana;
import pe.edu.upeu.lamb.planificacion.entity.HorarioGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public interface HorarioGrupoRepository extends JpaRepository<HorarioGrupo, Long> {

    List<HorarioGrupo> findByGrupoId(Long idGrupo);

    @Query("SELECT h FROM HorarioGrupo h JOIN FETCH h.grupo g JOIN FETCH h.ambiente "
            + "WHERE g.id IN :idsGrupo AND h.activo = true ORDER BY h.diaSemana, h.horaInicio")
    List<HorarioGrupo> findActivosDeGrupos(@Param("idsGrupo") Collection<Long> idsGrupo);

    // Dos rangos [inicio, fin) se cruzan si cada uno empieza antes de que termine el otro
    @Query("SELECT h FROM HorarioGrupo h WHERE h.ambiente.id = :idAmbiente AND h.idPeriodo = :idPeriodo "
            + "AND h.diaSemana = :dia AND h.activo = true AND h.horaInicio < :horaFin AND h.horaFin > :horaInicio "
            + "AND h.id <> :idExcluido")
    List<HorarioGrupo> findCrucesDeAmbiente(@Param("idAmbiente") Long idAmbiente,
                                            @Param("idPeriodo") Long idPeriodo,
                                            @Param("dia") DiaSemana dia,
                                            @Param("horaInicio") LocalTime horaInicio,
                                            @Param("horaFin") LocalTime horaFin,
                                            @Param("idExcluido") Long idExcluido);

    // Horarios de OTROS grupos del periodo donde el docente ya dicta y que se cruzan con el rango
    @Query("SELECT h FROM HorarioGrupo h, AsignacionDocente a WHERE a.grupo = h.grupo AND a.idDocente = :idDocente "
            + "AND h.idPeriodo = :idPeriodo AND h.diaSemana = :dia AND h.activo = true "
            + "AND h.horaInicio < :horaFin AND h.horaFin > :horaInicio AND h.grupo.id <> :idGrupo")
    List<HorarioGrupo> findCrucesDeDocente(@Param("idDocente") Long idDocente,
                                           @Param("idPeriodo") Long idPeriodo,
                                           @Param("dia") DiaSemana dia,
                                           @Param("horaInicio") LocalTime horaInicio,
                                           @Param("horaFin") LocalTime horaFin,
                                           @Param("idGrupo") Long idGrupo);
}
