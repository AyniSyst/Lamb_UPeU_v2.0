package pe.edu.upeu.lamb.trayectoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.trayectoria.entity.Tutoria;

import java.util.List;

public interface TutoriaRepository extends JpaRepository<Tutoria, Long> {
    List<Tutoria> findByIdPersona(Long idPersona);
    List<Tutoria> findByIdTrayectoria(Long idTrayectoria);
    List<Tutoria> findByEstado(String estado);
}
