package pe.edu.upeu.lamb.trayectoria.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.trayectoria.dto.CambioAcademicoRequest;
import pe.edu.upeu.lamb.trayectoria.dto.CambioAcademicoResponse;
import pe.edu.upeu.lamb.trayectoria.entity.CambioAcademico;

@Component
public class EstudiantePlanMapper {

    public CambioAcademico toEntity(CambioAcademicoRequest r) {
        return CambioAcademico.builder()
                .idTrayectoria(r.idTrayectoria())
                .idPersona(r.idPersona())
                .tipoCambio(r.tipoCambio())
                .idPlanOrigen(r.idPlanOrigen())
                .idPlanDestino(r.idPlanDestino())
                .idEscuelaOrigen(r.idEscuelaOrigen())
                .idEscuelaDestino(r.idEscuelaDestino())
                .idSedeOrigen(r.idSedeOrigen())
                .idSedeDestino(r.idSedeDestino())
                .estadoAnterior(r.estadoAnterior())
                .estadoNuevo(r.estadoNuevo())
                .motivo(r.motivo())
                .numeroResolucion(r.numeroResolucion())
                .fechaCambio(r.fechaCambio())
                .fechaVigencia(r.fechaVigencia())
                .build();
    }

    public void update(CambioAcademico c, CambioAcademicoRequest r) {
        c.setIdTrayectoria(r.idTrayectoria());
        c.setIdPersona(r.idPersona());
        c.setTipoCambio(r.tipoCambio());
        c.setIdPlanOrigen(r.idPlanOrigen());
        c.setIdPlanDestino(r.idPlanDestino());
        c.setIdEscuelaOrigen(r.idEscuelaOrigen());
        c.setIdEscuelaDestino(r.idEscuelaDestino());
        c.setIdSedeOrigen(r.idSedeOrigen());
        c.setIdSedeDestino(r.idSedeDestino());
        c.setEstadoAnterior(r.estadoAnterior());
        c.setEstadoNuevo(r.estadoNuevo());
        c.setMotivo(r.motivo());
        c.setNumeroResolucion(r.numeroResolucion());
        c.setFechaCambio(r.fechaCambio());
        c.setFechaVigencia(r.fechaVigencia());
    }

    public CambioAcademicoResponse toResponse(CambioAcademico c) {
        return new CambioAcademicoResponse(
                c.getId(), c.getIdTrayectoria(), c.getIdPersona(), c.getTipoCambio(),
                c.getIdPlanOrigen(), c.getIdPlanDestino(), c.getIdEscuelaOrigen(), c.getIdEscuelaDestino(),
                c.getIdSedeOrigen(), c.getIdSedeDestino(), c.getEstadoAnterior(), c.getEstadoNuevo(),
                c.getMotivo(), c.getNumeroResolucion(), c.getFechaCambio(), c.getFechaVigencia());
    }
}
