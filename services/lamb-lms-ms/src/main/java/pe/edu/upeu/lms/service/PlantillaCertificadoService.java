package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.PlantillaCertificadoRequest;
import pe.edu.upeu.lms.dto.PlantillaCertificadoResponse;
import pe.edu.upeu.lms.entity.PlantillaCertificado;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.PlantillaCertificadoMapper;
import pe.edu.upeu.lms.repository.PlantillaCertificadoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlantillaCertificadoService {

    private final PlantillaCertificadoRepository plantillaCertificadoRepository;
    private final PlantillaCertificadoMapper plantillaCertificadoMapper;

    public List<PlantillaCertificadoResponse> listar() {
        return plantillaCertificadoRepository.findAll().stream()
                .map(plantillaCertificadoMapper::toResponse)
                .toList();
    }

    public PlantillaCertificadoResponse obtener(Long id) {
        return plantillaCertificadoMapper.toResponse(buscarOFallar(id));
    }

    public PlantillaCertificadoResponse crear(PlantillaCertificadoRequest request) {
        PlantillaCertificado plantillaCertificado = plantillaCertificadoMapper.toEntity(request);
        return plantillaCertificadoMapper.toResponse(plantillaCertificadoRepository.save(plantillaCertificado));
    }

    public PlantillaCertificadoResponse actualizar(Long id, PlantillaCertificadoRequest request) {
        PlantillaCertificado plantillaCertificado = buscarOFallar(id);
        plantillaCertificado.setNombre(request.getNombre());
        plantillaCertificado.setContenidoPlantilla(request.getContenidoPlantilla());
        plantillaCertificado.setActivo(request.getActivo());
        return plantillaCertificadoMapper.toResponse(plantillaCertificadoRepository.save(plantillaCertificado));
    }

    public void eliminar(Long id) {
        plantillaCertificadoRepository.delete(buscarOFallar(id));
    }

    private PlantillaCertificado buscarOFallar(Long id) {
        return plantillaCertificadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plantilla de certificado no encontrada: " + id));
    }
}
