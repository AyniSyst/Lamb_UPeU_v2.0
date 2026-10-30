package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.SilaboBaseRequest;
import pe.edu.upeu.lamb.curriculo.dto.SilaboBaseResponse;
import pe.edu.upeu.lamb.curriculo.service.SilaboBaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Silabo base del curso", description = "CRUD 15 · Silabo base del curso (planificacion lo jala para el silabo de cada grupo)")
@RestController
@RequestMapping("/api/v1/silabos-base")
@RequiredArgsConstructor
public class SilaboBaseController {

    private final SilaboBaseService silaboBaseService;

    @GetMapping
    public List<SilaboBaseResponse> listar(@RequestParam(required = false) Long idCurso) {
        return silaboBaseService.listar(idCurso);
    }

    @GetMapping("/{id}")
    public SilaboBaseResponse obtener(@PathVariable Long id) {
        return silaboBaseService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SilaboBaseResponse crear(@Valid @RequestBody SilaboBaseRequest request) {
        return silaboBaseService.crear(request);
    }

    @PutMapping("/{id}")
    public SilaboBaseResponse actualizar(@PathVariable Long id, @Valid @RequestBody SilaboBaseRequest request) {
        return silaboBaseService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        silaboBaseService.eliminar(id);
    }
}
