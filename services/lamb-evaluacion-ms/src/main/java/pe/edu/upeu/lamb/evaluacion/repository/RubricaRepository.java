package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.Rubrica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RubricaRepository extends JpaRepository<Rubrica, Long> {

    List<Rubrica> findByComponenteId(Long idComponente);
}
