package pe.edu.upeu.lamb.trayectoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.trayectoria.entity.SolicitudConstancia;

import java.util.List;

public interface SolicitudConstanciaRepository extends JpaRepository<SolicitudConstancia, Long> {
    List<SolicitudConstancia> findByIdPersonaOrderByFechaSolicitudDesc(Long idPersona);
    List<SolicitudConstancia> findByEstado(String estado);
}
