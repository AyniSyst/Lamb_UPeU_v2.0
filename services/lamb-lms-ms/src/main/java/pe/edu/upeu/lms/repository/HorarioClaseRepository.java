package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.lms.entity.HorarioClase;

import java.util.List;

public interface HorarioClaseRepository extends JpaRepository<HorarioClase, Long> {

    @Query("SELECT h FROM HorarioClase h JOIN FETCH h.seccionCurso")
    List<HorarioClase> findAllConSeccionCurso();
}
