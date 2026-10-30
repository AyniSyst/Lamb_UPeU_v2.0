package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.DistribucionHorasRequest;
import pe.edu.upeu.lamb.curriculo.dto.DistribucionHorasResponse;
import pe.edu.upeu.lamb.curriculo.entity.DistribucionHoras;
import org.springframework.stereotype.Component;

@Component
public class DistribucionHorasMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public DistribucionHoras toEntity(DistribucionHorasRequest request) {
        return DistribucionHoras.builder()
                .horasTeoria(request.getHorasTeoria())
                .horasPractica(request.getHorasPractica())
                .horasLaboratorio(request.getHorasLaboratorio())
                .build();
    }

    public DistribucionHorasResponse toResponse(DistribucionHoras distribucionHoras) {
        return DistribucionHorasResponse.builder()
                .id(distribucionHoras.getId())
                .idCurso(distribucionHoras.getCurso().getId())
                .codigoCurso(distribucionHoras.getCurso().getCodigo())
                .horasTeoria(distribucionHoras.getHorasTeoria())
                .horasPractica(distribucionHoras.getHorasPractica())
                .horasLaboratorio(distribucionHoras.getHorasLaboratorio())
                .build();
    }
}
