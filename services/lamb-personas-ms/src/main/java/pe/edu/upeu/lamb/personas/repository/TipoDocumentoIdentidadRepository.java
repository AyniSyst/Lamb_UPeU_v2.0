package pe.edu.upeu.lamb.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.personas.entity.TipoDocumentoIdentidad;

public interface TipoDocumentoIdentidadRepository extends JpaRepository<TipoDocumentoIdentidad, Long> {
}
