package pe.edu.upeu.lamb.institucional.repository;

import pe.edu.upeu.lamb.institucional.entity.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProgramaRepository extends JpaRepository<Programa, Long> {

    @Query("SELECT p FROM Programa p JOIN FETCH p.facultad")
    List<Programa> findAllConFacultad();
}
