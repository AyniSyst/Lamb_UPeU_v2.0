package pe.edu.upeu.lamb.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.personas.entity.Contacto;

public interface ContactoRepository extends JpaRepository<Contacto, Long> {
}
