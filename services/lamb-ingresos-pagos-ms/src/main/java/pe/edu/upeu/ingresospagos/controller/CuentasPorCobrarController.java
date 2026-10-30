package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.BecaAsignadaRequest;
import pe.edu.upeu.ingresospagos.dto.BecaAsignadaResponse;
import pe.edu.upeu.ingresospagos.dto.BecaRequest;
import pe.edu.upeu.ingresospagos.dto.BecaResponse;
import pe.edu.upeu.ingresospagos.dto.ConvenioInstitucionalRequest;
import pe.edu.upeu.ingresospagos.dto.ConvenioInstitucionalResponse;
import pe.edu.upeu.ingresospagos.dto.FinanciamientoEducativoRequest;
import pe.edu.upeu.ingresospagos.dto.FinanciamientoEducativoResponse;
import pe.edu.upeu.ingresospagos.dto.PagadorTerceroRequest;
import pe.edu.upeu.ingresospagos.dto.PagadorTerceroResponse;
import pe.edu.upeu.ingresospagos.service.BecaAsignadaService;
import pe.edu.upeu.ingresospagos.service.BecaService;
import pe.edu.upeu.ingresospagos.service.ConvenioInstitucionalService;
import pe.edu.upeu.ingresospagos.service.FinanciamientoEducativoService;
import pe.edu.upeu.ingresospagos.service.PagadorTerceroService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingresos-pagos/cuentas-por-cobrar")
@RequiredArgsConstructor
public class CuentasPorCobrarController {

    private final BecaService becaService;
    private final BecaAsignadaService becaAsignadaService;
    private final ConvenioInstitucionalService convenioInstitucionalService;
    private final FinanciamientoEducativoService financiamientoEducativoService;
    private final PagadorTerceroService pagadorTerceroService;

    // --- becas ---

    @GetMapping("/becas")
    public List<BecaResponse> listarBecas() {
        return becaService.findAll();
    }

    @GetMapping("/becas/{id}")
    public BecaResponse obtenerBeca(@PathVariable Long id) {
        return becaService.findById(id);
    }

    @PostMapping("/becas")
    @ResponseStatus(HttpStatus.CREATED)
    public BecaResponse crearBeca(@Valid @RequestBody BecaRequest request) {
        return becaService.create(request);
    }

    @PutMapping("/becas/{id}")
    public BecaResponse actualizarBeca(@PathVariable Long id, @Valid @RequestBody BecaRequest request) {
        return becaService.update(id, request);
    }

    @DeleteMapping("/becas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarBeca(@PathVariable Long id) {
        becaService.delete(id);
    }

    // --- becas-asignadas ---

    @GetMapping("/becas-asignadas")
    public List<BecaAsignadaResponse> listarBecasAsignadas() {
        return becaAsignadaService.listar();
    }

    @GetMapping("/becas-asignadas/{id}")
    public BecaAsignadaResponse obtenerBecaAsignada(@PathVariable Long id) {
        return becaAsignadaService.obtener(id);
    }

    @PostMapping("/becas-asignadas")
    @ResponseStatus(HttpStatus.CREATED)
    public BecaAsignadaResponse crearBecaAsignada(@Valid @RequestBody BecaAsignadaRequest request) {
        return becaAsignadaService.crear(request);
    }

    @PutMapping("/becas-asignadas/{id}")
    public BecaAsignadaResponse actualizarBecaAsignada(@PathVariable Long id, @Valid @RequestBody BecaAsignadaRequest request) {
        return becaAsignadaService.actualizar(id, request);
    }

    @DeleteMapping("/becas-asignadas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarBecaAsignada(@PathVariable Long id) {
        becaAsignadaService.eliminar(id);
    }

    // --- convenios-institucionales ---

    @GetMapping("/convenios-institucionales")
    public List<ConvenioInstitucionalResponse> listarConveniosInstitucionales() {
        return convenioInstitucionalService.findAll();
    }

    @GetMapping("/convenios-institucionales/{id}")
    public ConvenioInstitucionalResponse obtenerConvenioInstitucional(@PathVariable Long id) {
        return convenioInstitucionalService.findById(id);
    }

    @PostMapping("/convenios-institucionales")
    @ResponseStatus(HttpStatus.CREATED)
    public ConvenioInstitucionalResponse crearConvenioInstitucional(@Valid @RequestBody ConvenioInstitucionalRequest request) {
        return convenioInstitucionalService.create(request);
    }

    @PutMapping("/convenios-institucionales/{id}")
    public ConvenioInstitucionalResponse actualizarConvenioInstitucional(@PathVariable Long id, @Valid @RequestBody ConvenioInstitucionalRequest request) {
        return convenioInstitucionalService.update(id, request);
    }

    @DeleteMapping("/convenios-institucionales/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarConvenioInstitucional(@PathVariable Long id) {
        convenioInstitucionalService.delete(id);
    }

    // --- financiamientos-educativos ---

    @GetMapping("/financiamientos-educativos")
    public List<FinanciamientoEducativoResponse> listarFinanciamientosEducativos() {
        return financiamientoEducativoService.listar();
    }

    @GetMapping("/financiamientos-educativos/{id}")
    public FinanciamientoEducativoResponse obtenerFinanciamientoEducativo(@PathVariable Long id) {
        return financiamientoEducativoService.obtener(id);
    }

    @PostMapping("/financiamientos-educativos")
    @ResponseStatus(HttpStatus.CREATED)
    public FinanciamientoEducativoResponse crearFinanciamientoEducativo(@Valid @RequestBody FinanciamientoEducativoRequest request) {
        return financiamientoEducativoService.crear(request);
    }

    @PutMapping("/financiamientos-educativos/{id}")
    public FinanciamientoEducativoResponse actualizarFinanciamientoEducativo(@PathVariable Long id, @Valid @RequestBody FinanciamientoEducativoRequest request) {
        return financiamientoEducativoService.actualizar(id, request);
    }

    @DeleteMapping("/financiamientos-educativos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarFinanciamientoEducativo(@PathVariable Long id) {
        financiamientoEducativoService.eliminar(id);
    }

    // --- pagadores-terceros ---

    @GetMapping("/pagadores-terceros")
    public List<PagadorTerceroResponse> listarPagadoresTerceros() {
        return pagadorTerceroService.findAll();
    }

    @GetMapping("/pagadores-terceros/{id}")
    public PagadorTerceroResponse obtenerPagadorTercero(@PathVariable Long id) {
        return pagadorTerceroService.findById(id);
    }

    @PostMapping("/pagadores-terceros")
    @ResponseStatus(HttpStatus.CREATED)
    public PagadorTerceroResponse crearPagadorTercero(@Valid @RequestBody PagadorTerceroRequest request) {
        return pagadorTerceroService.create(request);
    }

    @PutMapping("/pagadores-terceros/{id}")
    public PagadorTerceroResponse actualizarPagadorTercero(@PathVariable Long id, @Valid @RequestBody PagadorTerceroRequest request) {
        return pagadorTerceroService.update(id, request);
    }

    @DeleteMapping("/pagadores-terceros/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPagadorTercero(@PathVariable Long id) {
        pagadorTerceroService.delete(id);
    }
}
