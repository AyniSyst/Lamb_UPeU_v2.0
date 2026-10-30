package pe.edu.upeu.lamb.institucional.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.institucional.dto.*;
import pe.edu.upeu.lamb.institucional.service.*;

import java.util.List;

/**
 * Dominio "Programa Academico" (CRUD unificado). Subrecursos: programa,
 * nivel academico, modalidad de estudio, regimen academico, programa-sede,
 * grado y titulo, resolucion de programa, mencion/especialidad y
 * acreditacion de programa.
 */
@RestController
@RequestMapping("/api/v1/programa-academico")
@RequiredArgsConstructor
@Tag(name = "programa-academico-controller")
public class ProgramaAcademicoController {

    private final ProgramaService programaService;
    private final NivelAcademicoService nivelAcademicoService;
    private final ModalidadEstudioService modalidadEstudioService;
    private final RegimenAcademicoService regimenAcademicoService;
    private final ProgramaSedeService programaSedeService;
    private final GradoTituloService gradoTituloService;
    private final ResolucionProgramaService resolucionProgramaService;
    private final MencionEspecialidadService mencionEspecialidadService;
    private final AcreditacionProgramaService acreditacionProgramaService;

    // --- Programa (item 19, existente migrado) ---

    @GetMapping("/programas")
    @Operation(summary = "Listar programas academicos")
    public List<ProgramaResponse> listarProgramas() {
        return programaService.listar();
    }

    @GetMapping("/programas/{id}")
    @Operation(summary = "Obtener un programa academico")
    public ProgramaResponse obtenerPrograma(@PathVariable Long id) {
        return programaService.obtener(id);
    }

    @PostMapping("/programas")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un programa academico")
    public ProgramaResponse crearPrograma(@Valid @RequestBody ProgramaRequest request) {
        return programaService.crear(request);
    }

    // --- Nivel academico (item 20) ---

    @GetMapping("/niveles-academicos")
    @Operation(summary = "Listar niveles academicos")
    public List<NivelAcademicoResponse> listarNivelesAcademicos() {
        return nivelAcademicoService.listar();
    }

    @GetMapping("/niveles-academicos/{id}")
    @Operation(summary = "Obtener un nivel academico")
    public NivelAcademicoResponse obtenerNivelAcademico(@PathVariable Long id) {
        return nivelAcademicoService.obtener(id);
    }

    @PostMapping("/niveles-academicos")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un nivel academico")
    public NivelAcademicoResponse crearNivelAcademico(@Valid @RequestBody NivelAcademicoRequest request) {
        return nivelAcademicoService.crear(request);
    }

    @PutMapping("/niveles-academicos/{id}")
    public NivelAcademicoResponse actualizarNivelAcademico(@PathVariable Long id, @Valid @RequestBody NivelAcademicoRequest request) {
        return nivelAcademicoService.actualizar(id, request);
    }

    @DeleteMapping("/niveles-academicos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarNivelAcademico(@PathVariable Long id) {
        nivelAcademicoService.eliminar(id);
    }

    // --- Modalidad de estudio (item 21) ---

    @GetMapping("/modalidades-estudio")
    @Operation(summary = "Listar modalidades de estudio")
    public List<ModalidadEstudioResponse> listarModalidadesEstudio() {
        return modalidadEstudioService.listar();
    }

    @GetMapping("/modalidades-estudio/{id}")
    @Operation(summary = "Obtener una modalidad de estudio")
    public ModalidadEstudioResponse obtenerModalidadEstudio(@PathVariable Long id) {
        return modalidadEstudioService.obtener(id);
    }

    @PostMapping("/modalidades-estudio")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una modalidad de estudio")
    public ModalidadEstudioResponse crearModalidadEstudio(@Valid @RequestBody ModalidadEstudioRequest request) {
        return modalidadEstudioService.crear(request);
    }

    @PutMapping("/modalidades-estudio/{id}")
    public ModalidadEstudioResponse actualizarModalidadEstudio(@PathVariable Long id, @Valid @RequestBody ModalidadEstudioRequest request) {
        return modalidadEstudioService.actualizar(id, request);
    }

    @DeleteMapping("/modalidades-estudio/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarModalidadEstudio(@PathVariable Long id) {
        modalidadEstudioService.eliminar(id);
    }

    // --- Regimen academico (item 22) ---

    @GetMapping("/regimenes-academicos")
    @Operation(summary = "Listar regimenes academicos")
    public List<RegimenAcademicoResponse> listarRegimenesAcademicos() {
        return regimenAcademicoService.listar();
    }

    @GetMapping("/regimenes-academicos/{id}")
    @Operation(summary = "Obtener un regimen academico")
    public RegimenAcademicoResponse obtenerRegimenAcademico(@PathVariable Long id) {
        return regimenAcademicoService.obtener(id);
    }

    @PostMapping("/regimenes-academicos")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un regimen academico")
    public RegimenAcademicoResponse crearRegimenAcademico(@Valid @RequestBody RegimenAcademicoRequest request) {
        return regimenAcademicoService.crear(request);
    }

    @PutMapping("/regimenes-academicos/{id}")
    public RegimenAcademicoResponse actualizarRegimenAcademico(@PathVariable Long id, @Valid @RequestBody RegimenAcademicoRequest request) {
        return regimenAcademicoService.actualizar(id, request);
    }

    @DeleteMapping("/regimenes-academicos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarRegimenAcademico(@PathVariable Long id) {
        regimenAcademicoService.eliminar(id);
    }

    // --- Programa-sede (item 23) ---

    @GetMapping("/programa-sede")
    @Operation(summary = "Listar relaciones programa-sede")
    public List<ProgramaSedeResponse> listarProgramaSede() {
        return programaSedeService.listar();
    }

    @GetMapping("/programa-sede/{id}")
    @Operation(summary = "Obtener una relacion programa-sede")
    public ProgramaSedeResponse obtenerProgramaSede(@PathVariable Long id) {
        return programaSedeService.obtener(id);
    }

    @PostMapping("/programa-sede")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una relacion programa-sede")
    public ProgramaSedeResponse crearProgramaSede(@Valid @RequestBody ProgramaSedeRequest request) {
        return programaSedeService.crear(request);
    }

    @PutMapping("/programa-sede/{id}")
    public ProgramaSedeResponse actualizarProgramaSede(@PathVariable Long id, @Valid @RequestBody ProgramaSedeRequest request) {
        return programaSedeService.actualizar(id, request);
    }

    @DeleteMapping("/programa-sede/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProgramaSede(@PathVariable Long id) {
        programaSedeService.eliminar(id);
    }

    // --- Grado y titulo (item 24) ---

    @GetMapping("/grados-titulos")
    @Operation(summary = "Listar grados y titulos")
    public List<GradoTituloResponse> listarGradosTitulos() {
        return gradoTituloService.listar();
    }

    @GetMapping("/grados-titulos/{id}")
    @Operation(summary = "Obtener un grado y titulo")
    public GradoTituloResponse obtenerGradoTitulo(@PathVariable Long id) {
        return gradoTituloService.obtener(id);
    }

    @PostMapping("/grados-titulos")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un grado y titulo")
    public GradoTituloResponse crearGradoTitulo(@Valid @RequestBody GradoTituloRequest request) {
        return gradoTituloService.crear(request);
    }

    @PutMapping("/grados-titulos/{id}")
    public GradoTituloResponse actualizarGradoTitulo(@PathVariable Long id, @Valid @RequestBody GradoTituloRequest request) {
        return gradoTituloService.actualizar(id, request);
    }

    @DeleteMapping("/grados-titulos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarGradoTitulo(@PathVariable Long id) {
        gradoTituloService.eliminar(id);
    }

    // --- Resolucion de programa (item 25) ---

    @GetMapping("/resoluciones-programa")
    @Operation(summary = "Listar resoluciones de programa")
    public List<ResolucionProgramaResponse> listarResolucionesPrograma() {
        return resolucionProgramaService.listar();
    }

    @GetMapping("/resoluciones-programa/{id}")
    @Operation(summary = "Obtener una resolucion de programa")
    public ResolucionProgramaResponse obtenerResolucionPrograma(@PathVariable Long id) {
        return resolucionProgramaService.obtener(id);
    }

    @PostMapping("/resoluciones-programa")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una resolucion de programa")
    public ResolucionProgramaResponse crearResolucionPrograma(@Valid @RequestBody ResolucionProgramaRequest request) {
        return resolucionProgramaService.crear(request);
    }

    @PutMapping("/resoluciones-programa/{id}")
    public ResolucionProgramaResponse actualizarResolucionPrograma(@PathVariable Long id, @Valid @RequestBody ResolucionProgramaRequest request) {
        return resolucionProgramaService.actualizar(id, request);
    }

    @DeleteMapping("/resoluciones-programa/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarResolucionPrograma(@PathVariable Long id) {
        resolucionProgramaService.eliminar(id);
    }

    // --- Mencion/especialidad (item 26, opcional) ---

    @GetMapping("/menciones-especialidad")
    @Operation(summary = "Listar menciones/especialidades")
    public List<MencionEspecialidadResponse> listarMencionesEspecialidad() {
        return mencionEspecialidadService.listar();
    }

    @GetMapping("/menciones-especialidad/{id}")
    @Operation(summary = "Obtener una mencion/especialidad")
    public MencionEspecialidadResponse obtenerMencionEspecialidad(@PathVariable Long id) {
        return mencionEspecialidadService.obtener(id);
    }

    @PostMapping("/menciones-especialidad")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una mencion/especialidad")
    public MencionEspecialidadResponse crearMencionEspecialidad(@Valid @RequestBody MencionEspecialidadRequest request) {
        return mencionEspecialidadService.crear(request);
    }

    @PutMapping("/menciones-especialidad/{id}")
    public MencionEspecialidadResponse actualizarMencionEspecialidad(@PathVariable Long id, @Valid @RequestBody MencionEspecialidadRequest request) {
        return mencionEspecialidadService.actualizar(id, request);
    }

    @DeleteMapping("/menciones-especialidad/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarMencionEspecialidad(@PathVariable Long id) {
        mencionEspecialidadService.eliminar(id);
    }

    // --- Acreditacion de programa (item 27, opcional) ---

    @GetMapping("/acreditaciones-programa")
    @Operation(summary = "Listar acreditaciones de programa")
    public List<AcreditacionProgramaResponse> listarAcreditacionesPrograma() {
        return acreditacionProgramaService.listar();
    }

    @GetMapping("/acreditaciones-programa/{id}")
    @Operation(summary = "Obtener una acreditacion de programa")
    public AcreditacionProgramaResponse obtenerAcreditacionPrograma(@PathVariable Long id) {
        return acreditacionProgramaService.obtener(id);
    }

    @PostMapping("/acreditaciones-programa")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una acreditacion de programa")
    public AcreditacionProgramaResponse crearAcreditacionPrograma(@Valid @RequestBody AcreditacionProgramaRequest request) {
        return acreditacionProgramaService.crear(request);
    }

    @PutMapping("/acreditaciones-programa/{id}")
    public AcreditacionProgramaResponse actualizarAcreditacionPrograma(@PathVariable Long id, @Valid @RequestBody AcreditacionProgramaRequest request) {
        return acreditacionProgramaService.actualizar(id, request);
    }

    @DeleteMapping("/acreditaciones-programa/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarAcreditacionPrograma(@PathVariable Long id) {
        acreditacionProgramaService.eliminar(id);
    }
}
