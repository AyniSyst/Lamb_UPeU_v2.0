package pe.edu.upeu.lamb.admision.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.admision.dto.PostulanteResponse;
import pe.edu.upeu.lamb.admision.entity.Postulante;

@Component
public class PostulanteMapper {
    public PostulanteResponse toResponse(Postulante p) {
        return PostulanteResponse.builder()
                .id(p.getId())
                .idPersona(p.getIdPersona())
                .idConvocatoria(p.getConvocatoria().getId())
                .codigoConvocatoria(p.getConvocatoria().getCodigo())
                .idPrograma(p.getIdPrograma())
                .idModalidad(p.getModalidad().getId())
                .codigoModalidad(p.getModalidad().getCodigo())
                .codigoPostulante(p.getCodigoPostulante())
                .colegioProcedencia(p.getColegioProcedencia())
                .anioEgresoColegio(p.getAnioEgresoColegio())
                .promedioColegio(p.getPromedioColegio())
                .telefonoContacto(p.getTelefonoContacto())
                .correoContacto(p.getCorreoContacto())
                .nombreApoderado(p.getNombreApoderado())
                .telefonoApoderado(p.getTelefonoApoderado())
                .estado(p.getEstado().name())
                .fechaRegistro(p.getFechaRegistro())
                .build();
    }
}
