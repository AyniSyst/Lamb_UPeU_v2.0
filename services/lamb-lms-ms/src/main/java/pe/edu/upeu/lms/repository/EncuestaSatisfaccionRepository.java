package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.lms.entity.EncuestaSatisfaccion;

import java.util.List;

public interface EncuestaSatisfaccionRepository extends JpaRepository<EncuestaSatisfaccion, Long> {

    @Query("SELECT e FROM EncuestaSatisfaccion e JOIN FETCH e.curso")
    List<EncuestaSatisfaccion> findAllConCurso();
}
