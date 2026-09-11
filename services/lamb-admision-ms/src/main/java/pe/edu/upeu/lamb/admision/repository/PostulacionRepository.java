package pe.edu.upeu.lamb.admision.repository;

import pe.edu.upeu.lamb.admision.entity.Postulacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostulacionRepository extends JpaRepository<Postulacion, Long> {
    List<Postulacion> findByIdPersona(Long idPersona);
}
