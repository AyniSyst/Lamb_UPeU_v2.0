package pe.edu.upeu.ingresospagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.ingresospagos.entity.TarifarioPrograma;

import java.util.List;

public interface TarifarioProgramaRepository extends JpaRepository<TarifarioPrograma, Long> {

    @Query("SELECT t FROM TarifarioPrograma t JOIN FETCH t.conceptoPago")
    List<TarifarioPrograma> findAllConConceptoPago();
}
