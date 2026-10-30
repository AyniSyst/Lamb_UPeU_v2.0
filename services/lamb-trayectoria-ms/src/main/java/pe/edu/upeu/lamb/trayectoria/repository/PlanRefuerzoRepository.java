package pe.edu.upeu.lamb.trayectoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.trayectoria.entity.PlanRefuerzo;

import java.util.List;

public interface PlanRefuerzoRepository extends JpaRepository<PlanRefuerzo, Long> {
    List<PlanRefuerzo> findByIdPersona(Long idPersona);
    List<PlanRefuerzo> findByIdTrayectoria(Long idTrayectoria);
    List<PlanRefuerzo> findByPeriodo(String periodo);
    boolean existsByIdTrayectoriaAndPeriodoAndEstadoAndIdNot(Long idTrayectoria, String periodo, String estado, Long id);
}
