package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.SeccionRequest;
import pe.edu.upeu.lamb.planificacion.dto.SeccionResponse;
import pe.edu.upeu.lamb.planificacion.entity.Seccion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeccionMapper {

    private final CursoMapper cursoMapper;

    public Seccion toEntity(SeccionRequest request) {
        return Seccion.builder()
                .periodo(request.getPeriodo())
                .docente(request.getDocente())
                .cupoMaximo(request.getCupoMaximo())
                .horario(request.getHorario())
                .build();
    }

    public SeccionResponse toResponse(Seccion seccion) {
        return SeccionResponse.builder()
                .id(seccion.getId())
                .periodo(seccion.getPeriodo())
                .docente(seccion.getDocente())
                .cupoMaximo(seccion.getCupoMaximo())
                .horario(seccion.getHorario())
                .estado(seccion.getEstado())
                .motivo(seccion.getMotivo())
                .curso(cursoMapper.toResponse(seccion.getCurso()))
                .build();
    }
}
