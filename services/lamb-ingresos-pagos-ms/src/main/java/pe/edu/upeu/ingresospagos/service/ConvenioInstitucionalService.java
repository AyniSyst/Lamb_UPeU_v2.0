package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.ConvenioInstitucionalRequest;
import pe.edu.upeu.ingresospagos.dto.ConvenioInstitucionalResponse;
import pe.edu.upeu.ingresospagos.entity.ConvenioInstitucional;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.ConvenioInstitucionalMapper;
import pe.edu.upeu.ingresospagos.repository.ConvenioInstitucionalRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConvenioInstitucionalService {

    private final ConvenioInstitucionalRepository convenioInstitucionalRepository;
    private final ConvenioInstitucionalMapper convenioInstitucionalMapper;

    public List<ConvenioInstitucionalResponse> findAll() {
        return convenioInstitucionalRepository.findAll().stream()
                .map(convenioInstitucionalMapper::toResponse)
                .toList();
    }

    public ConvenioInstitucionalResponse findById(Long id) {
        return convenioInstitucionalMapper.toResponse(obtenerPorId(id));
    }

    public ConvenioInstitucionalResponse create(ConvenioInstitucionalRequest request) {
        ConvenioInstitucional convenioInstitucional = convenioInstitucionalMapper.toEntity(request);
        return convenioInstitucionalMapper.toResponse(convenioInstitucionalRepository.save(convenioInstitucional));
    }

    public ConvenioInstitucionalResponse update(Long id, ConvenioInstitucionalRequest request) {
        ConvenioInstitucional convenioInstitucional = obtenerPorId(id);
        convenioInstitucional.setNombreEmpresa(request.getNombreEmpresa());
        convenioInstitucional.setRuc(request.getRuc());
        convenioInstitucional.setPorcentajeDescuento(request.getPorcentajeDescuento());
        convenioInstitucional.setFechaInicio(request.getFechaInicio());
        convenioInstitucional.setFechaFin(request.getFechaFin());
        convenioInstitucional.setActivo(request.getActivo());
        return convenioInstitucionalMapper.toResponse(convenioInstitucionalRepository.save(convenioInstitucional));
    }

    public void delete(Long id) {
        convenioInstitucionalRepository.delete(obtenerPorId(id));
    }

    private ConvenioInstitucional obtenerPorId(Long id) {
        return convenioInstitucionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convenio institucional no encontrado con id: " + id));
    }
}
