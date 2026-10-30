package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.CargaNoLectivaRequest;
import pe.edu.upeu.lamb.planificacion.dto.CargaNoLectivaResponse;
import pe.edu.upeu.lamb.planificacion.entity.CargaNoLectiva;
import pe.edu.upeu.lamb.planificacion.entity.PeriodoAcademico;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.CargaNoLectivaMapper;
import pe.edu.upeu.lamb.planificacion.repository.CargaNoLectivaRepository;
import pe.edu.upeu.lamb.planificacion.repository.PeriodoAcademicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CargaNoLectivaService {

    private final CargaNoLectivaRepository cargaNoLectivaRepository;
    private final PeriodoAcademicoRepository periodoAcademicoRepository;
    private final CargaNoLectivaMapper cargaNoLectivaMapper;

    @Transactional(readOnly = true)
    public List<CargaNoLectivaResponse> listar(Long idPeriodo) {
        List<CargaNoLectiva> lista = idPeriodo == null
                ? cargaNoLectivaRepository.findAll()
                : cargaNoLectivaRepository.findByPeriodoId(idPeriodo);
        return lista.stream().map(cargaNoLectivaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CargaNoLectivaResponse obtener(Long id) {
        return cargaNoLectivaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CargaNoLectivaResponse crear(CargaNoLectivaRequest request) {
        validar(request, null);
        CargaNoLectiva cargaNoLectiva = cargaNoLectivaMapper.toEntity(request);
        asignarRelaciones(cargaNoLectiva, request);
        return cargaNoLectivaMapper.toResponse(cargaNoLectivaRepository.save(cargaNoLectiva));
    }

    @Transactional
    public CargaNoLectivaResponse actualizar(Long id, CargaNoLectivaRequest request) {
        CargaNoLectiva cargaNoLectiva = buscarOFallar(id);
        validar(request, id);
        cargaNoLectiva.setIdDocente(request.getIdDocente());
        cargaNoLectiva.setTipo(request.getTipo());
        cargaNoLectiva.setDescripcion(request.getDescripcion());
        cargaNoLectiva.setHorasSemanales(request.getHorasSemanales());
        asignarRelaciones(cargaNoLectiva, request);
        return cargaNoLectivaMapper.toResponse(cargaNoLectivaRepository.save(cargaNoLectiva));
    }

    @Transactional
    public void eliminar(Long id) {
        cargaNoLectivaRepository.delete(buscarOFallar(id));
    }

    CargaNoLectiva buscarOFallar(Long id) {
        return cargaNoLectivaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carga no lectiva no encontrada: " + id));
    }

    private void validar(CargaNoLectivaRequest request, Long id) {
    }

    private void asignarRelaciones(CargaNoLectiva cargaNoLectiva, CargaNoLectivaRequest request) {
        cargaNoLectiva.setPeriodo(periodoAcademicoRepository.findById(request.getIdPeriodo())
                .orElseThrow(() -> new ResourceNotFoundException("Periodo academico no encontrado: " + request.getIdPeriodo())));
    }
}
