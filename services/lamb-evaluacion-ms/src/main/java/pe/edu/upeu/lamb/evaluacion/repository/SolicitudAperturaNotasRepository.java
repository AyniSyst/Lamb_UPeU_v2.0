package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.SolicitudAperturaNotas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudAperturaNotasRepository extends JpaRepository<SolicitudAperturaNotas, Long> {

    List<SolicitudAperturaNotas> findByIdGrupo(Long idGrupo);
}
