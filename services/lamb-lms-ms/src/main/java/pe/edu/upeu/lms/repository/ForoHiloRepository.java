package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.lms.entity.ForoHilo;

import java.util.List;

public interface ForoHiloRepository extends JpaRepository<ForoHilo, Long> {

    @Query("SELECT f FROM ForoHilo f JOIN FETCH f.curso")
    List<ForoHilo> findAllConCurso();
}
