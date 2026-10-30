package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.CompetenciaGeneral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetenciaGeneralRepository extends JpaRepository<CompetenciaGeneral, Long> {

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
