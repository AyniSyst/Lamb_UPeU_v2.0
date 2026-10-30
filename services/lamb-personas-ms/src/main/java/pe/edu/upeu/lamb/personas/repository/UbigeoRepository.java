package pe.edu.upeu.lamb.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.personas.entity.Ubigeo;

public interface UbigeoRepository extends JpaRepository<Ubigeo, Long> {
}
