package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.DisponibilidadDocente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisponibilidadDocenteRepository extends JpaRepository<DisponibilidadDocente, Long> {

    List<DisponibilidadDocente> findByPeriodoId(Long idPeriodo);
}
