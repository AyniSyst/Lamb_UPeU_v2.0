package pe.edu.upeu.lamb.matricula.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lamb.matricula.dto.EleccionCursoElectivoRequest;
import pe.edu.upeu.lamb.matricula.dto.EleccionCursoElectivoResponse;
import pe.edu.upeu.lamb.matricula.entity.EleccionCursoElectivo;
import pe.edu.upeu.lamb.matricula.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.matricula.mapper.EleccionCursoElectivoMapper;
import pe.edu.upeu.lamb.matricula.repository.EleccionCursoElectivoRepository;

import java.time.Instant;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EleccionCursoElectivoService {

    private final EleccionCursoElectivoRepository eleccionCursoElectivoRepository;
    private final EleccionCursoElectivoMapper eleccionCursoElectivoMapper;

    public List<EleccionCursoElectivoResponse> listar() {
        return eleccionCursoElectivoRepository.findAll().stream()
                .map(eleccionCursoElectivoMapper::toResponse)
                .toList();
    }

    public EleccionCursoElectivoResponse obtener(Long id) {
        return eleccionCursoElectivoMapper.toResponse(buscarOFallar(id));
    }

    public EleccionCursoElectivoResponse crear(EleccionCursoElectivoRequest request) {
        EleccionCursoElectivo eleccionCursoElectivo = eleccionCursoElectivoMapper.toEntity(request);
        eleccionCursoElectivo.setFechaEleccion(Instant.now());
        return eleccionCursoElectivoMapper.toResponse(eleccionCursoElectivoRepository.save(eleccionCursoElectivo));
    }

    public EleccionCursoElectivoResponse actualizar(Long id, EleccionCursoElectivoRequest request) {
        EleccionCursoElectivo existente = buscarOFallar(id);
        EleccionCursoElectivo eleccionCursoElectivo = eleccionCursoElectivoMapper.toEntity(request);
        eleccionCursoElectivo.setFechaEleccion(Instant.now());
        return eleccionCursoElectivoMapper.toResponse(eleccionCursoElectivoRepository.save(Actualizacion.copiar(eleccionCursoElectivo, existente)));
    }

    public void eliminar(Long id) {
        eleccionCursoElectivoRepository.delete(buscarOFallar(id));
    }

    private EleccionCursoElectivo buscarOFallar(Long id) {
        return eleccionCursoElectivoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EleccionCursoElectivo no encontrada: " + id));
    }
}
