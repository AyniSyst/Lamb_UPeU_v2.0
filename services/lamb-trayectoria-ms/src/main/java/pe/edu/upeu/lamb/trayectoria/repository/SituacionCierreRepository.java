package pe.edu.upeu.lamb.trayectoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.trayectoria.entity.SituacionCierre;

import java.util.List;
import java.util.Optional;

public interface SituacionCierreRepository extends JpaRepository<SituacionCierre, Long> {
    List<SituacionCierre> findByIdTrayectoria(Long idTrayectoria);
    List<SituacionCierre> findByIdPersona(Long idPersona);
    Optional<SituacionCierre> findByIdTrayectoriaAndPeriodo(Long idTrayectoria, String periodo);
}
