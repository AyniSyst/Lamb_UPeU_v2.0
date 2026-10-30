package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.lms.entity.ListaEspera;

import java.util.List;

public interface ListaEsperaRepository extends JpaRepository<ListaEspera, Long> {

    @Query("SELECT l FROM ListaEspera l JOIN FETCH l.seccionCurso")
    List<ListaEspera> findAllConSeccionCurso();
}
