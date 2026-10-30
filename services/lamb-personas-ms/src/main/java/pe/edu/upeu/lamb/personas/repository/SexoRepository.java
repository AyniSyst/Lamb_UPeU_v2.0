package pe.edu.upeu.lamb.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.personas.entity.Sexo;

public interface SexoRepository extends JpaRepository<Sexo, Long> {
}
