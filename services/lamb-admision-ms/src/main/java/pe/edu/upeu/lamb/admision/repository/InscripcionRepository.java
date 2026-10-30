package pe.edu.upeu.lamb.admision.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.admision.entity.Inscripcion;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    List<Inscripcion> findByIdPersona(Long idPersona);

    List<Inscripcion> findByPeriodo(String periodo);

    boolean existsByIdPersonaAndIdProgramaAndPeriodo(Long idPersona, Long idPrograma, String periodo);
}
