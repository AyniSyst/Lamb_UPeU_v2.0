package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.CursoCatalogoRequest;
import pe.edu.upeu.lamb.curriculo.dto.CursoCatalogoResponse;
import pe.edu.upeu.lamb.curriculo.entity.CursoCatalogo;
import org.springframework.stereotype.Component;

@Component
public class CursoCatalogoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public CursoCatalogo toEntity(CursoCatalogoRequest request) {
        return CursoCatalogo.builder()
                .codigo(request.getCodigo().trim())
                .nombre(request.getNombre().trim())
                .creditos(request.getCreditos())
                .build();
    }

    public CursoCatalogoResponse toResponse(CursoCatalogo cursoCatalogo) {
        return CursoCatalogoResponse.builder()
                .id(cursoCatalogo.getId())
                .codigo(cursoCatalogo.getCodigo())
                .nombre(cursoCatalogo.getNombre())
                .creditos(cursoCatalogo.getCreditos())
                .idDepartamento(cursoCatalogo.getDepartamento() != null ? cursoCatalogo.getDepartamento().getId() : null)
                .codigoDepartamento(cursoCatalogo.getDepartamento() != null ? cursoCatalogo.getDepartamento().getCodigo() : null)
                .idModalidad(cursoCatalogo.getModalidad() != null ? cursoCatalogo.getModalidad().getId() : null)
                .codigoModalidad(cursoCatalogo.getModalidad() != null ? cursoCatalogo.getModalidad().getCodigo() : null)
                .build();
    }
}
