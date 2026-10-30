package pe.edu.upeu.lamb.admision.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.admision.dto.AdjudicacionRequest;
import pe.edu.upeu.lamb.admision.dto.AdjudicacionResponse;
import pe.edu.upeu.lamb.admision.entity.*;
import pe.edu.upeu.lamb.admision.exception.BusinessRuleException;
import pe.edu.upeu.lamb.admision.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.admision.mapper.AdjudicacionMapper;
import pe.edu.upeu.lamb.admision.repository.AdjudicacionRepository;
import pe.edu.upeu.lamb.admision.repository.CalificacionRepository;
import pe.edu.upeu.lamb.admision.repository.ModalidadAdmisionRepository;
import pe.edu.upeu.lamb.admision.repository.PostulanteRepository;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdjudicacionService {

    private static final Set<EstadoPostulante> ESTADOS_NO_ADJUDICABLES =
            Set.of(EstadoPostulante.NO_ADMITIDO, EstadoPostulante.ANULADO);

    private final AdjudicacionRepository repo;
    private final PostulanteRepository postulanteRepo;
    private final ModalidadAdmisionRepository modalidadRepo;
    private final CalificacionRepository calificacionRepo;
    private final ReglasInscripcionService reglas;
    private final AdjudicacionMapper mapper;

    @Transactional(readOnly = true)
    public List<AdjudicacionResponse> listar(Long idModalidad) {
        List<AdjudicacionVacante> data = idModalidad != null
                ? repo.findByModalidadId(idModalidad)
                : repo.findAll();
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public AdjudicacionResponse obtener(Long id) {
        return mapper.toResponse(buscar(id));
    }

    /**
     * Adjudicar consume una vacante de la modalidad. Igual que al aprobar una
     * inscripcion, la fila de la modalidad se bloquea (SELECT ... FOR UPDATE)
     * para que dos adjudicaciones simultaneas no superen el cupo. Si alguna
     * regla falla, rollback: ni adjudicacion ni contador movido.
     */
    @Transactional
    public AdjudicacionResponse crear(AdjudicacionRequest r) {
        if (repo.existsByPostulanteId(r.getIdPostulante())) {
            throw new BusinessRuleException("El postulante ya tiene una adjudicacion registrada");
        }
        Postulante p = postulanteRepo.findById(r.getIdPostulante())
                .orElseThrow(() -> new ResourceNotFoundException("Postulante no encontrado: " + r.getIdPostulante()));
        if (ESTADOS_NO_ADJUDICABLES.contains(p.getEstado())) {
            throw new BusinessRuleException("No se puede adjudicar a un postulante en estado " + p.getEstado());
        }
        if (!p.getModalidad().getId().equals(r.getIdModalidad())) {
            throw new BusinessRuleException("El postulante esta registrado en la modalidad "
                    + p.getModalidad().getId() + ", no en " + r.getIdModalidad());
        }
        if (!calificacionRepo.existsByPostulanteId(p.getId())) {
            throw new BusinessRuleException("El postulante no tiene calificacion de examen registrada");
        }

        ModalidadAdmision m = modalidadRepo.findByIdParaReserva(r.getIdModalidad())
                .orElseThrow(() -> new ResourceNotFoundException("Modalidad no encontrada: " + r.getIdModalidad()));
        reglas.reservarVacante(m);

        AdjudicacionVacante a = AdjudicacionVacante.builder()
                .postulante(p)
                .modalidad(m)
                .idPrograma(r.getIdPrograma())
                .puntajeFinal(r.getPuntajeFinal())
                .ordenMerito(r.getOrdenMerito())
                .numeroResolucion(r.getNumeroResolucion())
                .observacion(r.getObservacion())
                .estado(EstadoAdjudicacion.ADJUDICADA)
                .fechaAdjudicacion(Instant.now())
                .build();
        return mapper.toResponse(repo.save(a));
    }

    @Transactional
    public AdjudicacionResponse actualizar(Long id, AdjudicacionRequest r) {
        AdjudicacionVacante a = buscar(id);
        a.setPuntajeFinal(r.getPuntajeFinal());
        a.setOrdenMerito(r.getOrdenMerito());
        a.setNumeroResolucion(r.getNumeroResolucion());
        a.setObservacion(r.getObservacion());
        return mapper.toResponse(a);
    }

    @Transactional
    public AdjudicacionResponse publicar(Long id) {
        AdjudicacionVacante a = buscar(id);
        if (a.getEstado() != EstadoAdjudicacion.ADJUDICADA) {
            throw new BusinessRuleException("Solo se publica una adjudicacion en estado ADJUDICADA");
        }
        a.setEstado(EstadoAdjudicacion.PUBLICADA);
        a.setFechaPublicacion(Instant.now());
        return mapper.toResponse(a);
    }

    @Transactional
    public void eliminar(Long id) {
        AdjudicacionVacante a = buscar(id);
        if (a.getEstado() == EstadoAdjudicacion.PUBLICADA) {
            throw new BusinessRuleException("No se puede eliminar una adjudicacion PUBLICADA; use ANULADA");
        }
        // Compensacion: si la adjudicacion seguia vigente, devuelve la vacante
        if (a.getEstado() == EstadoAdjudicacion.ADJUDICADA) {
            ModalidadAdmision m = modalidadRepo.findByIdParaReserva(a.getModalidad().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Modalidad no encontrada: " + a.getModalidad().getId()));
            reglas.liberarVacante(m);
        }
        repo.delete(a);
    }

    private AdjudicacionVacante buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adjudicacion no encontrada: " + id));
    }
}
