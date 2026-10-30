package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.ModalidadTitulacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.ModalidadTitulacionResponse;
import pe.edu.upeu.lamb.curriculo.service.ModalidadTitulacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Modalidad de titulacion", description = "CRUD 38 · Modalidad de titulacion (tesis, suficiencia profesional)")
@RestController
@RequestMapping("/api/v1/modalidades-titulacion")
@RequiredArgsConstructor
public class ModalidadTitulacionController {

    private final ModalidadTitulacionService modalidadTitulacionService;

    @GetMapping
    public List<ModalidadTitulacionResponse> listar(@RequestParam(required = false) Long idPlan) {
        return modalidadTitulacionService.listar(idPlan);
    }

    @GetMapping("/{id}")
    public ModalidadTitulacionResponse obtener(@PathVariable Long id) {
        return modalidadTitulacionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModalidadTitulacionResponse crear(@Valid @RequestBody ModalidadTitulacionRequest request) {
        return modalidadTitulacionService.crear(request);
    }

    @PutMapping("/{id}")
    public ModalidadTitulacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody ModalidadTitulacionRequest request) {
        return modalidadTitulacionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        modalidadTitulacionService.eliminar(id);
    }
}
