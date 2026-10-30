package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.SolicitudAperturaGrupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudAperturaGrupoRepository extends JpaRepository<SolicitudAperturaGrupo, Long> {

    List<SolicitudAperturaGrupo> findByOfertaId(Long idOferta);
}
