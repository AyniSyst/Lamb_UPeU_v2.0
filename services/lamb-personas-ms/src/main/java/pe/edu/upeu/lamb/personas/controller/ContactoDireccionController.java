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

@Tag(name = "contacto-direccion-controller")
@RestController
@RequestMapping("/api/v1/contacto-direccion")
@RequiredArgsConstructor
public class ContactoDireccionController {

    private final TipoContactoService tipoContactoService;
    private final ContactoService contactoService;
    private final TipoDireccionService tipoDireccionService;
    private final DireccionService direccionService;
    private final CorreoInstitucionalService correoInstitucionalService;
    private final ContactoEmergenciaService contactoEmergenciaService;
    private final VerificacionContactoService verificacionContactoService;
    private final PreferenciaComunicacionService preferenciaComunicacionService;
    private final RedSocialService redSocialService;

    // ---- Tipo de contacto ----
    @Operation(summary = "Listar tipos de contacto")
    @GetMapping("/tipos-contacto")
    public List<TipoContactoResponse> listarTiposContacto() {
        return tipoContactoService.listar();
    }

    @Operation(summary = "Obtener tipo de contacto por id")
    @GetMapping("/tipos-contacto/{id}")
    public TipoContactoResponse obtenerTipoContacto(@PathVariable Long id) {
        return tipoContactoService.obtener(id);
    }

    @Operation(summary = "Crear tipo de contacto")
    @PostMapping("/tipos-contacto")
    @ResponseStatus(HttpStatus.CREATED)
    public TipoContactoResponse crearTipoContacto(@Valid @RequestBody TipoContactoRequest request) {
        return tipoContactoService.crear(request);
    }

    // ---- Contacto ----
    @Operation(summary = "Listar contactos")
    @GetMapping("/contactos")
    public List<ContactoResponse> listarContactos() {
        return contactoService.listar();
    }

    @Operation(summary = "Obtener contacto por id")
    @GetMapping("/contactos/{id}")
    public ContactoResponse obtenerContacto(@PathVariable Long id) {
        return contactoService.obtener(id);
    }

    @Operation(summary = "Crear contacto")
    @PostMapping("/contactos")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactoResponse crearContacto(@Valid @RequestBody ContactoRequest request) {
        return contactoService.crear(request);
    }

    // ---- Tipo de direccion ----
    @Operation(summary = "Listar tipos de direccion")
    @GetMapping("/tipos-direccion")
    public List<TipoDireccionResponse> listarTiposDireccion() {
        return tipoDireccionService.listar();
    }

    @Operation(summary = "Obtener tipo de direccion por id")
    @GetMapping("/tipos-direccion/{id}")
    public TipoDireccionResponse obtenerTipoDireccion(@PathVariable Long id) {
        return tipoDireccionService.obtener(id);
    }

    @Operation(summary = "Crear tipo de direccion")
    @PostMapping("/tipos-direccion")
    @ResponseStatus(HttpStatus.CREATED)
    public TipoDireccionResponse crearTipoDireccion(@Valid @RequestBody TipoDireccionRequest request) {
        return tipoDireccionService.crear(request);
    }

    // ---- Direccion ----
    @Operation(summary = "Listar direcciones")
    @GetMapping("/direcciones")
    public List<DireccionResponse> listarDirecciones() {
        return direccionService.listar();
    }

    @Operation(summary = "Obtener direccion por id")
    @GetMapping("/direcciones/{id}")
    public DireccionResponse obtenerDireccion(@PathVariable Long id) {
        return direccionService.obtener(id);
    }

    @Operation(summary = "Crear direccion")
    @PostMapping("/direcciones")
    @ResponseStatus(HttpStatus.CREATED)
    public DireccionResponse crearDireccion(@Valid @RequestBody DireccionRequest request) {
        return direccionService.crear(request);
    }

    // ---- Correo institucional ----
    @Operation(summary = "Listar correos institucionales")
    @GetMapping("/correos-institucionales")
    public List<CorreoInstitucionalResponse> listarCorreosInstitucionales() {
        return correoInstitucionalService.listar();
    }

    @Operation(summary = "Obtener correo institucional por id")
    @GetMapping("/correos-institucionales/{id}")
    public CorreoInstitucionalResponse obtenerCorreoInstitucional(@PathVariable Long id) {
        return correoInstitucionalService.obtener(id);
    }

    @Operation(summary = "Crear correo institucional")
    @PostMapping("/correos-institucionales")
    @ResponseStatus(HttpStatus.CREATED)
    public CorreoInstitucionalResponse crearCorreoInstitucional(@Valid @RequestBody CorreoInstitucionalRequest request) {
        return correoInstitucionalService.crear(request);
    }

    // ---- Contacto de emergencia ----
    @Operation(summary = "Listar contactos de emergencia")
    @GetMapping("/contactos-emergencia")
    public List<ContactoEmergenciaResponse> listarContactosEmergencia() {
        return contactoEmergenciaService.listar();
    }

    @Operation(summary = "Obtener contacto de emergencia por id")
    @GetMapping("/contactos-emergencia/{id}")
    public ContactoEmergenciaResponse obtenerContactoEmergencia(@PathVariable Long id) {
        return contactoEmergenciaService.obtener(id);
    }

    @Operation(summary = "Crear contacto de emergencia")
    @PostMapping("/contactos-emergencia")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactoEmergenciaResponse crearContactoEmergencia(@Valid @RequestBody ContactoEmergenciaRequest request) {
        return contactoEmergenciaService.crear(request);
    }

    // ---- Verificacion de contacto ----
    @Operation(summary = "Listar verificaciones de contacto")
    @GetMapping("/verificaciones-contacto")
    public List<VerificacionContactoResponse> listarVerificacionesContacto() {
        return verificacionContactoService.listar();
    }

    @Operation(summary = "Obtener verificacion de contacto por id")
    @GetMapping("/verificaciones-contacto/{id}")
    public VerificacionContactoResponse obtenerVerificacionContacto(@PathVariable Long id) {
        return verificacionContactoService.obtener(id);
    }

    @Operation(summary = "Crear verificacion de contacto")
    @PostMapping("/verificaciones-contacto")
    @ResponseStatus(HttpStatus.CREATED)
    public VerificacionContactoResponse crearVerificacionContacto(@Valid @RequestBody VerificacionContactoRequest request) {
        return verificacionContactoService.crear(request);
    }

    // ---- Preferencia de comunicacion ----
    @Operation(summary = "Listar preferencias de comunicacion")
    @GetMapping("/preferencias-comunicacion")
    public List<PreferenciaComunicacionResponse> listarPreferenciasComunicacion() {
        return preferenciaComunicacionService.listar();
    }

    @Operation(summary = "Obtener preferencia de comunicacion por id")
    @GetMapping("/preferencias-comunicacion/{id}")
    public PreferenciaComunicacionResponse obtenerPreferenciaComunicacion(@PathVariable Long id) {
        return preferenciaComunicacionService.obtener(id);
    }

    @Operation(summary = "Crear preferencia de comunicacion")
    @PostMapping("/preferencias-comunicacion")
    @ResponseStatus(HttpStatus.CREATED)
    public PreferenciaComunicacionResponse crearPreferenciaComunicacion(@Valid @RequestBody PreferenciaComunicacionRequest request) {
        return preferenciaComunicacionService.crear(request);
    }

    // ---- Red social ----
    @Operation(summary = "Listar redes sociales")
    @GetMapping("/redes-sociales")
    public List<RedSocialResponse> listarRedesSociales() {
        return redSocialService.listar();
    }

    @Operation(summary = "Obtener red social por id")
    @GetMapping("/redes-sociales/{id}")
    public RedSocialResponse obtenerRedSocial(@PathVariable Long id) {
        return redSocialService.obtener(id);
    }

    @Operation(summary = "Crear red social")
    @PostMapping("/redes-sociales")
    @ResponseStatus(HttpStatus.CREATED)
    public RedSocialResponse crearRedSocial(@Valid @RequestBody RedSocialRequest request) {
        return redSocialService.crear(request);
    }
}
