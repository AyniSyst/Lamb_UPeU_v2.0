package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.TituloProfesional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TituloProfesionalRepository extends JpaRepository<TituloProfesional, Long> {

    boolean existsByIdPrograma(Long idPrograma);

    boolean existsByIdProgramaAndIdNot(Long idPrograma, Long id);
}
