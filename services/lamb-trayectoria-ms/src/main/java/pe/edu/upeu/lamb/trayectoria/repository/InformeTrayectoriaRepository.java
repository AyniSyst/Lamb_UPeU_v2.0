package pe.edu.upeu.lamb.trayectoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.trayectoria.entity.InformeTrayectoria;

import java.util.List;

public interface InformeTrayectoriaRepository extends JpaRepository<InformeTrayectoria, Long> {
    List<InformeTrayectoria> findByIdPersonaOrderByFechaGeneracionDesc(Long idPersona);
    List<InformeTrayectoria> findByIdTrayectoriaOrderByFechaGeneracionDesc(Long idTrayectoria);
}
