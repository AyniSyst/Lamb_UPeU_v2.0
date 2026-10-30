package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.CursoCatalogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoCatalogoRepository extends JpaRepository<CursoCatalogo, Long> {

    List<CursoCatalogo> findByDepartamentoId(Long idDepartamento);

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
