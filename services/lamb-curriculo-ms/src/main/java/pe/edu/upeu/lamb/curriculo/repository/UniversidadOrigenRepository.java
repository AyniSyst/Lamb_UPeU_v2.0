package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.UniversidadOrigen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversidadOrigenRepository extends JpaRepository<UniversidadOrigen, Long> {

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
