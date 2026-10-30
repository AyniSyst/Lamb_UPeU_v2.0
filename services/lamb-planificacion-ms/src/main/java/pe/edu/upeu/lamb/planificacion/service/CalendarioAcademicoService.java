package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.CalendarioAcademicoRequest;
import pe.edu.upeu.lamb.planificacion.dto.CalendarioAcademicoResponse;
import pe.edu.upeu.lamb.planificacion.entity.CalendarioAcademico;
import pe.edu.upeu.lamb.planificacion.entity.PeriodoAcademico;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.CalendarioAcademicoMapper;
import pe.edu.upeu.lamb.planificacion.repository.CalendarioAcademicoRepository;
import pe.edu.upeu.lamb.planificacion.repository.PeriodoAcademicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarioAcademicoService {

    private final CalendarioAcademicoRepository calendarioAcademicoRepository;
    private final PeriodoAcademicoRepository periodoAcademicoRepository;
    private final CalendarioAcademicoMapper calendarioAcademicoMapper;

    @Transactional(readOnly = true)
    public List<CalendarioAcademicoResponse> listar(Long idPeriodo) {
        List<CalendarioAcademico> lista = idPeriodo == null
                ? calendarioAcademicoRepository.findAll()
                : calendarioAcademicoRepository.findByPeriodoId(idPeriodo);
        return lista.stream().map(calendarioAcademicoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CalendarioAcademicoResponse obtener(Long id) {
        return calendarioAcademicoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CalendarioAcademicoResponse crear(CalendarioAcademicoRequest request) {
        validar(request, null);
        CalendarioAcademico calendarioAcademico = calendarioAcademicoMapper.toEntity(request);
        asignarRelaciones(calendarioAcademico, request);
        return calendarioAcademicoMapper.toResponse(calendarioAcademicoRepository.save(calendarioAcademico));
    }

    @Transactional
    public CalendarioAcademicoResponse actualizar(Long id, CalendarioAcademicoRequest request) {
        CalendarioAcademico calendarioAcademico = buscarOFallar(id);
        validar(request, id);
        calendarioAcademico.setIdSede(request.getIdSede());
        calendarioAcademico.setNombre(request.getNombre().trim());
        calendarioAcademico.setResolucion(request.getResolucion());
        calendarioAcademico.setEstado(request.getEstado());
        asignarRelaciones(calendarioAcademico, request);
        return calendarioAcademicoMapper.toResponse(calendarioAcademicoRepository.save(calendarioAcademico));
    }

    @Transactional
    public void eliminar(Long id) {
        calendarioAcademicoRepository.delete(buscarOFallar(id));
    }

    CalendarioAcademico buscarOFallar(Long id) {
        return calendarioAcademicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calendario academico no encontrado: " + id));
    }

    private void validar(CalendarioAcademicoRequest request, Long id) {
        boolean duplicado = id == null
                ? calendarioAcademicoRepository.existsByPeriodoIdAndIdSede(request.getIdPeriodo(), request.getIdSede())
                : calendarioAcademicoRepository.existsByPeriodoIdAndIdSedeAndIdNot(request.getIdPeriodo(), request.getIdSede(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de calendario academico para esa combinacion de: periodo, sede");
        }
    }

    private void asignarRelaciones(CalendarioAcademico calendarioAcademico, CalendarioAcademicoRequest request) {
        calendarioAcademico.setPeriodo(periodoAcademicoRepository.findById(request.getIdPeriodo())
                .orElseThrow(() -> new ResourceNotFoundException("Periodo academico no encontrado: " + request.getIdPeriodo())));
    }
}
