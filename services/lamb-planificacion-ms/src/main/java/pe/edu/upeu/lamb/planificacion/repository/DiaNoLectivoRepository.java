package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.DiaNoLectivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DiaNoLectivoRepository extends JpaRepository<DiaNoLectivo, Long> {

    List<DiaNoLectivo> findByCalendarioId(Long idCalendario);

    boolean existsByCalendarioIdAndFecha(Long idCalendario, LocalDate fecha);

    boolean existsByCalendarioIdAndFechaAndIdNot(Long idCalendario, LocalDate fecha, Long id);
}
