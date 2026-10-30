package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.InhabilitacionInasistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InhabilitacionInasistenciaRepository extends JpaRepository<InhabilitacionInasistencia, Long> {

    List<InhabilitacionInasistencia> findByIdGrupo(Long idGrupo);

    boolean existsByIdGrupoAndIdPersona(Long idGrupo, Long idPersona);

    boolean existsByIdGrupoAndIdPersonaAndIdNot(Long idGrupo, Long idPersona, Long id);
}
