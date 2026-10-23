package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.Prerrequisito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrerrequisitoRepository extends JpaRepository<Prerrequisito, Long> {
    boolean existsByRequisitoId(Long idPlanCursoRequisito);
}
