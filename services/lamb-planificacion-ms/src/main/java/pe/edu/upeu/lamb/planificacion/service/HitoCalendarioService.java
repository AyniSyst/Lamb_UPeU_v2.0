package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.HitoCalendarioRequest;
import pe.edu.upeu.lamb.planificacion.dto.HitoCalendarioResponse;
import pe.edu.upeu.lamb.planificacion.entity.CalendarioAcademico;
import pe.edu.upeu.lamb.planificacion.entity.HitoCalendario;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.HitoCalendarioMapper;
import pe.edu.upeu.lamb.planificacion.repository.CalendarioAcademicoRepository;
import pe.edu.upeu.lamb.planificacion.repository.HitoCalendarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HitoCalendarioService {

    private final HitoCalendarioRepository hitoCalendarioRepository;
    private final CalendarioAcademicoRepository calendarioAcademicoRepository;
    private final HitoCalendarioMapper hitoCalendarioMapper;

    @Transactional(readOnly = true)
    public List<HitoCalendarioResponse> listar(Long idCalendario) {
        List<HitoCalendario> lista = idCalendario == null
                ? hitoCalendarioRepository.findAll()
                : hitoCalendarioRepository.findByCalendarioId(idCalendario);
        return lista.stream().map(hitoCalendarioMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public HitoCalendarioResponse obtener(Long id) {
        return hitoCalendarioMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public HitoCalendarioResponse crear(HitoCalendarioRequest request) {
        validar(request, null);
        HitoCalendario hitoCalendario = hitoCalendarioMapper.toEntity(request);
        asignarRelaciones(hitoCalendario, request);
        return hitoCalendarioMapper.toResponse(hitoCalendarioRepository.save(hitoCalendario));
    }

    @Transactional
    public HitoCalendarioResponse actualizar(Long id, HitoCalendarioRequest request) {
        HitoCalendario hitoCalendario = buscarOFallar(id);
        validar(request, id);
        hitoCalendario.setTipo(request.getTipo());
        hitoCalendario.setNombre(request.getNombre().trim());
        hitoCalendario.setFechaInicio(request.getFechaInicio());
        hitoCalendario.setFechaFin(request.getFechaFin());
        asignarRelaciones(hitoCalendario, request);
        return hitoCalendarioMapper.toResponse(hitoCalendarioRepository.save(hitoCalendario));
    }

    @Transactional
    public void eliminar(Long id) {
        hitoCalendarioRepository.delete(buscarOFallar(id));
    }

    HitoCalendario buscarOFallar(Long id) {
        return hitoCalendarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hito del calendario no encontrado: " + id));
    }

    private void validar(HitoCalendarioRequest request, Long id) {
        if (request.getFechaFin().isBefore(request.getFechaInicio())) {
            throw new BusinessRuleException("Fecha fin no puede ser anterior a fecha inicio");
        }
    }

    private void asignarRelaciones(HitoCalendario hitoCalendario, HitoCalendarioRequest request) {
        hitoCalendario.setCalendario(calendarioAcademicoRepository.findById(request.getIdCalendario())
                .orElseThrow(() -> new ResourceNotFoundException("Calendario academico no encontrado: " + request.getIdCalendario())));
    }
}
