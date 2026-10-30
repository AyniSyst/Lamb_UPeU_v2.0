package pe.edu.upeu.lamb.admision.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.admision.dto.CasoEspecialRequest;
import pe.edu.upeu.lamb.admision.dto.CasoEspecialResponse;
import pe.edu.upeu.lamb.admision.dto.ResolverCasoRequest;
import pe.edu.upeu.lamb.admision.entity.*;
import pe.edu.upeu.lamb.admision.exception.BusinessRuleException;
import pe.edu.upeu.lamb.admision.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.admision.mapper.CasoEspecialMapper;
import pe.edu.upeu.lamb.admision.repository.CasoEspecialRepository;
import pe.edu.upeu.lamb.admision.repository.PostulanteRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CasoEspecialService {

    private final CasoEspecialRepository repo;
    private final PostulanteRepository postulanteRepo;
    private final CasoEspecialMapper mapper;

    @Transactional(readOnly = true)
    public List<CasoEspecialResponse> listar(Long idPostulante, TipoCaso tipoCaso) {
        List<CasoEspecial> data;
        if (idPostulante != null) data = repo.findByPostulanteId(idPostulante);
        else if (tipoCaso != null) data = repo.findByTipoCaso(tipoCaso);
        else data = repo.findAll();
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CasoEspecialResponse obtener(Long id) {
        return mapper.toResponse(buscar(id));
    }

    @Transactional
    public CasoEspecialResponse crear(CasoEspecialRequest r) {
        Postulante p = postulanteRepo.findById(r.getIdPostulante())
                .orElseThrow(() -> new ResourceNotFoundException("Postulante no encontrado: " + r.getIdPostulante()));
        CasoEspecial c = CasoEspecial.builder()
                .postulante(p)
                .tipoCaso(r.getTipoCaso())
                .descripcion(r.getDescripcion())
                .requiereAjuste(r.getRequiereAjuste())
                .ajusteOtorgado(r.getAjusteOtorgado())
                .nivelSocioeconomico(r.getNivelSocioeconomico())
                .estado(EstadoCaso.PENDIENTE)
                .fechaRegistro(Instant.now())
                .build();
        return mapper.toResponse(repo.save(c));
    }

    @Transactional
    public CasoEspecialResponse actualizar(Long id, CasoEspecialRequest r) {
        CasoEspecial c = buscar(id);
        if (c.getEstado() != EstadoCaso.PENDIENTE && c.getEstado() != EstadoCaso.EN_REVISION) {
            throw new BusinessRuleException("Solo se edita un caso PENDIENTE o EN_REVISION");
        }
        c.setTipoCaso(r.getTipoCaso());
        c.setDescripcion(r.getDescripcion());
        c.setRequiereAjuste(r.getRequiereAjuste());
        c.setAjusteOtorgado(r.getAjusteOtorgado());
        c.setNivelSocioeconomico(r.getNivelSocioeconomico());
        return mapper.toResponse(c);
    }

    @Transactional
    public CasoEspecialResponse resolver(Long id, ResolverCasoRequest r, Long idResponsable) {
        CasoEspecial c = buscar(id);
        if (c.getEstado() != EstadoCaso.PENDIENTE && c.getEstado() != EstadoCaso.EN_REVISION) {
            throw new BusinessRuleException("Solo se resuelve un caso PENDIENTE o EN_REVISION");
        }
        c.setEstado(Boolean.TRUE.equals(r.getAprobar()) ? EstadoCaso.APROBADO : EstadoCaso.RECHAZADO);
        c.setAjusteOtorgado(r.getAjusteOtorgado());
        c.setIdResponsable(idResponsable);
        c.setFechaResolucion(Instant.now());
        return mapper.toResponse(c);
    }

    @Transactional
    public void eliminar(Long id) {
        CasoEspecial c = buscar(id);
        if (c.getEstado() != EstadoCaso.PENDIENTE) {
            throw new BusinessRuleException("Solo se elimina un caso PENDIENTE");
        }
        repo.delete(c);
    }

    private CasoEspecial buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Caso no encontrado: " + id));
    }
}
