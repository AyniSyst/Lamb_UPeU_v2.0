package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.EquipamientoAmbienteRequest;
import pe.edu.upeu.lamb.planificacion.dto.EquipamientoAmbienteResponse;
import pe.edu.upeu.lamb.planificacion.entity.Ambiente;
import pe.edu.upeu.lamb.planificacion.entity.EquipamientoAmbiente;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.EquipamientoAmbienteMapper;
import pe.edu.upeu.lamb.planificacion.repository.AmbienteRepository;
import pe.edu.upeu.lamb.planificacion.repository.EquipamientoAmbienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipamientoAmbienteService {

    private final EquipamientoAmbienteRepository equipamientoAmbienteRepository;
    private final AmbienteRepository ambienteRepository;
    private final EquipamientoAmbienteMapper equipamientoAmbienteMapper;

    @Transactional(readOnly = true)
    public List<EquipamientoAmbienteResponse> listar(Long idAmbiente) {
        List<EquipamientoAmbiente> lista = idAmbiente == null
                ? equipamientoAmbienteRepository.findAll()
                : equipamientoAmbienteRepository.findByAmbienteId(idAmbiente);
        return lista.stream().map(equipamientoAmbienteMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public EquipamientoAmbienteResponse obtener(Long id) {
        return equipamientoAmbienteMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public EquipamientoAmbienteResponse crear(EquipamientoAmbienteRequest request) {
        validar(request, null);
        EquipamientoAmbiente equipamientoAmbiente = equipamientoAmbienteMapper.toEntity(request);
        asignarRelaciones(equipamientoAmbiente, request);
        return equipamientoAmbienteMapper.toResponse(equipamientoAmbienteRepository.save(equipamientoAmbiente));
    }

    @Transactional
    public EquipamientoAmbienteResponse actualizar(Long id, EquipamientoAmbienteRequest request) {
        EquipamientoAmbiente equipamientoAmbiente = buscarOFallar(id);
        validar(request, id);
        equipamientoAmbiente.setNombre(request.getNombre().trim());
        equipamientoAmbiente.setCantidad(request.getCantidad());
        equipamientoAmbiente.setCodigoPatrimonial(request.getCodigoPatrimonial());
        equipamientoAmbiente.setEstado(request.getEstado());
        asignarRelaciones(equipamientoAmbiente, request);
        return equipamientoAmbienteMapper.toResponse(equipamientoAmbienteRepository.save(equipamientoAmbiente));
    }

    @Transactional
    public void eliminar(Long id) {
        equipamientoAmbienteRepository.delete(buscarOFallar(id));
    }

    EquipamientoAmbiente buscarOFallar(Long id) {
        return equipamientoAmbienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipamiento del ambiente no encontrado: " + id));
    }

    private void validar(EquipamientoAmbienteRequest request, Long id) {
    }

    private void asignarRelaciones(EquipamientoAmbiente equipamientoAmbiente, EquipamientoAmbienteRequest request) {
        equipamientoAmbiente.setAmbiente(ambienteRepository.findById(request.getIdAmbiente())
                .orElseThrow(() -> new ResourceNotFoundException("Ambiente no encontrado: " + request.getIdAmbiente())));
    }
}
