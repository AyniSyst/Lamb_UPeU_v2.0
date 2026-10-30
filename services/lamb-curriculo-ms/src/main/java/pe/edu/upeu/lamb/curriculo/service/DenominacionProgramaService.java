package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.DenominacionProgramaRequest;
import pe.edu.upeu.lamb.curriculo.dto.DenominacionProgramaResponse;
import pe.edu.upeu.lamb.curriculo.entity.DenominacionPrograma;
import pe.edu.upeu.lamb.curriculo.entity.Modalidad;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.DenominacionProgramaMapper;
import pe.edu.upeu.lamb.curriculo.repository.DenominacionProgramaRepository;
import pe.edu.upeu.lamb.curriculo.repository.ModalidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DenominacionProgramaService {

    private final DenominacionProgramaRepository denominacionProgramaRepository;
    private final ModalidadRepository modalidadRepository;
    private final DenominacionProgramaMapper denominacionProgramaMapper;

    @Transactional(readOnly = true)
    public List<DenominacionProgramaResponse> listar() {
        return denominacionProgramaRepository.findAll().stream()
                .map(denominacionProgramaMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public DenominacionProgramaResponse obtener(Long id) {
        return denominacionProgramaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public DenominacionProgramaResponse crear(DenominacionProgramaRequest request) {
        validar(request, null);
        DenominacionPrograma denominacionPrograma = denominacionProgramaMapper.toEntity(request);
        asignarRelaciones(denominacionPrograma, request);
        return denominacionProgramaMapper.toResponse(denominacionProgramaRepository.save(denominacionPrograma));
    }

    @Transactional
    public DenominacionProgramaResponse actualizar(Long id, DenominacionProgramaRequest request) {
        DenominacionPrograma denominacionPrograma = buscarOFallar(id);
        validar(request, id);
        denominacionPrograma.setIdPrograma(request.getIdPrograma());
        denominacionPrograma.setDenominacion(request.getDenominacion().trim());
        denominacionPrograma.setDuracionSemestres(request.getDuracionSemestres());
        denominacionPrograma.setResolucion(request.getResolucion());
        asignarRelaciones(denominacionPrograma, request);
        return denominacionProgramaMapper.toResponse(denominacionProgramaRepository.save(denominacionPrograma));
    }

    @Transactional
    public void eliminar(Long id) {
        denominacionProgramaRepository.delete(buscarOFallar(id));
    }

    DenominacionPrograma buscarOFallar(Long id) {
        return denominacionProgramaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Denominacion del programa no encontrada: " + id));
    }

    private void validar(DenominacionProgramaRequest request, Long id) {
        boolean duplicado = id == null
                ? denominacionProgramaRepository.existsByIdPrograma(request.getIdPrograma())
                : denominacionProgramaRepository.existsByIdProgramaAndIdNot(request.getIdPrograma(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de denominacion del programa para esa combinacion de: programa");
        }
    }

    private void asignarRelaciones(DenominacionPrograma denominacionPrograma, DenominacionProgramaRequest request) {
        denominacionPrograma.setModalidad(modalidadRepository.findById(request.getIdModalidad())
                .orElseThrow(() -> new ResourceNotFoundException("Modalidad no encontrada: " + request.getIdModalidad())));
    }
}
