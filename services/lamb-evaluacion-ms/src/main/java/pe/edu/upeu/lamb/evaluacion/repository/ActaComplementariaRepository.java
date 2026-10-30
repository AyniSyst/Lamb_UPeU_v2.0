package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.ActaComplementaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActaComplementariaRepository extends JpaRepository<ActaComplementaria, Long> {

    List<ActaComplementaria> findByActaId(Long idActa);

    boolean existsByActaIdAndIdPersona(Long idActa, Long idPersona);

    boolean existsByActaIdAndIdPersonaAndIdNot(Long idActa, Long idPersona, Long id);
}
