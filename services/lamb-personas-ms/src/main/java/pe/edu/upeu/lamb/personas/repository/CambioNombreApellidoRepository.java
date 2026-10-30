package pe.edu.upeu.lamb.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.personas.entity.CambioNombreApellido;

public interface CambioNombreApellidoRepository extends JpaRepository<CambioNombreApellido, Long> {
}
