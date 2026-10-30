package pe.edu.upeu.ingresospagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.ingresospagos.entity.SesionCaja;

import java.util.List;

public interface SesionCajaRepository extends JpaRepository<SesionCaja, Long> {

    @Query("SELECT s FROM SesionCaja s JOIN FETCH s.caja")
    List<SesionCaja> findAllConCaja();
}
