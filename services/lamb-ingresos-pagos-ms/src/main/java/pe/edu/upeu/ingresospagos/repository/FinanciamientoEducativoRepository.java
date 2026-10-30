package pe.edu.upeu.ingresospagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.ingresospagos.entity.FinanciamientoEducativo;

import java.util.List;

public interface FinanciamientoEducativoRepository extends JpaRepository<FinanciamientoEducativo, Long> {

    @Query("SELECT f FROM FinanciamientoEducativo f JOIN FETCH f.entidadFinanciera")
    List<FinanciamientoEducativo> findAllConEntidadFinanciera();
}
