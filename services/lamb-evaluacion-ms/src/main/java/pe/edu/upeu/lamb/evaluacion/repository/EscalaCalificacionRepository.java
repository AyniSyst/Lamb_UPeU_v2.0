package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.EscalaCalificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EscalaCalificacionRepository extends JpaRepository<EscalaCalificacion, Long> {

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, Long id);

    Optional<EscalaCalificacion> findFirstByVigenteTrue();

    boolean existsByVigenteTrueAndIdNot(Long id);
}
