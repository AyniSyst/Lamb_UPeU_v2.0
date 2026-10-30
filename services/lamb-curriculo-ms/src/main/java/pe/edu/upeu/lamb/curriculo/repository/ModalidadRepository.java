package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.Modalidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModalidadRepository extends JpaRepository<Modalidad, Long> {

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
