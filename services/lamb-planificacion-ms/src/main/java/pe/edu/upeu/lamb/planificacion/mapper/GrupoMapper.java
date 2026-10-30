package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.GrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.GrupoResponse;
import pe.edu.upeu.lamb.planificacion.entity.AsignacionDocente;
import pe.edu.upeu.lamb.planificacion.entity.Grupo;
import pe.edu.upeu.lamb.planificacion.entity.RolDocente;
import pe.edu.upeu.lamb.planificacion.event.GrupoAbiertoEvento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class GrupoMapper {

    private final CursoMapper cursoMapper;
    private final HorarioGrupoMapper horarioGrupoMapper;
    private final AsignacionDocenteMapper asignacionDocenteMapper;

    // Solo la cabecera: la oferta y los detalles los arma GrupoService dentro de la transaccion
    public Grupo toEntity(GrupoRequest request) {
        return Grupo.builder()
                .codigo(request.getCodigo().trim().toUpperCase())
                .cupoMaximo(request.getCupoMaximo())
                .build();
    }

    public GrupoResponse toResponse(Grupo grupo) {
        return GrupoResponse.builder()
                .id(grupo.getId())
                .codigo(grupo.getCodigo())
                .idOferta(grupo.getOferta().getId())
                .idPrograma(grupo.getOferta().getIdPrograma())
                .periodo(grupo.getPeriodo().getCodigo())
                .cupoMaximo(grupo.getCupoMaximo())
                .estado(grupo.getEstado())
                .motivo(grupo.getMotivo())
                .version(grupo.getVersion())
                .curso(cursoMapper.toResponse(grupo.getCurso()))
                .horarios(grupo.getHorarios().stream().map(horarioGrupoMapper::toResponse).toList())
                .docentes(grupo.getDocentes().stream().map(asignacionDocenteMapper::toResponse).toList())
                .build();
    }

    public GrupoAbiertoEvento toGrupoAbiertoEvento(Grupo grupo, String tipoEvento, String origen) {
        Long idDocenteTitular = grupo.getDocentes().stream()
                .filter(d -> d.getRol() == RolDocente.TITULAR)
                .map(AsignacionDocente::getIdDocente)
                .findFirst()
                .orElse(null);
        return GrupoAbiertoEvento.builder()
                .tipoEvento(tipoEvento)
                .grupoId(grupo.getId())
                .codigoGrupo(grupo.getCodigo())
                .codigoCurso(grupo.getCurso().getCodigo())
                .periodo(grupo.getPeriodo().getCodigo())
                .idDocenteTitular(idDocenteTitular)
                .cupoMaximo(grupo.getCupoMaximo())
                .creditos(grupo.getCurso().getCreditos())
                .origen(origen)
                .timestamp(Instant.now().toEpochMilli())
                .build();
    }
}
