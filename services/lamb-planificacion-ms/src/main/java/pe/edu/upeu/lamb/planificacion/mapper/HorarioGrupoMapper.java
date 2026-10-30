package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.HorarioGrupoResponse;
import pe.edu.upeu.lamb.planificacion.entity.HorarioGrupo;
import org.springframework.stereotype.Component;

@Component
public class HorarioGrupoMapper {

    public HorarioGrupoResponse toResponse(HorarioGrupo horario) {
        return HorarioGrupoResponse.builder()
                .id(horario.getId())
                .idGrupo(horario.getGrupo().getId())
                .codigoGrupo(horario.getGrupo().getCodigo())
                .idAmbiente(horario.getAmbiente().getId())
                .codigoAmbiente(horario.getAmbiente().getCodigo())
                .diaSemana(horario.getDiaSemana())
                .horaInicio(horario.getHoraInicio())
                .horaFin(horario.getHoraFin())
                .activo(horario.getActivo())
                .build();
    }
}
