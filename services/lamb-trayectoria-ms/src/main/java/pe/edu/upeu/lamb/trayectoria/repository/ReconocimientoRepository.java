package pe.edu.upeu.lamb.trayectoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.trayectoria.entity.Reconocimiento;

import java.util.List;

public interface ReconocimientoRepository extends JpaRepository<Reconocimiento, Long> {
    List<Reconocimiento> findByIdPersonaOrderByFechaRegistroDesc(Long idPersona);
    List<Reconocimiento> findByIdTrayectoriaOrderByFechaRegistroDesc(Long idTrayectoria);
}
