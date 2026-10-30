package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.UnidadAprendizaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnidadAprendizajeRepository extends JpaRepository<UnidadAprendizaje, Long> {

    List<UnidadAprendizaje> findBySilaboId(Long idSilabo);

    boolean existsBySilaboIdAndNumero(Long idSilabo, Integer numero);

    boolean existsBySilaboIdAndNumeroAndIdNot(Long idSilabo, Integer numero, Long id);
}
