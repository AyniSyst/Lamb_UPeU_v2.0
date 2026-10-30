package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.MallaCurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.MallaCurricularResponse;
import pe.edu.upeu.lamb.curriculo.service.MallaCurricularService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Malla curricular", description = "CRUD 10 · Malla curricular (documento adjunto: se guarda la referencia al archivo)")
@RestController
@RequestMapping("/api/v1/mallas-curriculares")
@RequiredArgsConstructor
public class MallaCurricularController {

    private final MallaCurricularService mallaCurricularService;

    @GetMapping
    public List<MallaCurricularResponse> listar(@RequestParam(required = false) Long idPlan) {
        return mallaCurricularService.listar(idPlan);
    }

    @GetMapping("/{id}")
    public MallaCurricularResponse obtener(@PathVariable Long id) {
        return mallaCurricularService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MallaCurricularResponse crear(@Valid @RequestBody MallaCurricularRequest request) {
        return mallaCurricularService.crear(request);
    }

    @PutMapping("/{id}")
    public MallaCurricularResponse actualizar(@PathVariable Long id, @Valid @RequestBody MallaCurricularRequest request) {
        return mallaCurricularService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        mallaCurricularService.eliminar(id);
    }
}
