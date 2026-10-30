package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.AmbienteRequest;
import pe.edu.upeu.lamb.planificacion.dto.AmbienteResponse;
import pe.edu.upeu.lamb.planificacion.entity.Ambiente;
import pe.edu.upeu.lamb.planificacion.entity.Pabellon;
import pe.edu.upeu.lamb.planificacion.entity.TipoAmbiente;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.AmbienteMapper;
import pe.edu.upeu.lamb.planificacion.repository.AmbienteRepository;
import pe.edu.upeu.lamb.planificacion.repository.PabellonRepository;
import pe.edu.upeu.lamb.planificacion.repository.TipoAmbienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AmbienteService {

    private final AmbienteRepository ambienteRepository;
    private final PabellonRepository pabellonRepository;
    private final TipoAmbienteRepository tipoAmbienteRepository;
    private final AmbienteMapper ambienteMapper;

    @Transactional(readOnly = true)
    public List<AmbienteResponse> listar(Long idPabellon) {
        List<Ambiente> lista = idPabellon == null
                ? ambienteRepository.findAll()
                : ambienteRepository.findByPabellonId(idPabellon);
        return lista.stream().map(ambienteMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public AmbienteResponse obtener(Long id) {
        return ambienteMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public AmbienteResponse crear(AmbienteRequest request) {
        validar(request, null);
        Ambiente ambiente = ambienteMapper.toEntity(request);
        asignarRelaciones(ambiente, request);
        return ambienteMapper.toResponse(ambienteRepository.save(ambiente));
    }

    @Transactional
    public AmbienteResponse actualizar(Long id, AmbienteRequest request) {
        Ambiente ambiente = buscarOFallar(id);
        validar(request, id);
        ambiente.setCodigo(request.getCodigo().trim());
        ambiente.setNombre(request.getNombre().trim());
        ambiente.setPiso(request.getPiso());
        ambiente.setAforo(request.getAforo());
        ambiente.setActivo(request.getActivo());
        asignarRelaciones(ambiente, request);
        return ambienteMapper.toResponse(ambienteRepository.save(ambiente));
    }

    @Transactional
    public void eliminar(Long id) {
        ambienteRepository.delete(buscarOFallar(id));
    }

    Ambiente buscarOFallar(Long id) {
        return ambienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ambiente no encontrado: " + id));
    }

    private void validar(AmbienteRequest request, Long id) {
        boolean duplicado = id == null
                ? ambienteRepository.existsByCodigo(request.getCodigo().trim())
                : ambienteRepository.existsByCodigoAndIdNot(request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de ambiente para esa combinacion de: codigo");
        }
    }

    private void asignarRelaciones(Ambiente ambiente, AmbienteRequest request) {
        ambiente.setPabellon(pabellonRepository.findById(request.getIdPabellon())
                .orElseThrow(() -> new ResourceNotFoundException("Pabellon no encontrado: " + request.getIdPabellon())));
        ambiente.setTipoAmbiente(tipoAmbienteRepository.findById(request.getIdTipoAmbiente())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de ambiente no encontrado: " + request.getIdTipoAmbiente())));
    }
}
