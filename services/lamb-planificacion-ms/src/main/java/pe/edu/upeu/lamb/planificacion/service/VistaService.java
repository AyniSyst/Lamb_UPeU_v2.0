package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.HorarioEstudianteResponse;
import pe.edu.upeu.lamb.planificacion.repository.HorarioGrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VistaService {

    private final HorarioGrupoRepository horarioGrupoRepository;

    // Horario del estudiante = horarios activos de los grupos en que esta matriculado
    @Transactional(readOnly = true)
    public List<HorarioEstudianteResponse> horarioEstudiante(List<Long> idsGrupo) {
        return horarioGrupoRepository.findActivosDeGrupos(idsGrupo).stream()
                .map(h -> HorarioEstudianteResponse.builder()
                        .diaSemana(h.getDiaSemana())
                        .horaInicio(h.getHoraInicio())
                        .horaFin(h.getHoraFin())
                        .idGrupo(h.getGrupo().getId())
                        .codigoGrupo(h.getGrupo().getCodigo())
                        .codigoCurso(h.getGrupo().getCurso().getCodigo())
                        .nombreCurso(h.getGrupo().getCurso().getNombre())
                        .codigoAmbiente(h.getAmbiente().getCodigo())
                        .build())
                .toList();
    }
}
