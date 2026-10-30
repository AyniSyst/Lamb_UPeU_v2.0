package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.CalendarioAcademico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarioAcademicoRepository extends JpaRepository<CalendarioAcademico, Long> {

    List<CalendarioAcademico> findByPeriodoId(Long idPeriodo);

    boolean existsByPeriodoIdAndIdSede(Long idPeriodo, Long idSede);

    boolean existsByPeriodoIdAndIdSedeAndIdNot(Long idPeriodo, Long idSede, Long id);
}
