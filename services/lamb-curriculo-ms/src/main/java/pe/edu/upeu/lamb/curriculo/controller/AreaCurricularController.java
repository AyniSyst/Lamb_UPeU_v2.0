package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.AreaCurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.AreaCurricularResponse;
import pe.edu.upeu.lamb.curriculo.service.AreaCurricularService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Area curricular", description = "CRUD 20 · Area curricular (estudios generales, especificos, especialidad)")
@RestController
@RequestMapping("/api/v1/areas-curriculares")
@RequiredArgsConstructor
public class AreaCurricularController {

    private final AreaCurricularService areaCurricularService;

    @GetMapping
    public List<AreaCurricularResponse> listar() {
        return areaCurricularService.listar();
    }

    @GetMapping("/{id}")
    public AreaCurricularResponse obtener(@PathVariable Long id) {
        return areaCurricularService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AreaCurricularResponse crear(@Valid @RequestBody AreaCurricularRequest request) {
        return areaCurricularService.crear(request);
    }

    @PutMapping("/{id}")
    public AreaCurricularResponse actualizar(@PathVariable Long id, @Valid @RequestBody AreaCurricularRequest request) {
        return areaCurricularService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        areaCurricularService.eliminar(id);
    }
}
