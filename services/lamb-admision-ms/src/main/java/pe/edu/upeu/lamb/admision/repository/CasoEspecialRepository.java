package pe.edu.upeu.lamb.admision.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.admision.entity.CasoEspecial;
import pe.edu.upeu.lamb.admision.entity.TipoCaso;

import java.util.List;

public interface CasoEspecialRepository extends JpaRepository<CasoEspecial, Long> {
    List<CasoEspecial> findByPostulanteId(Long idPostulante);
    List<CasoEspecial> findByTipoCaso(TipoCaso tipoCaso);
}
