package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.PagadorTerceroRequest;
import pe.edu.upeu.ingresospagos.dto.PagadorTerceroResponse;
import pe.edu.upeu.ingresospagos.entity.PagadorTercero;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.PagadorTerceroMapper;
import pe.edu.upeu.ingresospagos.repository.PagadorTerceroRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagadorTerceroService {

    private final PagadorTerceroRepository pagadorTerceroRepository;
    private final PagadorTerceroMapper pagadorTerceroMapper;

    public List<PagadorTerceroResponse> findAll() {
        return pagadorTerceroRepository.findAll().stream()
                .map(pagadorTerceroMapper::toResponse)
                .toList();
    }

    public PagadorTerceroResponse findById(Long id) {
        return pagadorTerceroMapper.toResponse(obtenerPorId(id));
    }

    public PagadorTerceroResponse create(PagadorTerceroRequest request) {
        PagadorTercero pagadorTercero = pagadorTerceroMapper.toEntity(request);
        return pagadorTerceroMapper.toResponse(pagadorTerceroRepository.save(pagadorTercero));
    }

    public PagadorTerceroResponse update(Long id, PagadorTerceroRequest request) {
        PagadorTercero pagadorTercero = obtenerPorId(id);
        pagadorTercero.setIdEstudiante(request.getIdEstudiante());
        pagadorTercero.setNombres(request.getNombres());
        pagadorTercero.setApellidos(request.getApellidos());
        pagadorTercero.setTipoDocumento(request.getTipoDocumento());
        pagadorTercero.setNumeroDocumento(request.getNumeroDocumento());
        pagadorTercero.setTelefono(request.getTelefono());
        pagadorTercero.setEmail(request.getEmail());
        pagadorTercero.setActivo(request.getActivo());
        return pagadorTerceroMapper.toResponse(pagadorTerceroRepository.save(pagadorTercero));
    }

    public void delete(Long id) {
        pagadorTerceroRepository.delete(obtenerPorId(id));
    }

    private PagadorTercero obtenerPorId(Long id) {
        return pagadorTerceroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pagador tercero no encontrado con id: " + id));
    }
}
