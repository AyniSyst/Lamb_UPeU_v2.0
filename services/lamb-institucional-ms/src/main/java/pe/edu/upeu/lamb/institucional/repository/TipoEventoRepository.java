package pe.edu.upeu.lamb.institucional.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.institucional.entity.TipoEvento;

public interface TipoEventoRepository extends JpaRepository<TipoEvento, Long> {
}
