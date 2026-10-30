package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.ModalidadRequest;
import pe.edu.upeu.lamb.curriculo.dto.ModalidadResponse;
import pe.edu.upeu.lamb.curriculo.service.ModalidadService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Modalidad", description = "CRUD 1 · Modalidad (presencial, semipresencial, a distancia, virtual): un solo catalogo para programa y curso")
@RestController
@RequestMapping("/api/v1/modalidades")
@RequiredArgsConstructor
public class ModalidadController {

    private final ModalidadService modalidadService;

    @GetMapping
    public List<ModalidadResponse> listar() {
        return modalidadService.listar();
    }

    @GetMapping("/{id}")
    public ModalidadResponse obtener(@PathVariable Long id) {
        return modalidadService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModalidadResponse crear(@Valid @RequestBody ModalidadRequest request) {
        return modalidadService.crear(request);
    }

    @PutMapping("/{id}")
    public ModalidadResponse actualizar(@PathVariable Long id, @Valid @RequestBody ModalidadRequest request) {
        return modalidadService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        modalidadService.eliminar(id);
    }
}
