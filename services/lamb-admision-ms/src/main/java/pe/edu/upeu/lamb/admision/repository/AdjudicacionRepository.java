package pe.edu.upeu.lamb.admision.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.admision.entity.AdjudicacionVacante;

import java.util.List;
import java.util.Optional;

public interface AdjudicacionRepository extends JpaRepository<AdjudicacionVacante, Long> {
    Optional<AdjudicacionVacante> findByPostulanteId(Long idPostulante);
    boolean existsByPostulanteId(Long idPostulante);
    List<AdjudicacionVacante> findByModalidadId(Long idModalidad);
}
