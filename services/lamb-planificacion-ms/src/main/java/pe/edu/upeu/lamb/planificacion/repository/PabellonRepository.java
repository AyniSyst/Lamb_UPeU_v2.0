package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.Pabellon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PabellonRepository extends JpaRepository<Pabellon, Long> {

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
