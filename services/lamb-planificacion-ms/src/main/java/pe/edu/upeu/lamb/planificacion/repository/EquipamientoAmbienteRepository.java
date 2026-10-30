package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.EquipamientoAmbiente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipamientoAmbienteRepository extends JpaRepository<EquipamientoAmbiente, Long> {

    List<EquipamientoAmbiente> findByAmbienteId(Long idAmbiente);
}
