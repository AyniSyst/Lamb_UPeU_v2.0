package pe.edu.upeu.lamb.trayectoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.trayectoria.entity.ProgramaTutoria;

import java.util.List;
import java.util.Optional;

public interface ProgramaTutoriaRepository extends JpaRepository<ProgramaTutoria, Long> {
    List<ProgramaTutoria> findByActivoTrue();
    Optional<ProgramaTutoria> findByCodigo(String codigo);
    boolean existsByCodigo(String codigo);
    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
