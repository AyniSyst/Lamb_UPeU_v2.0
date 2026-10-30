package pe.edu.upeu.lamb.trayectoria.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.trayectoria.dto.*;
import pe.edu.upeu.lamb.trayectoria.entity.*;

@Component
public class EgresoMapper {

    public Egreso toEntity(EgresoRequest r) {
        return Egreso.builder()
                .idTrayectoria(r.idTrayectoria())
                .idPersona(r.idPersona())
                .periodoEgreso(r.periodoEgreso())
                .fechaEgreso(r.fechaEgreso())
                .creditosTotales(r.creditosTotales())
                .promedioFinal(r.promedioFinal())
                .numeroResolucion(r.numeroResolucion())
                .observacion(r.observacion())
                .build();
    }

    public void update(Egreso e, EgresoRequest r) {
        e.setIdTrayectoria(r.idTrayectoria());
        e.setIdPersona(r.idPersona());
        e.setPeriodoEgreso(r.periodoEgreso());
        e.setFechaEgreso(r.fechaEgreso());
        e.setCreditosTotales(r.creditosTotales());
        e.setPromedioFinal(r.promedioFinal());
        e.setNumeroResolucion(r.numeroResolucion());
        e.setObservacion(r.observacion());
    }

    public EgresoResponse toResponse(Egreso e) {
        return new EgresoResponse(e.getId(), e.getIdTrayectoria(), e.getIdPersona(), e.getPeriodoEgreso(),
                e.getFechaEgreso(), e.getCreditosTotales(), e.getPromedioFinal(), e.isTodosRequisitosCumplidos(),
                e.getNumeroResolucion(), e.getObservacion(), e.getVersion());
    }

    public SolicitudConstancia toEntity(SolicitudConstanciaRequest r) {
        return SolicitudConstancia.builder()
                .idTrayectoria(r.idTrayectoria())
                .idPersona(r.idPersona())
                .tipoDocumento(r.tipoDocumento())
                .motivo(r.motivo())
                .estado(r.estado())
                .urlDocumentoGenerado(r.urlDocumentoGenerado())
                .idResponsable(r.idResponsable())
                .fechaSolicitud(r.fechaSolicitud())
                .fechaEntrega(r.fechaEntrega())
                .build();
    }

    public void update(SolicitudConstancia s, SolicitudConstanciaRequest r) {
        s.setIdTrayectoria(r.idTrayectoria());
        s.setIdPersona(r.idPersona());
        s.setTipoDocumento(r.tipoDocumento());
        s.setMotivo(r.motivo());
        s.setEstado(r.estado());
        s.setUrlDocumentoGenerado(r.urlDocumentoGenerado());
        s.setIdResponsable(r.idResponsable());
        s.setFechaSolicitud(r.fechaSolicitud());
        s.setFechaEntrega(r.fechaEntrega());
    }

    public SolicitudConstanciaResponse toResponse(SolicitudConstancia s) {
        return new SolicitudConstanciaResponse(s.getId(), s.getIdTrayectoria(), s.getIdPersona(),
                s.getTipoDocumento(), s.getMotivo(), s.getEstado(), s.getUrlDocumentoGenerado(),
                s.getIdResponsable(), s.getFechaSolicitud(), s.getFechaEntrega());
    }

    public SituacionCierre toEntity(SituacionCierreRequest r) {
        return SituacionCierre.builder()
                .idTrayectoria(r.idTrayectoria())
                .idPersona(r.idPersona())
                .periodo(r.periodo())
                .estadoAcademico(r.estadoAcademico())
                .creditosAprobados(r.creditosAprobados())
                .promedioPeriodo(r.promedioPeriodo())
                .observacion(r.observacion())
                .fechaCierre(r.fechaCierre())
                .build();
    }

    public void update(SituacionCierre s, SituacionCierreRequest r) {
        s.setIdTrayectoria(r.idTrayectoria());
        s.setIdPersona(r.idPersona());
        s.setPeriodo(r.periodo());
        s.setEstadoAcademico(r.estadoAcademico());
        s.setCreditosAprobados(r.creditosAprobados());
        s.setPromedioPeriodo(r.promedioPeriodo());
        s.setObservacion(r.observacion());
        s.setFechaCierre(r.fechaCierre());
    }

    public SituacionCierreResponse toResponse(SituacionCierre s) {
        return new SituacionCierreResponse(s.getId(), s.getIdTrayectoria(), s.getIdPersona(), s.getPeriodo(),
                s.getEstadoAcademico(), s.getCreditosAprobados(), s.getPromedioPeriodo(), s.getObservacion(),
                s.getFechaCierre());
    }

    public InformeTrayectoria toEntity(InformeTrayectoriaRequest r) {
        return InformeTrayectoria.builder()
                .idTrayectoria(r.idTrayectoria())
                .idPersona(r.idPersona())
                .periodo(r.periodo())
                .idApoderado(r.idApoderado())
                .resumen(r.resumen())
                .urlDocumento(r.urlDocumento())
                .fechaGeneracion(r.fechaGeneracion())
                .build();
    }

    public void update(InformeTrayectoria i, InformeTrayectoriaRequest r) {
        i.setIdTrayectoria(r.idTrayectoria());
        i.setIdPersona(r.idPersona());
        i.setPeriodo(r.periodo());
        i.setIdApoderado(r.idApoderado());
        i.setResumen(r.resumen());
        i.setUrlDocumento(r.urlDocumento());
        i.setFechaGeneracion(r.fechaGeneracion());
    }

    public InformeTrayectoriaResponse toResponse(InformeTrayectoria i) {
        return new InformeTrayectoriaResponse(i.getId(), i.getIdTrayectoria(), i.getIdPersona(), i.getPeriodo(),
                i.getIdApoderado(), i.getResumen(), i.getUrlDocumento(), i.getFechaGeneracion());
    }
}
