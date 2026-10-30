package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.lms.entity.Asistencia;

import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    @Query("SELECT a FROM Asistencia a JOIN FETCH a.sesionClase")
    List<Asistencia> findAllConSesionClase();
}
