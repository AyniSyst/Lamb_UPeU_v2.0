package pe.edu.upeu.lamb.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.personas.entity.DocumentoPersona;

public interface DocumentoPersonaRepository extends JpaRepository<DocumentoPersona, Long> {
}
