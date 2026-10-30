package pe.edu.upeu.lamb.admision.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.admision.dto.ExamenAdmisionResponse;
import pe.edu.upeu.lamb.admision.entity.ExamenAdmision;

@Component
public class ExamenAdmisionMapper {
    public ExamenAdmisionResponse toResponse(ExamenAdmision e) {
        return ExamenAdmisionResponse.builder()
                .id(e.getId())
                .idConvocatoria(e.getConvocatoria().getId())
                .codigoConvocatoria(e.getConvocatoria().getCodigo())
                .codigo(e.getCodigo())
                .nombre(e.getNombre())
                .fechaExamen(e.getFechaExamen())
                .duracionMinutos(e.getDuracionMinutos())
                .idAula(e.getIdAula())
                .aforoMaximo(e.getAforoMaximo())
                .aforoAsignado(e.getAforoAsignado())
                .estado(e.getEstado().name())
                .build();
    }
}
