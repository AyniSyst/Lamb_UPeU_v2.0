package pe.edu.upeu.lamb.trayectoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.trayectoria.entity.NivelRiesgo;

import java.util.List;
import java.util.Optional;

public interface NivelRiesgoRepository extends JpaRepository<NivelRiesgo, Long> {
    List<NivelRiesgo> findByActivoTrue();
    Optional<NivelRiesgo> findByCodigo(String codigo);
    boolean existsByCodigo(String codigo);
    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
