package pe.edu.upeu.lamb.admision.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.admision.dto.CalificacionRequest;
import pe.edu.upeu.lamb.admision.dto.CalificacionResponse;
import pe.edu.upeu.lamb.admision.entity.*;
import pe.edu.upeu.lamb.admision.exception.BusinessRuleException;
import pe.edu.upeu.lamb.admision.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.admision.mapper.CalificacionMapper;
import pe.edu.upeu.lamb.admision.repository.CalificacionRepository;
import pe.edu.upeu.lamb.admision.repository.ExamenAdmisionRepository;
import pe.edu.upeu.lamb.admision.repository.PostulanteRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalificacionService {

    private final CalificacionRepository repo;
    private final ExamenAdmisionRepository examenRepo;
    private final PostulanteRepository postulanteRepo;
    private final CalificacionMapper mapper;

    @Transactional(readOnly = true)
    public List<CalificacionResponse> listar(Long idExamen) {
        List<CalificacionExamen> data = idExamen != null
                ? repo.findByExamenId(idExamen)
                : repo.findAll();
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CalificacionResponse obtener(Long id) {
        return mapper.toResponse(buscar(id));
    }

    @Transactional
    public CalificacionResponse crear(CalificacionRequest r) {
        if (repo.existsByExamenIdAndPostulanteId(r.getIdExamen(), r.getIdPostulante())) {
            throw new BusinessRuleException("El postulante ya tiene calificacion registrada en este examen");
        }
        ExamenAdmision examen = examenRepo.findById(r.getIdExamen())
                .orElseThrow(() -> new ResourceNotFoundException("Examen no encontrado: " + r.getIdExamen()));
        Postulante postulante = postulanteRepo.findById(r.getIdPostulante())
                .orElseThrow(() -> new ResourceNotFoundException("Postulante no encontrado: " + r.getIdPostulante()));

        CalificacionExamen c = CalificacionExamen.builder()
                .examen(examen)
                .postulante(postulante)
                .puntajeTotal(r.getPuntajeTotal())
                .puntajeAptitud(r.getPuntajeAptitud())
                .puntajeConocimiento(r.getPuntajeConocimiento())
                .respuestasCorrectas(r.getRespuestasCorrectas())
                .respuestasIncorrectas(r.getRespuestasIncorrectas())
                .respuestasBlanco(r.getRespuestasBlanco())
                .idSupervisor(r.getIdSupervisor())
                .idEncargado(r.getIdEncargado())
                .observacion(r.getObservacion())
                .fechaCalificacion(Instant.now())
                .build();
        return mapper.toResponse(repo.save(c));
    }

    @Transactional
    public CalificacionResponse actualizar(Long id, CalificacionRequest r) {
        CalificacionExamen c = buscar(id);
        c.setPuntajeTotal(r.getPuntajeTotal());
        c.setPuntajeAptitud(r.getPuntajeAptitud());
        c.setPuntajeConocimiento(r.getPuntajeConocimiento());
        c.setRespuestasCorrectas(r.getRespuestasCorrectas());
        c.setRespuestasIncorrectas(r.getRespuestasIncorrectas());
        c.setRespuestasBlanco(r.getRespuestasBlanco());
        c.setIdSupervisor(r.getIdSupervisor());
        c.setIdEncargado(r.getIdEncargado());
        c.setObservacion(r.getObservacion());
        return mapper.toResponse(c);
    }

    @Transactional
    public void eliminar(Long id) {
        repo.delete(buscar(id));
    }

    private CalificacionExamen buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calificacion no encontrada: " + id));
    }
}
