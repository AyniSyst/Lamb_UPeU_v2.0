package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.TipoCurso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCursoRepository extends JpaRepository<TipoCurso, Long> {

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
