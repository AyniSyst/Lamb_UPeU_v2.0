package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lms.entity.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
}
