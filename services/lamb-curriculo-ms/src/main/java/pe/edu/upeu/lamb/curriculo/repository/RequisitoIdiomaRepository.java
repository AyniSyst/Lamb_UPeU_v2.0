package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.RequisitoIdioma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequisitoIdiomaRepository extends JpaRepository<RequisitoIdioma, Long> {

    List<RequisitoIdioma> findByPlanId(Long idPlan);

    boolean existsByPlanIdAndIdioma(Long idPlan, String idioma);

    boolean existsByPlanIdAndIdiomaAndIdNot(Long idPlan, String idioma, Long id);
}
