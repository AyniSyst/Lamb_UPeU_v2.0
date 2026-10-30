package pe.edu.upeu.ingresospagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.ingresospagos.entity.SerieComprobante;

import java.util.List;

public interface SerieComprobanteRepository extends JpaRepository<SerieComprobante, Long> {

    @Query("SELECT s FROM SerieComprobante s JOIN FETCH s.tipoComprobante")
    List<SerieComprobante> findAllConTipoComprobante();
}
