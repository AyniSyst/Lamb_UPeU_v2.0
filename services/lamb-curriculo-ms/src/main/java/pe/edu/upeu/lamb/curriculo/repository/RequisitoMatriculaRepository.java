package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.RequisitoMatricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequisitoMatriculaRepository extends JpaRepository<RequisitoMatricula, Long> {

    List<RequisitoMatricula> findByPlanCursoId(Long idPlanCurso);
}
