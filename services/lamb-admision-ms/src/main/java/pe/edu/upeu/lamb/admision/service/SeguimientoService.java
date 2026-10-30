package pe.edu.upeu.lamb.admision.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.admision.dto.SeguimientoRequest;
import pe.edu.upeu.lamb.admision.dto.SeguimientoResponse;
import pe.edu.upeu.lamb.admision.entity.*;
import pe.edu.upeu.lamb.admision.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.admision.mapper.SeguimientoMapper;
import pe.edu.upeu.lamb.admision.repository.PostulanteRepository;
import pe.edu.upeu.lamb.admision.repository.SeguimientoRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeguimientoService {

    private final SeguimientoRepository repo;
    private final PostulanteRepository postulanteRepo;
    private final SeguimientoMapper mapper;

    @Transactional(readOnly = true)
    public List<SeguimientoResponse> listar(Long idPostulante) {
        List<SeguimientoPostulante> data = idPostulante != null
                ? repo.findByPostulanteId(idPostulante)
                : repo.findAll();
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public SeguimientoResponse obtener(Long id) {
        return mapper.toResponse(buscar(id));
    }

    @Transactional
    public SeguimientoResponse crear(SeguimientoRequest r, Long idResponsable) {
        Postulante p = postulanteRepo.findById(r.getIdPostulante())
                .orElseThrow(() -> new ResourceNotFoundException("Postulante no encontrado: " + r.getIdPostulante()));
        SeguimientoPostulante s = SeguimientoPostulante.builder()
                .postulante(p)
                .canal(r.getCanal())
                .medioCaptacion(r.getMedioCaptacion())
                .asunto(r.getAsunto())
                .detalle(r.getDetalle())
                .resultado(r.getResultado())
                .idResponsable(idResponsable)
                .fechaContacto(Instant.now())
                .proximoContacto(r.getProximoContacto())
                .build();
        return mapper.toResponse(repo.save(s));
    }

    @Transactional
    public SeguimientoResponse actualizar(Long id, SeguimientoRequest r) {
        SeguimientoPostulante s = buscar(id);
        s.setCanal(r.getCanal());
        s.setMedioCaptacion(r.getMedioCaptacion());
        s.setAsunto(r.getAsunto());
        s.setDetalle(r.getDetalle());
        s.setResultado(r.getResultado());
        s.setProximoContacto(r.getProximoContacto());
        return mapper.toResponse(s);
    }

    @Transactional
    public void eliminar(Long id) {
        repo.delete(buscar(id));
    }

    private SeguimientoPostulante buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seguimiento no encontrado: " + id));
    }
}
