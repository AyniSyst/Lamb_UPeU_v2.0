package pe.edu.upeu.lamb.admision.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.admision.entity.ModalidadDocumento;

import java.util.List;

public interface ModalidadDocumentoRepository extends JpaRepository<ModalidadDocumento, Long> {

    List<ModalidadDocumento> findByModalidadIdAndObligatorioTrue(Long idModalidad);

    List<ModalidadDocumento> findByModalidadId(Long idModalidad);
}
