package pe.edu.upeu.lamb.trayectoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.trayectoria.entity.Egreso;

import java.util.List;
import java.util.Optional;

public interface EgresoRepository extends JpaRepository<Egreso, Long> {
    Optional<Egreso> findByIdTrayectoria(Long idTrayectoria);
    List<Egreso> findByIdPersona(Long idPersona);
    boolean existsByIdTrayectoria(Long idTrayectoria);
}
