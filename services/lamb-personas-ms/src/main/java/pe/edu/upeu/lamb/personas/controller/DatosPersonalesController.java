package pe.edu.upeu.lamb.personas.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.personas.dto.*;
import pe.edu.upeu.lamb.personas.service.*;

import java.util.List;

@Tag(name = "datos-personales-controller")
@RestController
@RequestMapping("/api/v1/datos-personales")
@RequiredArgsConstructor
public class DatosPersonalesController {

    private final DatoPersonalService datoPersonalService;
    private final SexoService sexoService;
    private final EstadoCivilService estadoCivilService;
    private final NacionalidadService nacionalidadService;
    private final UbigeoService ubigeoService;
    private final DiscapacidadCondicionService discapacidadCondicionService;
    private final CambioNombreApellidoService cambioNombreApellidoService;
    private final ConsentimientoDatosService consentimientoDatosService;
    private final LenguaIdiomaService lenguaIdiomaService;
    private final AutoidentificacionEtnicaService autoidentificacionEtnicaService;
    private final ConfesionReligiosaService confesionReligiosaService;
    private final ColegioProcedenciaService colegioProcedenciaService;
    private final DatoSocioeconomicoService datoSocioeconomicoService;

    // ---- Datos personales ----
    @Operation(summary = "Listar datos personales")
    @GetMapping("/datos")
    public List<DatoPersonalResponse> listarDatos() {
        return datoPersonalService.listar();
    }

    @Operation(summary = "Obtener dato personal por id")
    @GetMapping("/datos/{id}")
    public DatoPersonalResponse obtenerDato(@PathVariable Long id) {
        return datoPersonalService.obtener(id);
    }

    @Operation(summary = "Crear dato personal")
    @PostMapping("/datos")
    @ResponseStatus(HttpStatus.CREATED)
    public DatoPersonalResponse crearDato(@Valid @RequestBody DatoPersonalRequest request) {
        return datoPersonalService.crear(request);
    }

    @PutMapping("/datos/{id}")
    public DatoPersonalResponse actualizarDato(@PathVariable Long id, @Valid @RequestBody DatoPersonalRequest request) {
        return datoPersonalService.actualizar(id, request);
    }

    @DeleteMapping("/datos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarDato(@PathVariable Long id) {
        datoPersonalService.eliminar(id);
    }

    // ---- Sexo ----
    @Operation(summary = "Listar sexos")
    @GetMapping("/sexos")
    public List<SexoResponse> listarSexos() {
        return sexoService.listar();
    }

    @Operation(summary = "Obtener sexo por id")
    @GetMapping("/sexos/{id}")
    public SexoResponse obtenerSexo(@PathVariable Long id) {
        return sexoService.obtener(id);
    }

    @Operation(summary = "Crear sexo")
    @PostMapping("/sexos")
    @ResponseStatus(HttpStatus.CREATED)
    public SexoResponse crearSexo(@Valid @RequestBody SexoRequest request) {
        return sexoService.crear(request);
    }

    @PutMapping("/sexos/{id}")
    public SexoResponse actualizarSexo(@PathVariable Long id, @Valid @RequestBody SexoRequest request) {
        return sexoService.actualizar(id, request);
    }

    @DeleteMapping("/sexos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarSexo(@PathVariable Long id) {
        sexoService.eliminar(id);
    }

    // ---- Estado civil ----
    @Operation(summary = "Listar estados civiles")
    @GetMapping("/estados-civiles")
    public List<EstadoCivilResponse> listarEstadosCiviles() {
        return estadoCivilService.listar();
    }

    @Operation(summary = "Obtener estado civil por id")
    @GetMapping("/estados-civiles/{id}")
    public EstadoCivilResponse obtenerEstadoCivil(@PathVariable Long id) {
        return estadoCivilService.obtener(id);
    }

    @Operation(summary = "Crear estado civil")
    @PostMapping("/estados-civiles")
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoCivilResponse crearEstadoCivil(@Valid @RequestBody EstadoCivilRequest request) {
        return estadoCivilService.crear(request);
    }

    @PutMapping("/estados-civiles/{id}")
    public EstadoCivilResponse actualizarEstadoCivil(@PathVariable Long id, @Valid @RequestBody EstadoCivilRequest request) {
        return estadoCivilService.actualizar(id, request);
    }

    @DeleteMapping("/estados-civiles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarEstadoCivil(@PathVariable Long id) {
        estadoCivilService.eliminar(id);
    }

    // ---- Nacionalidad ----
    @Operation(summary = "Listar nacionalidades")
    @GetMapping("/nacionalidades")
    public List<NacionalidadResponse> listarNacionalidades() {
        return nacionalidadService.listar();
    }

    @Operation(summary = "Obtener nacionalidad por id")
    @GetMapping("/nacionalidades/{id}")
    public NacionalidadResponse obtenerNacionalidad(@PathVariable Long id) {
        return nacionalidadService.obtener(id);
    }

    @Operation(summary = "Crear nacionalidad")
    @PostMapping("/nacionalidades")
    @ResponseStatus(HttpStatus.CREATED)
    public NacionalidadResponse crearNacionalidad(@Valid @RequestBody NacionalidadRequest request) {
        return nacionalidadService.crear(request);
    }

    @PutMapping("/nacionalidades/{id}")
    public NacionalidadResponse actualizarNacionalidad(@PathVariable Long id, @Valid @RequestBody NacionalidadRequest request) {
        return nacionalidadService.actualizar(id, request);
    }

    @DeleteMapping("/nacionalidades/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarNacionalidad(@PathVariable Long id) {
        nacionalidadService.eliminar(id);
    }

    // ---- Ubigeo ----
    @Operation(summary = "Listar ubigeos")
    @GetMapping("/ubigeos")
    public List<UbigeoResponse> listarUbigeos() {
        return ubigeoService.listar();
    }

    @Operation(summary = "Obtener ubigeo por id")
    @GetMapping("/ubigeos/{id}")
    public UbigeoResponse obtenerUbigeo(@PathVariable Long id) {
        return ubigeoService.obtener(id);
    }

    @Operation(summary = "Crear ubigeo")
    @PostMapping("/ubigeos")
    @ResponseStatus(HttpStatus.CREATED)
    public UbigeoResponse crearUbigeo(@Valid @RequestBody UbigeoRequest request) {
        return ubigeoService.crear(request);
    }

    @PutMapping("/ubigeos/{id}")
    public UbigeoResponse actualizarUbigeo(@PathVariable Long id, @Valid @RequestBody UbigeoRequest request) {
        return ubigeoService.actualizar(id, request);
    }

    @DeleteMapping("/ubigeos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUbigeo(@PathVariable Long id) {
        ubigeoService.eliminar(id);
    }

    // ---- Discapacidad / condicion ----
    @Operation(summary = "Listar discapacidades/condiciones")
    @GetMapping("/discapacidades")
    public List<DiscapacidadCondicionResponse> listarDiscapacidades() {
        return discapacidadCondicionService.listar();
    }

    @Operation(summary = "Obtener discapacidad/condicion por id")
    @GetMapping("/discapacidades/{id}")
    public DiscapacidadCondicionResponse obtenerDiscapacidad(@PathVariable Long id) {
        return discapacidadCondicionService.obtener(id);
    }

    @Operation(summary = "Crear discapacidad/condicion")
    @PostMapping("/discapacidades")
    @ResponseStatus(HttpStatus.CREATED)
    public DiscapacidadCondicionResponse crearDiscapacidad(@Valid @RequestBody DiscapacidadCondicionRequest request) {
        return discapacidadCondicionService.crear(request);
    }

    @PutMapping("/discapacidades/{id}")
    public DiscapacidadCondicionResponse actualizarDiscapacidad(@PathVariable Long id, @Valid @RequestBody DiscapacidadCondicionRequest request) {
        return discapacidadCondicionService.actualizar(id, request);
    }

    @DeleteMapping("/discapacidades/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarDiscapacidad(@PathVariable Long id) {
        discapacidadCondicionService.eliminar(id);
    }

    // ---- Cambio de nombre/apellido ----
    @Operation(summary = "Listar cambios de nombre/apellido")
    @GetMapping("/cambios-nombre-apellido")
    public List<CambioNombreApellidoResponse> listarCambiosNombreApellido() {
        return cambioNombreApellidoService.listar();
    }

    @Operation(summary = "Obtener cambio de nombre/apellido por id")
    @GetMapping("/cambios-nombre-apellido/{id}")
    public CambioNombreApellidoResponse obtenerCambioNombreApellido(@PathVariable Long id) {
        return cambioNombreApellidoService.obtener(id);
    }

    @Operation(summary = "Crear cambio de nombre/apellido")
    @PostMapping("/cambios-nombre-apellido")
    @ResponseStatus(HttpStatus.CREATED)
    public CambioNombreApellidoResponse crearCambioNombreApellido(@Valid @RequestBody CambioNombreApellidoRequest request) {
        return cambioNombreApellidoService.crear(request);
    }

    @PutMapping("/cambios-nombre-apellido/{id}")
    public CambioNombreApellidoResponse actualizarCambioNombreApellido(@PathVariable Long id, @Valid @RequestBody CambioNombreApellidoRequest request) {
        return cambioNombreApellidoService.actualizar(id, request);
    }

    @DeleteMapping("/cambios-nombre-apellido/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCambioNombreApellido(@PathVariable Long id) {
        cambioNombreApellidoService.eliminar(id);
    }

    // ---- Consentimiento de datos ----
    @Operation(summary = "Listar consentimientos de datos")
    @GetMapping("/consentimientos")
    public List<ConsentimientoDatosResponse> listarConsentimientos() {
        return consentimientoDatosService.listar();
    }

    @Operation(summary = "Obtener consentimiento de datos por id")
    @GetMapping("/consentimientos/{id}")
    public ConsentimientoDatosResponse obtenerConsentimiento(@PathVariable Long id) {
        return consentimientoDatosService.obtener(id);
    }

    @Operation(summary = "Crear consentimiento de datos")
    @PostMapping("/consentimientos")
    @ResponseStatus(HttpStatus.CREATED)
    public ConsentimientoDatosResponse crearConsentimiento(@Valid @RequestBody ConsentimientoDatosRequest request) {
        return consentimientoDatosService.crear(request);
    }

    @PutMapping("/consentimientos/{id}")
    public ConsentimientoDatosResponse actualizarConsentimiento(@PathVariable Long id, @Valid @RequestBody ConsentimientoDatosRequest request) {
        return consentimientoDatosService.actualizar(id, request);
    }

    @DeleteMapping("/consentimientos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarConsentimiento(@PathVariable Long id) {
        consentimientoDatosService.eliminar(id);
    }

    // ---- Lengua / idioma ----
    @Operation(summary = "Listar lenguas/idiomas")
    @GetMapping("/lenguas-idiomas")
    public List<LenguaIdiomaResponse> listarLenguasIdiomas() {
        return lenguaIdiomaService.listar();
    }

    @Operation(summary = "Obtener lengua/idioma por id")
    @GetMapping("/lenguas-idiomas/{id}")
    public LenguaIdiomaResponse obtenerLenguaIdioma(@PathVariable Long id) {
        return lenguaIdiomaService.obtener(id);
    }

    @Operation(summary = "Crear lengua/idioma")
    @PostMapping("/lenguas-idiomas")
    @ResponseStatus(HttpStatus.CREATED)
    public LenguaIdiomaResponse crearLenguaIdioma(@Valid @RequestBody LenguaIdiomaRequest request) {
        return lenguaIdiomaService.crear(request);
    }

    @PutMapping("/lenguas-idiomas/{id}")
    public LenguaIdiomaResponse actualizarLenguaIdioma(@PathVariable Long id, @Valid @RequestBody LenguaIdiomaRequest request) {
        return lenguaIdiomaService.actualizar(id, request);
    }

    @DeleteMapping("/lenguas-idiomas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarLenguaIdioma(@PathVariable Long id) {
        lenguaIdiomaService.eliminar(id);
    }

    // ---- Autoidentificacion etnica ----
    @Operation(summary = "Listar autoidentificaciones etnicas")
    @GetMapping("/autoidentificaciones-etnicas")
    public List<AutoidentificacionEtnicaResponse> listarAutoidentificacionesEtnicas() {
        return autoidentificacionEtnicaService.listar();
    }

    @Operation(summary = "Obtener autoidentificacion etnica por id")
    @GetMapping("/autoidentificaciones-etnicas/{id}")
    public AutoidentificacionEtnicaResponse obtenerAutoidentificacionEtnica(@PathVariable Long id) {
        return autoidentificacionEtnicaService.obtener(id);
    }

    @Operation(summary = "Crear autoidentificacion etnica")
    @PostMapping("/autoidentificaciones-etnicas")
    @ResponseStatus(HttpStatus.CREATED)
    public AutoidentificacionEtnicaResponse crearAutoidentificacionEtnica(@Valid @RequestBody AutoidentificacionEtnicaRequest request) {
        return autoidentificacionEtnicaService.crear(request);
    }

    @PutMapping("/autoidentificaciones-etnicas/{id}")
    public AutoidentificacionEtnicaResponse actualizarAutoidentificacionEtnica(@PathVariable Long id, @Valid @RequestBody AutoidentificacionEtnicaRequest request) {
        return autoidentificacionEtnicaService.actualizar(id, request);
    }

    @DeleteMapping("/autoidentificaciones-etnicas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarAutoidentificacionEtnica(@PathVariable Long id) {
        autoidentificacionEtnicaService.eliminar(id);
    }

    // ---- Confesion religiosa ----
    @Operation(summary = "Listar confesiones religiosas")
    @GetMapping("/confesiones-religiosas")
    public List<ConfesionReligiosaResponse> listarConfesionesReligiosas() {
        return confesionReligiosaService.listar();
    }

    @Operation(summary = "Obtener confesion religiosa por id")
    @GetMapping("/confesiones-religiosas/{id}")
    public ConfesionReligiosaResponse obtenerConfesionReligiosa(@PathVariable Long id) {
        return confesionReligiosaService.obtener(id);
    }

    @Operation(summary = "Crear confesion religiosa")
    @PostMapping("/confesiones-religiosas")
    @ResponseStatus(HttpStatus.CREATED)
    public ConfesionReligiosaResponse crearConfesionReligiosa(@Valid @RequestBody ConfesionReligiosaRequest request) {
        return confesionReligiosaService.crear(request);
    }

    @PutMapping("/confesiones-religiosas/{id}")
    public ConfesionReligiosaResponse actualizarConfesionReligiosa(@PathVariable Long id, @Valid @RequestBody ConfesionReligiosaRequest request) {
        return confesionReligiosaService.actualizar(id, request);
    }

    @DeleteMapping("/confesiones-religiosas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarConfesionReligiosa(@PathVariable Long id) {
        confesionReligiosaService.eliminar(id);
    }

    // ---- Colegio de procedencia ----
    @Operation(summary = "Listar colegios de procedencia")
    @GetMapping("/colegios-procedencia")
    public List<ColegioProcedenciaResponse> listarColegiosProcedencia() {
        return colegioProcedenciaService.listar();
    }

    @Operation(summary = "Obtener colegio de procedencia por id")
    @GetMapping("/colegios-procedencia/{id}")
    public ColegioProcedenciaResponse obtenerColegioProcedencia(@PathVariable Long id) {
        return colegioProcedenciaService.obtener(id);
    }

    @Operation(summary = "Crear colegio de procedencia")
    @PostMapping("/colegios-procedencia")
    @ResponseStatus(HttpStatus.CREATED)
    public ColegioProcedenciaResponse crearColegioProcedencia(@Valid @RequestBody ColegioProcedenciaRequest request) {
        return colegioProcedenciaService.crear(request);
    }

    @PutMapping("/colegios-procedencia/{id}")
    public ColegioProcedenciaResponse actualizarColegioProcedencia(@PathVariable Long id, @Valid @RequestBody ColegioProcedenciaRequest request) {
        return colegioProcedenciaService.actualizar(id, request);
    }

    @DeleteMapping("/colegios-procedencia/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarColegioProcedencia(@PathVariable Long id) {
        colegioProcedenciaService.eliminar(id);
    }

    // ---- Dato socioeconomico ----
    @Operation(summary = "Listar datos socioeconomicos")
    @GetMapping("/socioeconomicos")
    public List<DatoSocioeconomicoResponse> listarDatosSocioeconomicos() {
        return datoSocioeconomicoService.listar();
    }

    @Operation(summary = "Obtener dato socioeconomico por id")
    @GetMapping("/socioeconomicos/{id}")
    public DatoSocioeconomicoResponse obtenerDatoSocioeconomico(@PathVariable Long id) {
        return datoSocioeconomicoService.obtener(id);
    }

    @Operation(summary = "Crear dato socioeconomico")
    @PostMapping("/socioeconomicos")
    @ResponseStatus(HttpStatus.CREATED)
    public DatoSocioeconomicoResponse crearDatoSocioeconomico(@Valid @RequestBody DatoSocioeconomicoRequest request) {
        return datoSocioeconomicoService.crear(request);
    }

    @PutMapping("/socioeconomicos/{id}")
    public DatoSocioeconomicoResponse actualizarDatoSocioeconomico(@PathVariable Long id, @Valid @RequestBody DatoSocioeconomicoRequest request) {
        return datoSocioeconomicoService.actualizar(id, request);
    }

    @DeleteMapping("/socioeconomicos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarDatoSocioeconomico(@PathVariable Long id) {
        datoSocioeconomicoService.eliminar(id);
    }
}
