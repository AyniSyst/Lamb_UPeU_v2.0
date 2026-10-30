package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.RequisitoEgresoRequest;
import pe.edu.upeu.lamb.curriculo.dto.RequisitoEgresoResponse;
import pe.edu.upeu.lamb.curriculo.entity.RequisitoEgreso;
import org.springframework.stereotype.Component;

@Component
public class RequisitoEgresoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public RequisitoEgreso toEntity(RequisitoEgresoRequest request) {
        return RequisitoEgreso.builder()
                .creditosObligatorios(request.getCreditosObligatorios())
                .creditosElectivos(request.getCreditosElectivos())
                .creditosTotales(request.getCreditosTotales())
                .promedioMinimo(request.getPromedioMinimo())
                .build();
    }

    public RequisitoEgresoResponse toResponse(RequisitoEgreso requisitoEgreso) {
        return RequisitoEgresoResponse.builder()
                .id(requisitoEgreso.getId())
                .idPlan(requisitoEgreso.getPlan().getId())
                .versionPlan(requisitoEgreso.getPlan().getVersion())
                .creditosObligatorios(requisitoEgreso.getCreditosObligatorios())
                .creditosElectivos(requisitoEgreso.getCreditosElectivos())
                .creditosTotales(requisitoEgreso.getCreditosTotales())
                .promedioMinimo(requisitoEgreso.getPromedioMinimo())
                .build();
    }
}
