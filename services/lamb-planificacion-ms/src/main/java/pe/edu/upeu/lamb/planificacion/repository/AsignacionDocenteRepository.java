package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.AsignacionDocente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsignacionDocenteRepository extends JpaRepository<AsignacionDocente, Long> {

    List<AsignacionDocente> findByGrupoId(Long idGrupo);

    // Horas lectivas que el docente ya tiene en el periodo (los grupos anulados no cuentan)
    @Query("SELECT COALESCE(SUM(a.horasSemanales), 0) FROM AsignacionDocente a WHERE a.idDocente = :idDocente "
            + "AND a.grupo.oferta.periodo.id = :idPeriodo "
            + "AND a.grupo.estado <> pe.edu.upeu.lamb.planificacion.entity.EstadoGrupo.ANULADO AND a.id <> :idExcluido")
    long sumarHorasDelPeriodo(@Param("idDocente") Long idDocente,
                              @Param("idPeriodo") Long idPeriodo,
                              @Param("idExcluido") Long idExcluido);

    /**
     * Candado transaccional de PostgreSQL por docente (se libera solo al commit o rollback).
     * Serializa dos aperturas simultaneas que asignan al mismo docente: no hay tabla de docentes que bloquear.
     */
    @Query(value = "SELECT 1 FROM pg_advisory_xact_lock(1, CAST(:idDocente AS INTEGER))", nativeQuery = true)
    Integer bloquearDocente(@Param("idDocente") Long idDocente);
}
