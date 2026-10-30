package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.TipoAmbienteRequest;
import pe.edu.upeu.lamb.planificacion.dto.TipoAmbienteResponse;
import pe.edu.upeu.lamb.planificacion.entity.TipoAmbiente;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.TipoAmbienteMapper;
import pe.edu.upeu.lamb.planificacion.repository.TipoAmbienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoAmbienteService {

    private final TipoAmbienteRepository tipoAmbienteRepository;
    private final TipoAmbienteMapper tipoAmbienteMapper;

    @Transactional(readOnly = true)
    public List<TipoAmbienteResponse> listar() {
        return tipoAmbienteRepository.findAll().stream()
                .map(tipoAmbienteMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public TipoAmbienteResponse obtener(Long id) {
        return tipoAmbienteMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public TipoAmbienteResponse crear(TipoAmbienteRequest request) {
        validar(request, null);
        TipoAmbiente tipoAmbiente = tipoAmbienteMapper.toEntity(request);
        return tipoAmbienteMapper.toResponse(tipoAmbienteRepository.save(tipoAmbiente));
    }

    @Transactional
    public TipoAmbienteResponse actualizar(Long id, TipoAmbienteRequest request) {
        TipoAmbiente tipoAmbiente = buscarOFallar(id);
        validar(request, id);
        tipoAmbiente.setCodigo(request.getCodigo().trim());
        tipoAmbiente.setNombre(request.getNombre().trim());
        tipoAmbiente.setDescripcion(request.getDescripcion());
        return tipoAmbienteMapper.toResponse(tipoAmbienteRepository.save(tipoAmbiente));
    }

    @Transactional
    public void eliminar(Long id) {
        tipoAmbienteRepository.delete(buscarOFallar(id));
    }

    TipoAmbiente buscarOFallar(Long id) {
        return tipoAmbienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de ambiente no encontrado: " + id));
    }

    private void validar(TipoAmbienteRequest request, Long id) {
        boolean duplicado = id == null
                ? tipoAmbienteRepository.existsByCodigo(request.getCodigo().trim())
                : tipoAmbienteRepository.existsByCodigoAndIdNot(request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de tipo de ambiente para esa combinacion de: codigo");
        }
    }
}
