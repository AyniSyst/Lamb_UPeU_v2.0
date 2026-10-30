package pe.edu.upeu.ingresospagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.ingresospagos.entity.ArqueoCaja;

import java.util.List;

public interface ArqueoCajaRepository extends JpaRepository<ArqueoCaja, Long> {

    @Query("SELECT a FROM ArqueoCaja a JOIN FETCH a.sesionCaja")
    List<ArqueoCaja> findAllConSesionCaja();
}
