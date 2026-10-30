package pe.edu.upeu.lamb.admision.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.admision.entity.Postulante;

import java.util.List;

public interface PostulanteRepository extends JpaRepository<Postulante, Long> {
    List<Postulante> findByConvocatoriaId(Long idConvocatoria);
    boolean existsByCodigoPostulante(String codigo);
    boolean existsByCodigoPostulanteAndIdNot(String codigo, Long id);
    boolean existsByIdPersonaAndConvocatoriaId(Long idPersona, Long idConvocatoria);
}
