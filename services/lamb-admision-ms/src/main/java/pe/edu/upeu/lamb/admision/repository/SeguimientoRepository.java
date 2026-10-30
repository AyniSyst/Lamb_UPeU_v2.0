package pe.edu.upeu.lamb.admision.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.admision.entity.SeguimientoPostulante;

import java.util.List;

public interface SeguimientoRepository extends JpaRepository<SeguimientoPostulante, Long> {
    List<SeguimientoPostulante> findByPostulanteId(Long idPostulante);
}
