package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.lms.entity.AnuncioCurso;

import java.util.List;

public interface AnuncioCursoRepository extends JpaRepository<AnuncioCurso, Long> {

    @Query("SELECT a FROM AnuncioCurso a JOIN FETCH a.curso")
    List<AnuncioCurso> findAllConCurso();
}
