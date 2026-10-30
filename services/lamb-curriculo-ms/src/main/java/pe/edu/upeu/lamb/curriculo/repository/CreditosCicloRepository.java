package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.CreditosCiclo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditosCicloRepository extends JpaRepository<CreditosCiclo, Long> {

    List<CreditosCiclo> findByCicloPlanId(Long idCicloPlan);

    boolean existsByCicloPlanId(Long idCicloPlan);

    boolean existsByCicloPlanIdAndIdNot(Long idCicloPlan, Long id);
}
