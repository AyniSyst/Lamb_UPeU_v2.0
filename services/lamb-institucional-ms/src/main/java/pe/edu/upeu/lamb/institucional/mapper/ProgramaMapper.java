package pe.edu.upeu.lamb.institucional.mapper;

import pe.edu.upeu.lamb.institucional.dto.ProgramaRequest;
import pe.edu.upeu.lamb.institucional.dto.ProgramaResponse;
import pe.edu.upeu.lamb.institucional.entity.Programa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProgramaMapper {

    private final FacultadMapper facultadMapper;
    private final NivelAcademicoMapper nivelAcademicoMapper;
    private final GradoAcademicoCatalogoMapper gradoAcademicoCatalogoMapper;

    public Programa toEntity(ProgramaRequest request) {
        return Programa.builder()
                .nombre(request.getNombre())
                .gradoAcademico(request.getGradoAcademico() != null ? request.getGradoAcademico() : pe.edu.upeu.lamb.institucional.entity.GradoAcademico.SIN_ASIGNAR)
                .duracionSemestres(request.getDuracionSemestres())
                .build();
    }

    public ProgramaResponse toResponse(Programa programa) {
        return ProgramaResponse.builder()
                .id(programa.getId())
                .nombre(programa.getNombre())
                .gradoAcademico(programa.getGradoAcademico())
                .duracionSemestres(programa.getDuracionSemestres())
                .facultad(facultadMapper.toResponse(programa.getFacultad()))
                .nivelAcademico(programa.getNivelAcademico() != null ? nivelAcademicoMapper.toResponse(programa.getNivelAcademico()) : null)
                .gradoAcademicoCatalogo(programa.getGradoAcademicoCatalogo() != null ? gradoAcademicoCatalogoMapper.toResponse(programa.getGradoAcademicoCatalogo()) : null)
                .build();
    }
}
