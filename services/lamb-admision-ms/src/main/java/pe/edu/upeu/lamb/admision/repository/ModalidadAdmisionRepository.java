package pe.edu.upeu.lamb.admision.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upeu.lamb.admision.entity.ModalidadAdmision;

import java.util.Optional;

public interface ModalidadAdmisionRepository extends JpaRepository<ModalidadAdmision, Long> {

    Optional<ModalidadAdmision> findByCodigo(String codigo);

    // SELECT ... FOR UPDATE: bloquea la fila de la modalidad mientras se reserva
    // una vacante. Dos aprobaciones concurrentes se serializan y la 2da ve el
    // contador ya actualizado -> si no queda cupo, rebota con BusinessRuleException.
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT m FROM ModalidadAdmision m WHERE m.id = :id")
    Optional<ModalidadAdmision> findByIdParaReserva(@Param("id") Long id);
}
