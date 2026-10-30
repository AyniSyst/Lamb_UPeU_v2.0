package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.EstadoAsistencia;
import pe.edu.upeu.lamb.evaluacion.entity.RegistroAsistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RegistroAsistenciaRepository extends JpaRepository<RegistroAsistencia, Long> {

    List<RegistroAsistencia> findByIdGrupo(Long idGrupo);

    boolean existsByIdGrupoAndIdPersonaAndFecha(Long idGrupo, Long idPersona, LocalDate fecha);

    boolean existsByIdGrupoAndIdPersonaAndFechaAndIdNot(Long idGrupo, Long idPersona, LocalDate fecha, Long id);

    long countByIdGrupoAndIdPersona(Long idGrupo, Long idPersona);

    long countByIdGrupoAndIdPersonaAndEstado(Long idGrupo, Long idPersona, EstadoAsistencia estado);
}
