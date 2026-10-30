package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.ActividadFormacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadFormacionRepository extends JpaRepository<ActividadFormacion, Long> {

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
