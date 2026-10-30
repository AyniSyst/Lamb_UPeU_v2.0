package pe.edu.upeu.lamb.institucional.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.institucional.entity.EventoCalendario;

public interface EventoCalendarioRepository extends JpaRepository<EventoCalendario, Long> {
}
