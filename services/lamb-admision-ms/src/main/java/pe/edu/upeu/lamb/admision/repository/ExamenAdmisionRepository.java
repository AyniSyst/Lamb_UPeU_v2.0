package pe.edu.upeu.lamb.admision.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.admision.entity.ExamenAdmision;

import java.util.List;

public interface ExamenAdmisionRepository extends JpaRepository<ExamenAdmision, Long> {
    List<ExamenAdmision> findByConvocatoriaId(Long idConvocatoria);
    boolean existsByCodigo(String codigo);
    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
