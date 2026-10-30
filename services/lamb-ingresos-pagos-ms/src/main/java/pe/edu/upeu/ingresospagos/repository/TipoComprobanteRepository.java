package pe.edu.upeu.ingresospagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.ingresospagos.entity.TipoComprobante;

public interface TipoComprobanteRepository extends JpaRepository<TipoComprobante, Long> {
}
