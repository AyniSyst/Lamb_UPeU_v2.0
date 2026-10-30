package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.MiembroComisionRequest;
import pe.edu.upeu.lamb.curriculo.dto.MiembroComisionResponse;
import pe.edu.upeu.lamb.curriculo.entity.ComisionCurricular;
import pe.edu.upeu.lamb.curriculo.entity.MiembroComision;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.MiembroComisionMapper;
import pe.edu.upeu.lamb.curriculo.repository.ComisionCurricularRepository;
import pe.edu.upeu.lamb.curriculo.repository.MiembroComisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MiembroComisionService {

    private final MiembroComisionRepository miembroComisionRepository;
    private final ComisionCurricularRepository comisionCurricularRepository;
    private final MiembroComisionMapper miembroComisionMapper;

    @Transactional(readOnly = true)
    public List<MiembroComisionResponse> listar(Long idComision) {
        List<MiembroComision> lista = idComision == null
                ? miembroComisionRepository.findAll()
                : miembroComisionRepository.findByComisionId(idComision);
        return lista.stream().map(miembroComisionMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public MiembroComisionResponse obtener(Long id) {
        return miembroComisionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public MiembroComisionResponse crear(MiembroComisionRequest request) {
        validar(request, null);
        MiembroComision miembroComision = miembroComisionMapper.toEntity(request);
        asignarRelaciones(miembroComision, request);
        return miembroComisionMapper.toResponse(miembroComisionRepository.save(miembroComision));
    }

    @Transactional
    public MiembroComisionResponse actualizar(Long id, MiembroComisionRequest request) {
        MiembroComision miembroComision = buscarOFallar(id);
        validar(request, id);
        miembroComision.setIdPersona(request.getIdPersona());
        miembroComision.setRol(request.getRol());
        miembroComision.setFechaIncorporacion(request.getFechaIncorporacion());
        asignarRelaciones(miembroComision, request);
        return miembroComisionMapper.toResponse(miembroComisionRepository.save(miembroComision));
    }

    @Transactional
    public void eliminar(Long id) {
        miembroComisionRepository.delete(buscarOFallar(id));
    }

    MiembroComision buscarOFallar(Long id) {
        return miembroComisionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Miembro de comision no encontrado: " + id));
    }

    private void validar(MiembroComisionRequest request, Long id) {
        boolean duplicado = id == null
                ? miembroComisionRepository.existsByComisionIdAndIdPersona(request.getIdComision(), request.getIdPersona())
                : miembroComisionRepository.existsByComisionIdAndIdPersonaAndIdNot(request.getIdComision(), request.getIdPersona(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de miembro de comision para esa combinacion de: comision, persona");
        }
    }

    private void asignarRelaciones(MiembroComision miembroComision, MiembroComisionRequest request) {
        miembroComision.setComision(comisionCurricularRepository.findById(request.getIdComision())
                .orElseThrow(() -> new ResourceNotFoundException("Comision curricular no encontrada: " + request.getIdComision())));
    }
}
