package pe.edu.upeu.ingresospagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.ingresospagos.entity.EntidadFinanciera;

public interface EntidadFinancieraRepository extends JpaRepository<EntidadFinanciera, Long> {
}
