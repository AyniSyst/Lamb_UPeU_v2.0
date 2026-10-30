package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.HitoCalendario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HitoCalendarioRepository extends JpaRepository<HitoCalendario, Long> {

    List<HitoCalendario> findByCalendarioId(Long idCalendario);
}
