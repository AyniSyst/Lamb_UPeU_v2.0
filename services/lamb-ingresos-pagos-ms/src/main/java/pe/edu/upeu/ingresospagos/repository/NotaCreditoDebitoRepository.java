package pe.edu.upeu.ingresospagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.ingresospagos.entity.NotaCreditoDebito;

import java.util.List;

public interface NotaCreditoDebitoRepository extends JpaRepository<NotaCreditoDebito, Long> {

    @Query("SELECT n FROM NotaCreditoDebito n JOIN FETCH n.pago")
    List<NotaCreditoDebito> findAllConPago();
}
