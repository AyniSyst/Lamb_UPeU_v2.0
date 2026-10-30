package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.PabellonRequest;
import pe.edu.upeu.lamb.planificacion.dto.PabellonResponse;
import pe.edu.upeu.lamb.planificacion.entity.Pabellon;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.PabellonMapper;
import pe.edu.upeu.lamb.planificacion.repository.PabellonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PabellonService {

    private final PabellonRepository pabellonRepository;
    private final PabellonMapper pabellonMapper;

    @Transactional(readOnly = true)
    public List<PabellonResponse> listar() {
        return pabellonRepository.findAll().stream()
                .map(pabellonMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public PabellonResponse obtener(Long id) {
        return pabellonMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public PabellonResponse crear(PabellonRequest request) {
        validar(request, null);
        Pabellon pabellon = pabellonMapper.toEntity(request);
        return pabellonMapper.toResponse(pabellonRepository.save(pabellon));
    }

    @Transactional
    public PabellonResponse actualizar(Long id, PabellonRequest request) {
        Pabellon pabellon = buscarOFallar(id);
        validar(request, id);
        pabellon.setIdSede(request.getIdSede());
        pabellon.setCodigo(request.getCodigo().trim());
        pabellon.setNombre(request.getNombre().trim());
        pabellon.setNumeroPisos(request.getNumeroPisos());
        return pabellonMapper.toResponse(pabellonRepository.save(pabellon));
    }

    @Transactional
    public void eliminar(Long id) {
        pabellonRepository.delete(buscarOFallar(id));
    }

    Pabellon buscarOFallar(Long id) {
        return pabellonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pabellon no encontrado: " + id));
    }

    private void validar(PabellonRequest request, Long id) {
        boolean duplicado = id == null
                ? pabellonRepository.existsByCodigo(request.getCodigo().trim())
                : pabellonRepository.existsByCodigoAndIdNot(request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de pabellon para esa combinacion de: codigo");
        }
    }
}
