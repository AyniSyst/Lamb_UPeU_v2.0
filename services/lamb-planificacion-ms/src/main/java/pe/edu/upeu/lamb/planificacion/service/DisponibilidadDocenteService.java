package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.DisponibilidadDocenteRequest;
import pe.edu.upeu.lamb.planificacion.dto.DisponibilidadDocenteResponse;
import pe.edu.upeu.lamb.planificacion.entity.DisponibilidadDocente;
import pe.edu.upeu.lamb.planificacion.entity.PeriodoAcademico;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.DisponibilidadDocenteMapper;
import pe.edu.upeu.lamb.planificacion.repository.DisponibilidadDocenteRepository;
import pe.edu.upeu.lamb.planificacion.repository.PeriodoAcademicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisponibilidadDocenteService {

    private final DisponibilidadDocenteRepository disponibilidadDocenteRepository;
    private final PeriodoAcademicoRepository periodoAcademicoRepository;
    private final DisponibilidadDocenteMapper disponibilidadDocenteMapper;

    @Transactional(readOnly = true)
    public List<DisponibilidadDocenteResponse> listar(Long idPeriodo) {
        List<DisponibilidadDocente> lista = idPeriodo == null
                ? disponibilidadDocenteRepository.findAll()
                : disponibilidadDocenteRepository.findByPeriodoId(idPeriodo);
        return lista.stream().map(disponibilidadDocenteMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public DisponibilidadDocenteResponse obtener(Long id) {
        return disponibilidadDocenteMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public DisponibilidadDocenteResponse crear(DisponibilidadDocenteRequest request) {
        validar(request, null);
        DisponibilidadDocente disponibilidadDocente = disponibilidadDocenteMapper.toEntity(request);
        asignarRelaciones(disponibilidadDocente, request);
        return disponibilidadDocenteMapper.toResponse(disponibilidadDocenteRepository.save(disponibilidadDocente));
    }

    @Transactional
    public DisponibilidadDocenteResponse actualizar(Long id, DisponibilidadDocenteRequest request) {
        DisponibilidadDocente disponibilidadDocente = buscarOFallar(id);
        validar(request, id);
        disponibilidadDocente.setIdDocente(request.getIdDocente());
        disponibilidadDocente.setDiaSemana(request.getDiaSemana());
        disponibilidadDocente.setHoraInicio(request.getHoraInicio());
        disponibilidadDocente.setHoraFin(request.getHoraFin());
        asignarRelaciones(disponibilidadDocente, request);
        return disponibilidadDocenteMapper.toResponse(disponibilidadDocenteRepository.save(disponibilidadDocente));
    }

    @Transactional
    public void eliminar(Long id) {
        disponibilidadDocenteRepository.delete(buscarOFallar(id));
    }

    DisponibilidadDocente buscarOFallar(Long id) {
        return disponibilidadDocenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disponibilidad horaria del docente no encontrada: " + id));
    }

    private void validar(DisponibilidadDocenteRequest request, Long id) {
        if (!request.getHoraFin().isAfter(request.getHoraInicio())) {
            throw new BusinessRuleException("Hora fin debe ser posterior a hora inicio");
        }
    }

    private void asignarRelaciones(DisponibilidadDocente disponibilidadDocente, DisponibilidadDocenteRequest request) {
        disponibilidadDocente.setPeriodo(periodoAcademicoRepository.findById(request.getIdPeriodo())
                .orElseThrow(() -> new ResourceNotFoundException("Periodo academico no encontrado: " + request.getIdPeriodo())));
    }
}
