package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.RespuestaSolicitudApertura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespuestaSolicitudAperturaRepository extends JpaRepository<RespuestaSolicitudApertura, Long> {

    List<RespuestaSolicitudApertura> findBySolicitudId(Long idSolicitud);

    boolean existsBySolicitudId(Long idSolicitud);

    boolean existsBySolicitudIdAndIdNot(Long idSolicitud, Long id);
}
