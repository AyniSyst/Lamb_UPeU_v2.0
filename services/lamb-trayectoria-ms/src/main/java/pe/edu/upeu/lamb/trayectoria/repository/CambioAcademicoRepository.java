package pe.edu.upeu.lamb.trayectoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.trayectoria.entity.CambioAcademico;

import java.util.List;

public interface CambioAcademicoRepository extends JpaRepository<CambioAcademico, Long> {
    List<CambioAcademico> findByIdPersonaOrderByFechaCambioDesc(Long idPersona);
    List<CambioAcademico> findByIdTrayectoriaOrderByFechaCambioDesc(Long idTrayectoria);
}
