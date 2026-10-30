package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.CargaLectivaRequest;
import pe.edu.upeu.lamb.planificacion.dto.CargaLectivaResponse;
import pe.edu.upeu.lamb.planificacion.entity.CargaLectiva;
import pe.edu.upeu.lamb.planificacion.entity.PeriodoAcademico;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.CargaLectivaMapper;
import pe.edu.upeu.lamb.planificacion.repository.CargaLectivaRepository;
import pe.edu.upeu.lamb.planificacion.repository.PeriodoAcademicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CargaLectivaService {

    private final CargaLectivaRepository cargaLectivaRepository;
    private final PeriodoAcademicoRepository periodoAcademicoRepository;
    private final CargaLectivaMapper cargaLectivaMapper;

    @Transactional(readOnly = true)
    public List<CargaLectivaResponse> listar(Long idPeriodo) {
        List<CargaLectiva> lista = idPeriodo == null
                ? cargaLectivaRepository.findAll()
                : cargaLectivaRepository.findByPeriodoId(idPeriodo);
        return lista.stream().map(cargaLectivaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CargaLectivaResponse obtener(Long id) {
        return cargaLectivaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CargaLectivaResponse crear(CargaLectivaRequest request) {
        validar(request, null);
        CargaLectiva cargaLectiva = cargaLectivaMapper.toEntity(request);
        asignarRelaciones(cargaLectiva, request);
        return cargaLectivaMapper.toResponse(cargaLectivaRepository.save(cargaLectiva));
    }

    @Transactional
    public CargaLectivaResponse actualizar(Long id, CargaLectivaRequest request) {
        CargaLectiva cargaLectiva = buscarOFallar(id);
        validar(request, id);
        cargaLectiva.setIdDocente(request.getIdDocente());
        cargaLectiva.setHorasMaximas(request.getHorasMaximas());
        asignarRelaciones(cargaLectiva, request);
        return cargaLectivaMapper.toResponse(cargaLectivaRepository.save(cargaLectiva));
    }

    @Transactional
    public void eliminar(Long id) {
        cargaLectivaRepository.delete(buscarOFallar(id));
    }

    CargaLectiva buscarOFallar(Long id) {
        return cargaLectivaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carga lectiva no encontrada: " + id));
    }

    private void validar(CargaLectivaRequest request, Long id) {
        boolean duplicado = id == null
                ? cargaLectivaRepository.existsByIdDocenteAndPeriodoId(request.getIdDocente(), request.getIdPeriodo())
                : cargaLectivaRepository.existsByIdDocenteAndPeriodoIdAndIdNot(request.getIdDocente(), request.getIdPeriodo(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de carga lectiva para esa combinacion de: docente, periodo");
        }
    }

    private void asignarRelaciones(CargaLectiva cargaLectiva, CargaLectivaRequest request) {
        cargaLectiva.setPeriodo(periodoAcademicoRepository.findById(request.getIdPeriodo())
                .orElseThrow(() -> new ResourceNotFoundException("Periodo academico no encontrado: " + request.getIdPeriodo())));
    }
}
