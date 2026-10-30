package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.EncuestaDocente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EncuestaDocenteRepository extends JpaRepository<EncuestaDocente, Long> {

    List<EncuestaDocente> findByIdPeriodo(Long idPeriodo);
}
