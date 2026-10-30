package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.ProyeccionSocialRequest;
import pe.edu.upeu.lamb.curriculo.dto.ProyeccionSocialResponse;
import pe.edu.upeu.lamb.curriculo.entity.ProyeccionSocial;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.ProyeccionSocialMapper;
import pe.edu.upeu.lamb.curriculo.repository.ProyeccionSocialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProyeccionSocialService {

    private final ProyeccionSocialRepository proyeccionSocialRepository;
    private final ProyeccionSocialMapper proyeccionSocialMapper;

    @Transactional(readOnly = true)
    public List<ProyeccionSocialResponse> listar() {
        return proyeccionSocialRepository.findAll().stream()
                .map(proyeccionSocialMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProyeccionSocialResponse obtener(Long id) {
        return proyeccionSocialMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ProyeccionSocialResponse crear(ProyeccionSocialRequest request) {
        validar(request, null);
        ProyeccionSocial proyeccionSocial = proyeccionSocialMapper.toEntity(request);
        return proyeccionSocialMapper.toResponse(proyeccionSocialRepository.save(proyeccionSocial));
    }

    @Transactional
    public ProyeccionSocialResponse actualizar(Long id, ProyeccionSocialRequest request) {
        ProyeccionSocial proyeccionSocial = buscarOFallar(id);
        validar(request, id);
        proyeccionSocial.setIdPrograma(request.getIdPrograma());
        proyeccionSocial.setNombre(request.getNombre().trim());
        proyeccionSocial.setDescripcion(request.getDescripcion());
        proyeccionSocial.setFechaInicio(request.getFechaInicio());
        proyeccionSocial.setFechaFin(request.getFechaFin());
        proyeccionSocial.setHoras(request.getHoras());
        proyeccionSocial.setEstado(request.getEstado());
        return proyeccionSocialMapper.toResponse(proyeccionSocialRepository.save(proyeccionSocial));
    }

    @Transactional
    public void eliminar(Long id) {
        proyeccionSocialRepository.delete(buscarOFallar(id));
    }

    ProyeccionSocial buscarOFallar(Long id) {
        return proyeccionSocialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto de proyeccion social no encontrado: " + id));
    }

    private void validar(ProyeccionSocialRequest request, Long id) {
        if (request.getFechaFin() != null && request.getFechaFin().isBefore(request.getFechaInicio())) {
            throw new BusinessRuleException("Fecha fin no puede ser anterior a fecha inicio");
        }
    }
}
