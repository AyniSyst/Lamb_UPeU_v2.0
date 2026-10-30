package pe.edu.upeu.lamb.trayectoria.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.trayectoria.dto.ReconocimientoRequest;
import pe.edu.upeu.lamb.trayectoria.dto.ReconocimientoResponse;
import pe.edu.upeu.lamb.trayectoria.entity.Reconocimiento;

@Component
public class LogrosMapper {

    public Reconocimiento toEntity(ReconocimientoRequest r) {
        return Reconocimiento.builder()
                .idTrayectoria(r.idTrayectoria())
                .idPersona(r.idPersona())
                .tipoReconocimiento(r.tipoReconocimiento())
                .descripcion(r.descripcion())
                .periodo(r.periodo())
                .idCompetencia(r.idCompetencia())
                .autoevaluacion(r.autoevaluacion())
                .evidenciaUrl(r.evidenciaUrl())
                .fechaRegistro(r.fechaRegistro())
                .build();
    }

    public void update(Reconocimiento c, ReconocimientoRequest r) {
        c.setIdTrayectoria(r.idTrayectoria());
        c.setIdPersona(r.idPersona());
        c.setTipoReconocimiento(r.tipoReconocimiento());
        c.setDescripcion(r.descripcion());
        c.setPeriodo(r.periodo());
        c.setIdCompetencia(r.idCompetencia());
        c.setAutoevaluacion(r.autoevaluacion());
        c.setEvidenciaUrl(r.evidenciaUrl());
        c.setFechaRegistro(r.fechaRegistro());
    }

    public ReconocimientoResponse toResponse(Reconocimiento c) {
        return new ReconocimientoResponse(c.getId(), c.getIdTrayectoria(), c.getIdPersona(),
                c.getTipoReconocimiento(), c.getDescripcion(), c.getPeriodo(), c.getIdCompetencia(),
                c.getAutoevaluacion(), c.getEvidenciaUrl(), c.getFechaRegistro());
    }
}
