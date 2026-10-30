package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.LineaInvestigacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineaInvestigacionRepository extends JpaRepository<LineaInvestigacion, Long> {

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
