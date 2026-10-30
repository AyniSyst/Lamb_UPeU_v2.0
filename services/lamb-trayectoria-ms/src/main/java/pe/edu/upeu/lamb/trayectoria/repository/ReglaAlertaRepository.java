package pe.edu.upeu.lamb.trayectoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.trayectoria.entity.ReglaAlerta;

import java.util.List;

public interface ReglaAlertaRepository extends JpaRepository<ReglaAlerta, Long> {
    List<ReglaAlerta> findByActivoTrue();
    List<ReglaAlerta> findByTipoAlerta(String tipoAlerta);
}
