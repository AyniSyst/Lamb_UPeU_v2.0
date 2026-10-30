package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.ModalidadRequest;
import pe.edu.upeu.lamb.curriculo.dto.ModalidadResponse;
import pe.edu.upeu.lamb.curriculo.entity.Modalidad;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.ModalidadMapper;
import pe.edu.upeu.lamb.curriculo.repository.ModalidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModalidadService {

    private final ModalidadRepository modalidadRepository;
    private final ModalidadMapper modalidadMapper;

    @Transactional(readOnly = true)
    public List<ModalidadResponse> listar() {
        return modalidadRepository.findAll().stream()
                .map(modalidadMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ModalidadResponse obtener(Long id) {
        return modalidadMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ModalidadResponse crear(ModalidadRequest request) {
        validar(request, null);
        Modalidad modalidad = modalidadMapper.toEntity(request);
        return modalidadMapper.toResponse(modalidadRepository.save(modalidad));
    }

    @Transactional
    public ModalidadResponse actualizar(Long id, ModalidadRequest request) {
        Modalidad modalidad = buscarOFallar(id);
        validar(request, id);
        modalidad.setCodigo(request.getCodigo().trim());
        modalidad.setNombre(request.getNombre().trim());
        modalidad.setDescripcion(request.getDescripcion());
        return modalidadMapper.toResponse(modalidadRepository.save(modalidad));
    }

    @Transactional
    public void eliminar(Long id) {
        modalidadRepository.delete(buscarOFallar(id));
    }

    Modalidad buscarOFallar(Long id) {
        return modalidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Modalidad no encontrada: " + id));
    }

    private void validar(ModalidadRequest request, Long id) {
        boolean duplicado = id == null
                ? modalidadRepository.existsByCodigo(request.getCodigo().trim())
                : modalidadRepository.existsByCodigoAndIdNot(request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de modalidad para esa combinacion de: codigo");
        }
    }
}
