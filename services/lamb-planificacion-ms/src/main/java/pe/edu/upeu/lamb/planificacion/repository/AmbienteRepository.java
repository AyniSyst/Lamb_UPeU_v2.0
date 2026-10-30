package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.Ambiente;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {

    List<Ambiente> findByPabellonId(Long idPabellon);

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, Long id);

    // SELECT ... FOR UPDATE: mientras dure la transaccion nadie mas reserva este ambiente
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Ambiente a WHERE a.id = :id")
    Optional<Ambiente> findByIdParaReserva(@Param("id") Long id);
}
