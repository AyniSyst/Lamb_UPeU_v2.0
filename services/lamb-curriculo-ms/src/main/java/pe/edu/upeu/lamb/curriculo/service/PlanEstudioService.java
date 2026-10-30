package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.PlanCursoRequest;
import pe.edu.upeu.lamb.curriculo.dto.PlanEstudioRequest;
import pe.edu.upeu.lamb.curriculo.dto.PlanEstudioResponse;
import pe.edu.upeu.lamb.curriculo.dto.PrerrequisitoRequest;
import pe.edu.upeu.lamb.curriculo.entity.EstadoPlan;
import pe.edu.upeu.lamb.curriculo.entity.Modalidad;
import pe.edu.upeu.lamb.curriculo.entity.PlanCurso;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.entity.Prerrequisito;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.PlanEstudioMapper;
import pe.edu.upeu.lamb.curriculo.repository.AreaCurricularRepository;
import pe.edu.upeu.lamb.curriculo.repository.CursoCatalogoRepository;
import pe.edu.upeu.lamb.curriculo.repository.ModalidadRepository;
import pe.edu.upeu.lamb.curriculo.repository.PlanCursoRepository;
import pe.edu.upeu.lamb.curriculo.repository.TipoCursoRepository;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import pe.edu.upeu.lamb.curriculo.repository.PrerrequisitoRepository;
import lombok.RequiredArgsConstructor;
import pe.edu.upeu.lamb.curriculo.dto.VigenciaCursoResponse;
import pe.edu.upeu.lamb.curriculo.messaging.CurriculoEventosProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanEstudioService {

    private final PlanEstudioRepository planEstudioRepository;
    private final PlanCursoRepository planCursoRepository;
    private final PrerrequisitoRepository prerrequisitoRepository;
    private final CursoCatalogoRepository cursoCatalogoRepository;
    private final TipoCursoRepository tipoCursoRepository;
    private final AreaCurricularRepository areaCurricularRepository;
    private final ModalidadRepository modalidadRepository;
    private final PlanEstudioMapper planEstudioMapper;
    private final CurriculoEventosProducer producer;

    @Value("${spring.application.name}")
    private String nombreServicio;

    @Transactional(readOnly = true)
    public List<PlanEstudioResponse> listar(Long idPrograma) {
        List<PlanEstudio> planes = idPrograma == null
                ? planEstudioRepository.findAll()
                : planEstudioRepository.findByIdPrograma(idPrograma);
        return planes.stream().map(planEstudioMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PlanEstudioResponse obtener(Long id) {
        return planEstudioMapper.toResponse(buscarOFallar(id));
    }

    @Transactional(readOnly = true)
    public PlanEstudioResponse obtenerVigente(Long idPrograma) {
        return planEstudioRepository.findByIdProgramaAndEstado(idPrograma, EstadoPlan.VIGENTE)
                .map(planEstudioMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("El programa " + idPrograma + " no tiene plan vigente"));
    }

    // Consulta sincrona de planificacion (Feign): no lanza 404, responde enPlanVigente=false
    @Transactional(readOnly = true)
    public VigenciaCursoResponse consultarVigencia(String codigoCurso) {
        String codigo = codigoCurso.trim().toUpperCase();
        return planCursoRepository.findFirstByCursoCatalogoCodigoAndPlanEstado(codigo, EstadoPlan.VIGENTE)
                .map(c -> VigenciaCursoResponse.builder()
                        .codigoCurso(codigo)
                        .enPlanVigente(true)
                        .idPlan(c.getPlan().getId())
                        .version(c.getPlan().getVersion())
                        .nombreCurso(c.getNombreCurso())
                        .creditos(c.getCreditos())
                        .build())
                .orElseGet(() -> VigenciaCursoResponse.builder().codigoCurso(codigo).enPlanVigente(false).build());
    }

    @Transactional
    public PlanEstudioResponse crear(PlanEstudioRequest request) {
        if (planEstudioRepository.existsByIdProgramaAndVersion(request.getIdPrograma(), request.getVersion().trim())) {
            throw new BusinessRuleException("Ya existe el plan " + request.getVersion() + " para el programa " + request.getIdPrograma());
        }
        PlanEstudio plan = planEstudioMapper.toEntity(request);
        plan.setModalidad(buscarModalidad(request.getIdModalidad()));
        return planEstudioMapper.toResponse(planEstudioRepository.save(plan));
    }

    // Solo un plan en BORRADOR se edita: uno VIGENTE o DEROGADO ya es historia oficial
    @Transactional
    public PlanEstudioResponse actualizar(Long id, PlanEstudioRequest request) {
        PlanEstudio plan = buscarOFallar(id);
        validarBorrador(plan);
        String version = request.getVersion().trim();
        boolean cambiaClave = !plan.getIdPrograma().equals(request.getIdPrograma()) || !plan.getVersion().equals(version);
        if (cambiaClave && planEstudioRepository.existsByIdProgramaAndVersion(request.getIdPrograma(), version)) {
            throw new BusinessRuleException("Ya existe el plan " + version + " para el programa " + request.getIdPrograma());
        }
        plan.setIdPrograma(request.getIdPrograma());
        plan.setVersion(version);
        plan.setNombre(request.getNombre().trim());
        plan.setModalidad(buscarModalidad(request.getIdModalidad()));
        return planEstudioMapper.toResponse(planEstudioRepository.save(plan));
    }

    @Transactional
    public void eliminar(Long id) {
        PlanEstudio plan = buscarOFallar(id);
        validarBorrador(plan);
        planEstudioRepository.delete(plan);
    }

    @Transactional
    public PlanEstudioResponse agregarCurso(Long idPlan, PlanCursoRequest request) {
        PlanEstudio plan = buscarOFallar(idPlan);
        validarBorrador(plan);
        PlanCurso curso = planEstudioMapper.toEntity(request, plan);
        curso.setCursoCatalogo(cursoCatalogoRepository.findById(request.getIdCursoCatalogo())
                .orElseThrow(() -> new ResourceNotFoundException("Curso del catalogo no encontrado: " + request.getIdCursoCatalogo())));
        curso.setTipo(tipoCursoRepository.findById(request.getIdTipoCurso())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de curso no encontrado: " + request.getIdTipoCurso())));
        if (request.getIdAreaCurricular() != null) {
            curso.setAreaCurricular(areaCurricularRepository.findById(request.getIdAreaCurricular())
                    .orElseThrow(() -> new ResourceNotFoundException("Area curricular no encontrada: " + request.getIdAreaCurricular())));
        }
        if (planCursoRepository.existsByPlanIdAndCursoCatalogoId(idPlan, curso.getCursoCatalogo().getId())) {
            throw new BusinessRuleException("El curso " + curso.getCodigoCurso() + " ya esta en el plan");
        }
        plan.getCursos().add(curso);
        return planEstudioMapper.toResponse(planEstudioRepository.saveAndFlush(plan));
    }

    @Transactional
    public PlanEstudioResponse eliminarCurso(Long idPlan, Long idPlanCurso) {
        PlanEstudio plan = buscarOFallar(idPlan);
        validarBorrador(plan);
        PlanCurso curso = buscarCursoOFallar(idPlan, idPlanCurso);
        if (prerrequisitoRepository.existsByRequisitoId(idPlanCurso)) {
            throw new BusinessRuleException("El curso " + curso.getCodigoCurso() + " es prerrequisito de otro curso del plan");
        }
        plan.getCursos().remove(curso);
        return planEstudioMapper.toResponse(planEstudioRepository.saveAndFlush(plan));
    }

    @Transactional
    public PlanEstudioResponse agregarPrerrequisito(Long idPlan, Long idPlanCurso, PrerrequisitoRequest request) {
        PlanEstudio plan = buscarOFallar(idPlan);
        validarBorrador(plan);
        PlanCurso curso = buscarCursoOFallar(idPlan, idPlanCurso);
        PlanCurso requisito = buscarCursoOFallar(idPlan, request.getIdPlanCursoRequisito());

        // Exigir un ciclo menor tambien evita ciclos entre prerrequisitos
        if (requisito.getCiclo() >= curso.getCiclo()) {
            throw new BusinessRuleException("El prerrequisito debe ser de un ciclo anterior al del curso");
        }
        boolean yaExiste = curso.getPrerrequisitos().stream()
                .anyMatch(p -> p.getRequisito().getId().equals(requisito.getId()));
        if (yaExiste) {
            throw new BusinessRuleException("El curso " + curso.getCodigoCurso() + " ya tiene ese prerrequisito");
        }
        curso.getPrerrequisitos().add(Prerrequisito.builder().curso(curso).requisito(requisito).build());
        return planEstudioMapper.toResponse(planEstudioRepository.saveAndFlush(plan));
    }

    @Transactional
    public PlanEstudioResponse eliminarPrerrequisito(Long idPlan, Long idPlanCurso, Long idPlanCursoRequisito) {
        PlanEstudio plan = buscarOFallar(idPlan);
        validarBorrador(plan);
        PlanCurso curso = buscarCursoOFallar(idPlan, idPlanCurso);
        boolean eliminado = curso.getPrerrequisitos()
                .removeIf(p -> p.getRequisito().getId().equals(idPlanCursoRequisito));
        if (!eliminado) {
            throw new ResourceNotFoundException("Prerrequisito no encontrado: " + idPlanCursoRequisito);
        }
        return planEstudioMapper.toResponse(planEstudioRepository.saveAndFlush(plan));
    }

    @Transactional
    public PlanEstudioResponse publicar(Long id) {
        PlanEstudio plan = buscarOFallar(id);
        validarBorrador(plan);
        if (plan.getCursos().isEmpty()) {
            throw new BusinessRuleException("No se puede publicar un plan sin cursos");
        }

        // El plan vigente anterior del programa pasa a DEROGADO antes de activar el nuevo
        Long idPlanDerogado = planEstudioRepository
                .findByIdProgramaAndEstado(plan.getIdPrograma(), EstadoPlan.VIGENTE)
                .map(anterior -> {
                    anterior.setEstado(EstadoPlan.DEROGADO);
                    planEstudioRepository.saveAndFlush(anterior);
                    return anterior.getId();
                })
                .orElse(null);

        plan.setEstado(EstadoPlan.VIGENTE);
        plan.setFechaPublicacion(Instant.now());
        PlanEstudio publicado = planEstudioRepository.saveAndFlush(plan);

        // Se envia a Kafka despues del commit (curriculo-eventos, key = planId)
        producer.publicarTrasCommit(planEstudioMapper.toPlanPublicadoEvento(publicado, idPlanDerogado, nombreServicio));
        return planEstudioMapper.toResponse(publicado);
    }

    private Modalidad buscarModalidad(Long idModalidad) {
        if (idModalidad == null) {
            return null;
        }
        return modalidadRepository.findById(idModalidad)
                .orElseThrow(() -> new ResourceNotFoundException("Modalidad no encontrada: " + idModalidad));
    }

    private void validarBorrador(PlanEstudio plan) {
        if (plan.getEstado() != EstadoPlan.BORRADOR) {
            throw new BusinessRuleException("El plan " + plan.getId() + " esta " + plan.getEstado() + " y ya no se puede modificar");
        }
    }

    private PlanEstudio buscarOFallar(Long id) {
        return planEstudioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + id));
    }

    private PlanCurso buscarCursoOFallar(Long idPlan, Long idPlanCurso) {
        return planCursoRepository.findByIdAndPlanId(idPlanCurso, idPlan)
                .orElseThrow(() -> new ResourceNotFoundException("Curso " + idPlanCurso + " no encontrado en el plan " + idPlan));
    }
}
