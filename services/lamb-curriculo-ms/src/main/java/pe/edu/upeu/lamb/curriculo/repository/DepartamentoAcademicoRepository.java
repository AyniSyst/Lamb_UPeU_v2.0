package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.DepartamentoAcademico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartamentoAcademicoRepository extends JpaRepository<DepartamentoAcademico, Long> {

    List<DepartamentoAcademico> findByPlanId(Long idPlan);

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
