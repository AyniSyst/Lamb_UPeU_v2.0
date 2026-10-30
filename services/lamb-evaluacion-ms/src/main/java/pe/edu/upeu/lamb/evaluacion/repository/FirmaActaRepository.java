package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.FirmaActa;
import pe.edu.upeu.lamb.evaluacion.entity.RolFirma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FirmaActaRepository extends JpaRepository<FirmaActa, Long> {

    List<FirmaActa> findByActaId(Long idActa);

    boolean existsByActaIdAndRol(Long idActa, RolFirma rol);

    boolean existsByActaIdAndRolAndIdNot(Long idActa, RolFirma rol, Long id);
}
