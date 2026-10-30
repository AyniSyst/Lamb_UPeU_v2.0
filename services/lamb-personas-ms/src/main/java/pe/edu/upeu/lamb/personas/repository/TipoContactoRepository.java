package pe.edu.upeu.lamb.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.personas.entity.TipoContacto;

public interface TipoContactoRepository extends JpaRepository<TipoContacto, Long> {
}
