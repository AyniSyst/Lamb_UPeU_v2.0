package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.PlazoApertura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlazoAperturaRepository extends JpaRepository<PlazoApertura, Long> {

    List<PlazoApertura> findByIdGrupo(Long idGrupo);
}
