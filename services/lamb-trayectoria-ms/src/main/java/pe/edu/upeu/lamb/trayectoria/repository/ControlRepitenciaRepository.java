package pe.edu.upeu.lamb.trayectoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.trayectoria.entity.ControlRepitencia;

import java.util.List;

public interface ControlRepitenciaRepository extends JpaRepository<ControlRepitencia, Long> {
    List<ControlRepitencia> findByIdPersona(Long idPersona);
    List<ControlRepitencia> findByIdPersonaAndCodigoCurso(Long idPersona, String codigoCurso);
}
