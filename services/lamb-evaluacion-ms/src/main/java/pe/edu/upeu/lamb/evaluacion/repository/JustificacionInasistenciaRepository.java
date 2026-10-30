package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.JustificacionInasistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JustificacionInasistenciaRepository extends JpaRepository<JustificacionInasistencia, Long> {

    List<JustificacionInasistencia> findByRegistroId(Long idRegistro);
}
