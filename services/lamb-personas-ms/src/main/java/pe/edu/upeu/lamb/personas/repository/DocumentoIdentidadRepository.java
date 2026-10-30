package pe.edu.upeu.lamb.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.personas.entity.DocumentoIdentidad;

public interface DocumentoIdentidadRepository extends JpaRepository<DocumentoIdentidad, Long> {
}
