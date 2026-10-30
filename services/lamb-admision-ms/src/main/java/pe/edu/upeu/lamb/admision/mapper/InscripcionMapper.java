package pe.edu.upeu.lamb.admision.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.admision.dto.DocumentoPresentadoResponse;
import pe.edu.upeu.lamb.admision.dto.InscripcionResponse;
import pe.edu.upeu.lamb.admision.entity.DocumentoPresentado;
import pe.edu.upeu.lamb.admision.entity.Inscripcion;

import java.util.List;

@Component
public class InscripcionMapper {

    public InscripcionResponse toResponse(Inscripcion i) {
        List<DocumentoPresentadoResponse> docs = i.getDocumentos().stream()
                .map(this::toDocResponse)
                .toList();
        return InscripcionResponse.builder()
                .id(i.getId())
                .idPersona(i.getIdPersona())
                .idPrograma(i.getIdPrograma())
                .idModalidad(i.getModalidad().getId())
                .codigoModalidad(i.getModalidad().getCodigo())
                .nombreModalidad(i.getModalidad().getNombre())
                .periodo(i.getPeriodo())
                .estado(i.getEstado().name())
                .observacion(i.getObservacion())
                .fechaRegistro(i.getFechaRegistro())
                .fechaAprobacion(i.getFechaAprobacion())
                .idAprobadoPor(i.getIdAprobadoPor())
                .version(i.getVersion())
                .documentos(docs)
                .build();
    }

    public DocumentoPresentadoResponse toDocResponse(DocumentoPresentado d) {
        return DocumentoPresentadoResponse.builder()
                .id(d.getId())
                .idTipoDocumento(d.getTipoDocumento().getId())
                .codigoTipoDocumento(d.getTipoDocumento().getCodigo())
                .nombreTipoDocumento(d.getTipoDocumento().getNombre())
                .numeroDocumento(d.getNumeroDocumento())
                .urlArchivo(d.getUrlArchivo())
                .observacion(d.getObservacion())
                .fechaEntrega(d.getFechaEntrega())
                .build();
    }
}
