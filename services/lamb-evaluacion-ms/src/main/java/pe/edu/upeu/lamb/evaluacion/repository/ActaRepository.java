package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.Acta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActaRepository extends JpaRepository<Acta, Long> {
    Optional<Acta> findByIdSeccion(Long idSeccion);
}
