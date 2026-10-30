package pe.edu.upeu.ingresospagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.ingresospagos.entity.MetodoPago;

public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {
}
