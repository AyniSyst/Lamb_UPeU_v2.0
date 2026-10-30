package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.DistribucionHorasRequest;
import pe.edu.upeu.lamb.curriculo.dto.DistribucionHorasResponse;
import pe.edu.upeu.lamb.curriculo.service.DistribucionHorasService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Distribucion de horas", description = "CRUD 18 · Distribucion de horas del curso (teoria, practica, laboratorio)")
@RestController
@RequestMapping("/api/v1/distribuciones-horas")
@RequiredArgsConstructor
public class DistribucionHorasController {

    private final DistribucionHorasService distribucionHorasService;

    @GetMapping
    public List<DistribucionHorasResponse> listar(@RequestParam(required = false) Long idCurso) {
        return distribucionHorasService.listar(idCurso);
    }

    @GetMapping("/{id}")
    public DistribucionHorasResponse obtener(@PathVariable Long id) {
        return distribucionHorasService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DistribucionHorasResponse crear(@Valid @RequestBody DistribucionHorasRequest request) {
        return distribucionHorasService.crear(request);
    }

    @PutMapping("/{id}")
    public DistribucionHorasResponse actualizar(@PathVariable Long id, @Valid @RequestBody DistribucionHorasRequest request) {
        return distribucionHorasService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        distribucionHorasService.eliminar(id);
    }
}
