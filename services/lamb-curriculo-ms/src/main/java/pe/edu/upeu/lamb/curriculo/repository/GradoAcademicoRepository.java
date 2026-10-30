package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.GradoAcademico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradoAcademicoRepository extends JpaRepository<GradoAcademico, Long> {

    boolean existsByIdPrograma(Long idPrograma);

    boolean existsByIdProgramaAndIdNot(Long idPrograma, Long id);
}
