package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.CargaNoLectiva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CargaNoLectivaRepository extends JpaRepository<CargaNoLectiva, Long> {

    List<CargaNoLectiva> findByPeriodoId(Long idPeriodo);
}
