package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.OfertaAcademicaRequest;
import pe.edu.upeu.lamb.planificacion.dto.OfertaAcademicaResponse;
import pe.edu.upeu.lamb.planificacion.service.OfertaAcademicaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Oferta academica", description = "CRUD 6 · Oferta academica por programa y periodo (el curso se jala de curriculo)")
@RestController
@RequestMapping("/api/v1/ofertas-academicas")
@RequiredArgsConstructor
public class OfertaAcademicaController {

    private final OfertaAcademicaService ofertaAcademicaService;

    @GetMapping
    public List<OfertaAcademicaResponse> listar(@RequestParam(required = false) Long idPeriodo) {
        return ofertaAcademicaService.listar(idPeriodo);
    }

    @GetMapping("/{id}")
    public OfertaAcademicaResponse obtener(@PathVariable Long id) {
        return ofertaAcademicaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OfertaAcademicaResponse crear(@Valid @RequestBody OfertaAcademicaRequest request) {
        return ofertaAcademicaService.crear(request);
    }

    @PutMapping("/{id}")
    public OfertaAcademicaResponse actualizar(@PathVariable Long id, @Valid @RequestBody OfertaAcademicaRequest request) {
        return ofertaAcademicaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        ofertaAcademicaService.eliminar(id);
    }
}
