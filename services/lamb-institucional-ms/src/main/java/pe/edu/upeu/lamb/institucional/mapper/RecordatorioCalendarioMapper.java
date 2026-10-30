package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.RecordatorioCalendarioRequest;
import pe.edu.upeu.lamb.institucional.dto.RecordatorioCalendarioResponse;
import pe.edu.upeu.lamb.institucional.entity.RecordatorioCalendario;

@Component
public class RecordatorioCalendarioMapper {

    public RecordatorioCalendario toEntity(RecordatorioCalendarioRequest request) {
        return RecordatorioCalendario.builder()
                .idEventoCalendario(request.getIdEventoCalendario())
                .mensaje(request.getMensaje())
                .fechaEnvio(request.getFechaEnvio())
                .build();
    }

    public RecordatorioCalendarioResponse toResponse(RecordatorioCalendario entity) {
        return RecordatorioCalendarioResponse.builder()
                .id(entity.getId())
                .idEventoCalendario(entity.getIdEventoCalendario())
                .mensaje(entity.getMensaje())
                .fechaEnvio(entity.getFechaEnvio())
                .build();
    }
}
