package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.Mencion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MencionRepository extends JpaRepository<Mencion, Long> {

    List<Mencion> findByPlanId(Long idPlan);

    boolean existsByPlanIdAndNombre(Long idPlan, String nombre);

    boolean existsByPlanIdAndNombreAndIdNot(Long idPlan, String nombre, Long id);
}
