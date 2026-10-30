package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.GrupoHabilitadoEquivalencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Lock;
import jakarta.persistence.LockModeType;

import java.util.List;
import java.util.Optional;

public interface GrupoHabilitadoEquivalenciaRepository extends JpaRepository<GrupoHabilitadoEquivalencia, Long> {

    List<GrupoHabilitadoEquivalencia> findByEquivalenciaId(Long idEquivalencia);

    boolean existsByEquivalenciaIdAndGrupoId(Long idEquivalencia, Long idGrupo);

    boolean existsByEquivalenciaIdAndGrupoIdAndIdNot(Long idEquivalencia, Long idGrupo, Long id);

    // FOR UPDATE: dos aprobaciones simultaneas no pueden gastar el mismo ultimo cupo
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT g FROM GrupoHabilitadoEquivalencia g WHERE g.id = :id")
    Optional<GrupoHabilitadoEquivalencia> findByIdParaActualizar(@Param("id") Long id);
}
