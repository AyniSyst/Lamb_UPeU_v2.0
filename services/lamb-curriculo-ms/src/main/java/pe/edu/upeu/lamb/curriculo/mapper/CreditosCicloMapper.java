package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.CreditosCicloRequest;
import pe.edu.upeu.lamb.curriculo.dto.CreditosCicloResponse;
import pe.edu.upeu.lamb.curriculo.entity.CreditosCiclo;
import org.springframework.stereotype.Component;

@Component
public class CreditosCicloMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public CreditosCiclo toEntity(CreditosCicloRequest request) {
        return CreditosCiclo.builder()
                .creditosMinimos(request.getCreditosMinimos())
                .creditosMaximos(request.getCreditosMaximos())
                .build();
    }

    public CreditosCicloResponse toResponse(CreditosCiclo creditosCiclo) {
        return CreditosCicloResponse.builder()
                .id(creditosCiclo.getId())
                .idCicloPlan(creditosCiclo.getCicloPlan().getId())
                .nombreCicloPlan(creditosCiclo.getCicloPlan().getNombre())
                .creditosMinimos(creditosCiclo.getCreditosMinimos())
                .creditosMaximos(creditosCiclo.getCreditosMaximos())
                .build();
    }
}
