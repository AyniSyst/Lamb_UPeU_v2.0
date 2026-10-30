package pe.edu.upeu.ingresospagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.ingresospagos.entity.LogTransaccionPasarela;

import java.util.List;

public interface LogTransaccionPasarelaRepository extends JpaRepository<LogTransaccionPasarela, Long> {

    @Query("SELECT l FROM LogTransaccionPasarela l JOIN FETCH l.pago")
    List<LogTransaccionPasarela> findAllConPago();
}
