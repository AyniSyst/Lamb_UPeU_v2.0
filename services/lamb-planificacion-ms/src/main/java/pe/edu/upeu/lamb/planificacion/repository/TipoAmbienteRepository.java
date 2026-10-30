package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.TipoAmbiente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoAmbienteRepository extends JpaRepository<TipoAmbiente, Long> {

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
