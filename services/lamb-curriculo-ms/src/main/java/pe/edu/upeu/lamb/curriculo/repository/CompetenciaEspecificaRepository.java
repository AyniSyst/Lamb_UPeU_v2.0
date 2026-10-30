package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.CompetenciaEspecifica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompetenciaEspecificaRepository extends JpaRepository<CompetenciaEspecifica, Long> {

    List<CompetenciaEspecifica> findByPerfilEgresoId(Long idPerfilEgreso);

    boolean existsByPerfilEgresoIdAndCodigo(Long idPerfilEgreso, String codigo);

    boolean existsByPerfilEgresoIdAndCodigoAndIdNot(Long idPerfilEgreso, String codigo, Long id);
}
