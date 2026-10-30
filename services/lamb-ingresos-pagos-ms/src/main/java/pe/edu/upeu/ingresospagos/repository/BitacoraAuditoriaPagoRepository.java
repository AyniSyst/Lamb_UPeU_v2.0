package pe.edu.upeu.ingresospagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.ingresospagos.entity.BitacoraAuditoriaPago;

import java.util.List;

public interface BitacoraAuditoriaPagoRepository extends JpaRepository<BitacoraAuditoriaPago, Long> {

    @Query("SELECT b FROM BitacoraAuditoriaPago b JOIN FETCH b.pago")
    List<BitacoraAuditoriaPago> findAllConPago();
}
