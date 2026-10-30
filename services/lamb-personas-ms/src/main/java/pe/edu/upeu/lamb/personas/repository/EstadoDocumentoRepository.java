package pe.edu.upeu.lamb.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.personas.entity.EstadoDocumento;

public interface EstadoDocumentoRepository extends JpaRepository<EstadoDocumento, Long> {
}
