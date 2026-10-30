package pe.edu.upeu.lamb.admision.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.admision.dto.ConvocatoriaRequest;
import pe.edu.upeu.lamb.admision.dto.ConvocatoriaResponse;
import pe.edu.upeu.lamb.admision.entity.Convocatoria;
import pe.edu.upeu.lamb.admision.entity.EstadoConvocatoria;

@Component
public class ConvocatoriaMapper {
    public Convocatoria toEntity(ConvocatoriaRequest r) {
        return Convocatoria.builder()
                .codigo(r.getCodigo())
                .nombre(r.getNombre())
                .periodo(r.getPeriodo())
                .fechaInicio(r.getFechaInicio())
                .fechaFin(r.getFechaFin())
                .fechaExamen(r.getFechaExamen())
                .vacantesTotales(r.getVacantesTotales())
                .observacion(r.getObservacion())
                .estado(EstadoConvocatoria.PLANIFICADA)
                .build();
    }
    public ConvocatoriaResponse toResponse(Convocatoria c) {
        return ConvocatoriaResponse.builder()
                .id(c.getId())
                .codigo(c.getCodigo())
                .nombre(c.getNombre())
                .periodo(c.getPeriodo())
                .fechaInicio(c.getFechaInicio())
                .fechaFin(c.getFechaFin())
                .fechaExamen(c.getFechaExamen())
                .estado(c.getEstado().name())
                .vacantesTotales(c.getVacantesTotales())
                .observacion(c.getObservacion())
                .build();
    }
}
