package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.CargaLectiva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CargaLectivaRepository extends JpaRepository<CargaLectiva, Long> {

    List<CargaLectiva> findByPeriodoId(Long idPeriodo);

    boolean existsByIdDocenteAndPeriodoId(Long idDocente, Long idPeriodo);

    boolean existsByIdDocenteAndPeriodoIdAndIdNot(Long idDocente, Long idPeriodo, Long id);

    Optional<CargaLectiva> findByIdDocenteAndPeriodoId(Long idDocente, Long idPeriodo);
}
