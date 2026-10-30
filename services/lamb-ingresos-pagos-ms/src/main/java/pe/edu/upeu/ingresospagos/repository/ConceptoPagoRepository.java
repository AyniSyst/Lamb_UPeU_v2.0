package pe.edu.upeu.ingresospagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.ingresospagos.entity.ConceptoPago;

public interface ConceptoPagoRepository extends JpaRepository<ConceptoPago, Long> {
}
