package pe.edu.upeu.lamb.admision.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.admision.entity.Convocatoria;

import java.util.List;
import java.util.Optional;

public interface ConvocatoriaRepository extends JpaRepository<Convocatoria, Long> {
    Optional<Convocatoria> findByCodigo(String codigo);
    List<Convocatoria> findByPeriodo(String periodo);
    boolean existsByCodigo(String codigo);
    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
