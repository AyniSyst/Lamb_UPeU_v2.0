package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.DiaNoLectivoRequest;
import pe.edu.upeu.lamb.planificacion.dto.DiaNoLectivoResponse;
import pe.edu.upeu.lamb.planificacion.entity.CalendarioAcademico;
import pe.edu.upeu.lamb.planificacion.entity.DiaNoLectivo;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.DiaNoLectivoMapper;
import pe.edu.upeu.lamb.planificacion.repository.CalendarioAcademicoRepository;
import pe.edu.upeu.lamb.planificacion.repository.DiaNoLectivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaNoLectivoService {

    private final DiaNoLectivoRepository diaNoLectivoRepository;
    private final CalendarioAcademicoRepository calendarioAcademicoRepository;
    private final DiaNoLectivoMapper diaNoLectivoMapper;

    @Transactional(readOnly = true)
    public List<DiaNoLectivoResponse> listar(Long idCalendario) {
        List<DiaNoLectivo> lista = idCalendario == null
                ? diaNoLectivoRepository.findAll()
                : diaNoLectivoRepository.findByCalendarioId(idCalendario);
        return lista.stream().map(diaNoLectivoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public DiaNoLectivoResponse obtener(Long id) {
        return diaNoLectivoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public DiaNoLectivoResponse crear(DiaNoLectivoRequest request) {
        validar(request, null);
        DiaNoLectivo diaNoLectivo = diaNoLectivoMapper.toEntity(request);
        asignarRelaciones(diaNoLectivo, request);
        return diaNoLectivoMapper.toResponse(diaNoLectivoRepository.save(diaNoLectivo));
    }

    @Transactional
    public DiaNoLectivoResponse actualizar(Long id, DiaNoLectivoRequest request) {
        DiaNoLectivo diaNoLectivo = buscarOFallar(id);
        validar(request, id);
        diaNoLectivo.setFecha(request.getFecha());
        diaNoLectivo.setMotivo(request.getMotivo().trim());
        diaNoLectivo.setTipo(request.getTipo());
        asignarRelaciones(diaNoLectivo, request);
        return diaNoLectivoMapper.toResponse(diaNoLectivoRepository.save(diaNoLectivo));
    }

    @Transactional
    public void eliminar(Long id) {
        diaNoLectivoRepository.delete(buscarOFallar(id));
    }

    DiaNoLectivo buscarOFallar(Long id) {
        return diaNoLectivoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dia no lectivo no encontrado: " + id));
    }

    private void validar(DiaNoLectivoRequest request, Long id) {
        boolean duplicado = id == null
                ? diaNoLectivoRepository.existsByCalendarioIdAndFecha(request.getIdCalendario(), request.getFecha())
                : diaNoLectivoRepository.existsByCalendarioIdAndFechaAndIdNot(request.getIdCalendario(), request.getFecha(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de dia no lectivo para esa combinacion de: calendario, fecha");
        }
    }

    private void asignarRelaciones(DiaNoLectivo diaNoLectivo, DiaNoLectivoRequest request) {
        diaNoLectivo.setCalendario(calendarioAcademicoRepository.findById(request.getIdCalendario())
                .orElseThrow(() -> new ResourceNotFoundException("Calendario academico no encontrado: " + request.getIdCalendario())));
    }
}
