package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.EstadoGrupo;
import pe.edu.upeu.lamb.planificacion.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    @Query("SELECT g FROM Grupo g JOIN FETCH g.oferta o JOIN FETCH o.curso JOIN FETCH o.periodo ORDER BY g.id")
    List<Grupo> findAllConOferta();

    List<Grupo> findByOfertaId(Long idOferta);

    List<Grupo> findByEstadoAndOfertaCursoCodigoIn(EstadoGrupo estado, Collection<String> codigos);

    boolean existsByOfertaIdAndCodigo(Long idOferta, String codigo);

    boolean existsByOfertaIdAndCodigoAndIdNot(Long idOferta, String codigo, Long id);
}
