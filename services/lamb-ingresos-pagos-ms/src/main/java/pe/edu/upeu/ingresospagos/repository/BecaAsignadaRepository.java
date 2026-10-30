package pe.edu.upeu.ingresospagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.ingresospagos.entity.BecaAsignada;

import java.util.List;

public interface BecaAsignadaRepository extends JpaRepository<BecaAsignada, Long> {

    @Query("SELECT b FROM BecaAsignada b JOIN FETCH b.beca")
    List<BecaAsignada> findAllConBeca();
}
