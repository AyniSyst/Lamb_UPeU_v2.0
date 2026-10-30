package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.InstitucionRequest;
import pe.edu.upeu.lamb.institucional.dto.InstitucionResponse;
import pe.edu.upeu.lamb.institucional.entity.Institucion;

@Component
public class InstitucionMapper {

    public Institucion toEntity(InstitucionRequest request) {
        return Institucion.builder()
                .razonSocial(request.getRazonSocial())
                .ruc(request.getRuc())
                .nombreComercial(request.getNombreComercial())
                .idTipoInstitucion(request.getIdTipoInstitucion())
                .direccion(request.getDireccion())
                .telefono(request.getTelefono())
                .email(request.getEmail())
                .sitioWeb(request.getSitioWeb())
                .fechaFundacion(request.getFechaFundacion())
                .build();
    }

    public InstitucionResponse toResponse(Institucion entity) {
        return InstitucionResponse.builder()
                .id(entity.getId())
                .razonSocial(entity.getRazonSocial())
                .ruc(entity.getRuc())
                .nombreComercial(entity.getNombreComercial())
                .idTipoInstitucion(entity.getIdTipoInstitucion())
                .direccion(entity.getDireccion())
                .telefono(entity.getTelefono())
                .email(entity.getEmail())
                .sitioWeb(entity.getSitioWeb())
                .fechaFundacion(entity.getFechaFundacion())
                .build();
    }
}
