package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.MiembroComision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MiembroComisionRepository extends JpaRepository<MiembroComision, Long> {

    List<MiembroComision> findByComisionId(Long idComision);

    boolean existsByComisionIdAndIdPersona(Long idComision, Long idPersona);

    boolean existsByComisionIdAndIdPersonaAndIdNot(Long idComision, Long idPersona, Long id);
}
