package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.BitacoraAuditoriaPagoRequest;
import pe.edu.upeu.ingresospagos.dto.BitacoraAuditoriaPagoResponse;
import pe.edu.upeu.ingresospagos.entity.BitacoraAuditoriaPago;

@Mapper(componentModel = "spring", uses = PagoMapper.class)
public interface BitacoraAuditoriaPagoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pago", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    BitacoraAuditoriaPago toEntity(BitacoraAuditoriaPagoRequest request);

    BitacoraAuditoriaPagoResponse toResponse(BitacoraAuditoriaPago bitacoraAuditoriaPago);
}
