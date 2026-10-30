package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.lms.entity.SeccionCurso;

import java.util.List;

public interface SeccionCursoRepository extends JpaRepository<SeccionCurso, Long> {

    @Query("SELECT s FROM SeccionCurso s JOIN FETCH s.curso JOIN FETCH s.docente JOIN FETCH s.periodoAcademico")
    List<SeccionCurso> findAllConRelaciones();
}
