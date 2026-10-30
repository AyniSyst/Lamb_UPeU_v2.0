package pe.edu.upeu.lamb.matricula.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.matricula.dto.CargaMinimaAutorizadaRequest;
import pe.edu.upeu.lamb.matricula.dto.CargaMinimaAutorizadaResponse;
import pe.edu.upeu.lamb.matricula.entity.CargaMinimaAutorizada;
import pe.edu.upeu.lamb.matricula.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.matricula.mapper.CargaMinimaAutorizadaMapper;
import pe.edu.upeu.lamb.matricula.repository.CargaMinimaAutorizadaRepository;

import java.time.Instant;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CargaMinimaAutorizadaService {

    private final CargaMinimaAutorizadaRepository cargaMinimaAutorizadaRepository;
    private final CargaMinimaAutorizadaMapper cargaMinimaAutorizadaMapper;

    public List<CargaMinimaAutorizadaResponse> listar() {
        return cargaMinimaAutorizadaRepository.findAll().stream()
                .map(cargaMinimaAutorizadaMapper::toResponse)
                .toList();
    }

    public CargaMinimaAutorizadaResponse obtener(Long id) {
        return cargaMinimaAutorizadaMapper.toResponse(buscarOFallar(id));
    }

    public CargaMinimaAutorizadaResponse crear(CargaMinimaAutorizadaRequest request) {
        CargaMinimaAutorizada cargaMinimaAutorizada = cargaMinimaAutorizadaMapper.toEntity(request);
        cargaMinimaAutorizada.setFechaAutorizacion(Instant.now());
        return cargaMinimaAutorizadaMapper.toResponse(cargaMinimaAutorizadaRepository.save(cargaMinimaAutorizada));
    }

    public CargaMinimaAutorizadaResponse actualizar(Long id, CargaMinimaAutorizadaRequest request) {
        CargaMinimaAutorizada existente = buscarOFallar(id);
        CargaMinimaAutorizada cargaMinimaAutorizada = cargaMinimaAutorizadaMapper.toEntity(request);
        cargaMinimaAutorizada.setFechaAutorizacion(Instant.now());
        return cargaMinimaAutorizadaMapper.toResponse(cargaMinimaAutorizadaRepository.save(Actualizacion.copiar(cargaMinimaAutorizada, existente)));
    }

    public void eliminar(Long id) {
        cargaMinimaAutorizadaRepository.delete(buscarOFallar(id));
    }

    private CargaMinimaAutorizada buscarOFallar(Long id) {
        return cargaMinimaAutorizadaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CargaMinimaAutorizada no encontrada: " + id));
    }
}
