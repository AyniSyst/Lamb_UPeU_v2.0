package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.EstadoPlan;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanEstudioRepository extends JpaRepository<PlanEstudio, Long> {
    List<PlanEstudio> findByIdPrograma(Long idPrograma);

    Optional<PlanEstudio> findByIdProgramaAndEstado(Long idPrograma, EstadoPlan estado);

    boolean existsByIdProgramaAndVersion(Long idPrograma, String version);
}
