package pe.edu.upeu.lamb.trayectoria.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.trayectoria.dto.CumplimientoRequisitoRequest;
import pe.edu.upeu.lamb.trayectoria.dto.CumplimientoRequisitoResponse;
import pe.edu.upeu.lamb.trayectoria.entity.CumplimientoRequisito;

@Component
public class AvanceRequisitosMapper {

    public CumplimientoRequisito toEntity(CumplimientoRequisitoRequest r) {
        return CumplimientoRequisito.builder()
                .idTrayectoria(r.idTrayectoria())
                .idPersona(r.idPersona())
                .tipoRequisito(r.tipoRequisito())
                .idRequisitoExterno(r.idRequisitoExterno())
                .codigoExterno(r.codigoExterno())
                .descripcion(r.descripcion())
                .cumplido(Boolean.TRUE.equals(r.cumplido()))
                .valorCumplimiento(r.valorCumplimiento())
                .creditosReconocidos(r.creditosReconocidos())
                .fechaCumplimiento(r.fechaCumplimiento())
                .evidenciaUrl(r.evidenciaUrl())
                .observacion(r.observacion())
                .build();
    }

    public void update(CumplimientoRequisito c, CumplimientoRequisitoRequest r) {
        c.setIdTrayectoria(r.idTrayectoria());
        c.setIdPersona(r.idPersona());
        c.setTipoRequisito(r.tipoRequisito());
        c.setIdRequisitoExterno(r.idRequisitoExterno());
        c.setCodigoExterno(r.codigoExterno());
        c.setDescripcion(r.descripcion());
        c.setCumplido(Boolean.TRUE.equals(r.cumplido()));
        c.setValorCumplimiento(r.valorCumplimiento());
        c.setCreditosReconocidos(r.creditosReconocidos());
        c.setFechaCumplimiento(r.fechaCumplimiento());
        c.setEvidenciaUrl(r.evidenciaUrl());
        c.setObservacion(r.observacion());
    }

    public CumplimientoRequisitoResponse toResponse(CumplimientoRequisito c) {
        return new CumplimientoRequisitoResponse(
                c.getId(), c.getIdTrayectoria(), c.getIdPersona(), c.getTipoRequisito(),
                c.getIdRequisitoExterno(), c.getCodigoExterno(), c.getDescripcion(), c.isCumplido(),
                c.getValorCumplimiento(), c.getCreditosReconocidos(), c.getFechaCumplimiento(),
                c.getEvidenciaUrl(), c.getObservacion());
    }
}
