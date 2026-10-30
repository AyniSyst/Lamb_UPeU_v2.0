package pe.edu.upeu.lamb.trayectoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.trayectoria.entity.CumplimientoRequisito;

import java.util.List;

public interface CumplimientoRequisitoRepository extends JpaRepository<CumplimientoRequisito, Long> {
    List<CumplimientoRequisito> findByIdTrayectoria(Long idTrayectoria);
    List<CumplimientoRequisito> findByIdPersona(Long idPersona);
    boolean existsByIdTrayectoriaAndCumplidoFalse(Long idTrayectoria);
}
