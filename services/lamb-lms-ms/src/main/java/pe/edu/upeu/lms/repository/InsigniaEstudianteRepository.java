package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.lms.entity.InsigniaEstudiante;

import java.util.List;

public interface InsigniaEstudianteRepository extends JpaRepository<InsigniaEstudiante, Long> {

    @Query("SELECT i FROM InsigniaEstudiante i JOIN FETCH i.insignia")
    List<InsigniaEstudiante> findAllConInsignia();
}
