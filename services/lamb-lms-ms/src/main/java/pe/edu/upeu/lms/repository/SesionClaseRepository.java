package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.lms.entity.SesionClase;

import java.util.List;

public interface SesionClaseRepository extends JpaRepository<SesionClase, Long> {

    @Query("SELECT s FROM SesionClase s JOIN FETCH s.seccionCurso")
    List<SesionClase> findAllConSeccionCurso();
}
