package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.lms.entity.RespuestaForo;

import java.util.List;

public interface RespuestaForoRepository extends JpaRepository<RespuestaForo, Long> {

    @Query("SELECT r FROM RespuestaForo r JOIN FETCH r.foroHilo")
    List<RespuestaForo> findAllConForoHilo();
}
