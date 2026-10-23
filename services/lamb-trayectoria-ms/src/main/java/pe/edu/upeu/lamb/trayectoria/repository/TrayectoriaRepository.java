package pe.edu.upeu.lamb.trayectoria.repository;

import pe.edu.upeu.lamb.trayectoria.entity.Trayectoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrayectoriaRepository extends JpaRepository<Trayectoria, Long> {
    Optional<Trayectoria> findByIdPersona(Long idPersona);
}
