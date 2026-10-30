package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.UniversidadOrigenRequest;
import pe.edu.upeu.lamb.curriculo.dto.UniversidadOrigenResponse;
import pe.edu.upeu.lamb.curriculo.entity.UniversidadOrigen;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.UniversidadOrigenMapper;
import pe.edu.upeu.lamb.curriculo.repository.UniversidadOrigenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversidadOrigenService {

    private final UniversidadOrigenRepository universidadOrigenRepository;
    private final UniversidadOrigenMapper universidadOrigenMapper;

    @Transactional(readOnly = true)
    public List<UniversidadOrigenResponse> listar() {
        return universidadOrigenRepository.findAll().stream()
                .map(universidadOrigenMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public UniversidadOrigenResponse obtener(Long id) {
        return universidadOrigenMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public UniversidadOrigenResponse crear(UniversidadOrigenRequest request) {
        validar(request, null);
        UniversidadOrigen universidadOrigen = universidadOrigenMapper.toEntity(request);
        return universidadOrigenMapper.toResponse(universidadOrigenRepository.save(universidadOrigen));
    }

    @Transactional
    public UniversidadOrigenResponse actualizar(Long id, UniversidadOrigenRequest request) {
        UniversidadOrigen universidadOrigen = buscarOFallar(id);
        validar(request, id);
        universidadOrigen.setCodigo(request.getCodigo().trim());
        universidadOrigen.setNombre(request.getNombre().trim());
        universidadOrigen.setPais(request.getPais().trim());
        universidadOrigen.setTipo(request.getTipo());
        return universidadOrigenMapper.toResponse(universidadOrigenRepository.save(universidadOrigen));
    }

    @Transactional
    public void eliminar(Long id) {
        universidadOrigenRepository.delete(buscarOFallar(id));
    }

    UniversidadOrigen buscarOFallar(Long id) {
        return universidadOrigenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Universidad de origen no encontrada: " + id));
    }

    private void validar(UniversidadOrigenRequest request, Long id) {
        boolean duplicado = id == null
                ? universidadOrigenRepository.existsByCodigo(request.getCodigo().trim())
                : universidadOrigenRepository.existsByCodigoAndIdNot(request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de universidad de origen para esa combinacion de: codigo");
        }
    }
}
