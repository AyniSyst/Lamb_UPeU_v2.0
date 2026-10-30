package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.lms.entity.EntregaTarea;

import java.util.List;

public interface EntregaTareaRepository extends JpaRepository<EntregaTarea, Long> {

    @Query("SELECT e FROM EntregaTarea e JOIN FETCH e.tarea")
    List<EntregaTarea> findAllConTarea();
}
