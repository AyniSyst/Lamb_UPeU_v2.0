package pe.edu.upeu.lamb.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.personas.entity.Nacionalidad;

public interface NacionalidadRepository extends JpaRepository<Nacionalidad, Long> {
}
