package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.DenominacionPrograma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DenominacionProgramaRepository extends JpaRepository<DenominacionPrograma, Long> {

    boolean existsByIdPrograma(Long idPrograma);

    boolean existsByIdProgramaAndIdNot(Long idPrograma, Long id);
}
