package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.MiembroComisionRequest;
import pe.edu.upeu.lamb.curriculo.dto.MiembroComisionResponse;
import pe.edu.upeu.lamb.curriculo.service.MiembroComisionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Miembro de comision curricular", description = "CRUD 45 · Miembro de comision curricular")
@RestController
@RequestMapping("/api/v1/miembros-comision")
@RequiredArgsConstructor
public class MiembroComisionController {

    private final MiembroComisionService miembroComisionService;

    @GetMapping
    public List<MiembroComisionResponse> listar(@RequestParam(required = false) Long idComision) {
        return miembroComisionService.listar(idComision);
    }

    @GetMapping("/{id}")
    public MiembroComisionResponse obtener(@PathVariable Long id) {
        return miembroComisionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MiembroComisionResponse crear(@Valid @RequestBody MiembroComisionRequest request) {
        return miembroComisionService.crear(request);
    }

    @PutMapping("/{id}")
    public MiembroComisionResponse actualizar(@PathVariable Long id, @Valid @RequestBody MiembroComisionRequest request) {
        return miembroComisionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        miembroComisionService.eliminar(id);
    }
}
