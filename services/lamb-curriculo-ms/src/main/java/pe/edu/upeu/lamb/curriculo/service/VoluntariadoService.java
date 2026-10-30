package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.VoluntariadoRequest;
import pe.edu.upeu.lamb.curriculo.dto.VoluntariadoResponse;
import pe.edu.upeu.lamb.curriculo.entity.Voluntariado;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.VoluntariadoMapper;
import pe.edu.upeu.lamb.curriculo.repository.VoluntariadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoluntariadoService {

    private final VoluntariadoRepository voluntariadoRepository;
    private final VoluntariadoMapper voluntariadoMapper;

    @Transactional(readOnly = true)
    public List<VoluntariadoResponse> listar() {
        return voluntariadoRepository.findAll().stream()
                .map(voluntariadoMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public VoluntariadoResponse obtener(Long id) {
        return voluntariadoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public VoluntariadoResponse crear(VoluntariadoRequest request) {
        validar(request, null);
        Voluntariado voluntariado = voluntariadoMapper.toEntity(request);
        return voluntariadoMapper.toResponse(voluntariadoRepository.save(voluntariado));
    }

    @Transactional
    public VoluntariadoResponse actualizar(Long id, VoluntariadoRequest request) {
        Voluntariado voluntariado = buscarOFallar(id);
        validar(request, id);
        voluntariado.setNombre(request.getNombre().trim());
        voluntariado.setOrganizacion(request.getOrganizacion().trim());
        voluntariado.setTipo(request.getTipo());
        voluntariado.setHorasRequeridas(request.getHorasRequeridas());
        voluntariado.setDescripcion(request.getDescripcion());
        voluntariado.setActivo(request.getActivo());
        return voluntariadoMapper.toResponse(voluntariadoRepository.save(voluntariado));
    }

    @Transactional
    public void eliminar(Long id) {
        voluntariadoRepository.delete(buscarOFallar(id));
    }

    Voluntariado buscarOFallar(Long id) {
        return voluntariadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voluntariado no encontrado: " + id));
    }

    private void validar(VoluntariadoRequest request, Long id) {
    }
}
