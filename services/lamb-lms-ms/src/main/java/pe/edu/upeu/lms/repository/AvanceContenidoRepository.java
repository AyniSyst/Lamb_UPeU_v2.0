package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.lms.entity.AvanceContenido;

import java.util.List;

public interface AvanceContenidoRepository extends JpaRepository<AvanceContenido, Long> {

    @Query("SELECT a FROM AvanceContenido a JOIN FETCH a.contenido")
    List<AvanceContenido> findAllConContenido();
}
