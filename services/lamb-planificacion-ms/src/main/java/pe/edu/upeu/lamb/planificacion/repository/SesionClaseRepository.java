package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.SesionClase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SesionClaseRepository extends JpaRepository<SesionClase, Long> {

    List<SesionClase> findBySilaboId(Long idSilabo);

    boolean existsBySilaboIdAndNumero(Long idSilabo, Integer numero);

    boolean existsBySilaboIdAndNumeroAndIdNot(Long idSilabo, Integer numero, Long id);
}
