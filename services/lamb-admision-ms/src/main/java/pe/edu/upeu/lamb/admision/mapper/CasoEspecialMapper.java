package pe.edu.upeu.lamb.admision.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.admision.dto.CasoEspecialResponse;
import pe.edu.upeu.lamb.admision.entity.CasoEspecial;

@Component
public class CasoEspecialMapper {
    public CasoEspecialResponse toResponse(CasoEspecial c) {
        return CasoEspecialResponse.builder()
                .id(c.getId())
                .idPostulante(c.getPostulante().getId())
                .codigoPostulante(c.getPostulante().getCodigoPostulante())
                .tipoCaso(c.getTipoCaso().name())
                .descripcion(c.getDescripcion())
                .requiereAjuste(c.getRequiereAjuste())
                .ajusteOtorgado(c.getAjusteOtorgado())
                .nivelSocioeconomico(c.getNivelSocioeconomico())
                .idResponsable(c.getIdResponsable())
                .estado(c.getEstado().name())
                .fechaRegistro(c.getFechaRegistro())
                .fechaResolucion(c.getFechaResolucion())
                .build();
    }
}
