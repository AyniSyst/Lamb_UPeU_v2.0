package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.LogTransaccionPasarelaRequest;
import pe.edu.upeu.ingresospagos.dto.LogTransaccionPasarelaResponse;
import pe.edu.upeu.ingresospagos.entity.LogTransaccionPasarela;

@Mapper(componentModel = "spring", uses = PagoMapper.class)
public interface LogTransaccionPasarelaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pago", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    LogTransaccionPasarela toEntity(LogTransaccionPasarelaRequest request);

    LogTransaccionPasarelaResponse toResponse(LogTransaccionPasarela logTransaccionPasarela);
}
