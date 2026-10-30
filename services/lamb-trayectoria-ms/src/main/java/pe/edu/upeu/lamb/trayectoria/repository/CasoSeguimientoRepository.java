package pe.edu.upeu.lamb.trayectoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.trayectoria.entity.CasoSeguimiento;

import java.util.List;

public interface CasoSeguimientoRepository extends JpaRepository<CasoSeguimiento, Long> {
    List<CasoSeguimiento> findByIdPersona(Long idPersona);
    List<CasoSeguimiento> findByIdAlerta(Long idAlerta);
    List<CasoSeguimiento> findByEstado(String estado);
}
