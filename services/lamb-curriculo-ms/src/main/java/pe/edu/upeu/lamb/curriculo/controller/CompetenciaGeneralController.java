package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.CompetenciaGeneralRequest;
import pe.edu.upeu.lamb.curriculo.dto.CompetenciaGeneralResponse;
import pe.edu.upeu.lamb.curriculo.service.CompetenciaGeneralService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Competencia general", description = "CRUD 31 · Competencia general (institucional, comun a todos los programas)")
@RestController
@RequestMapping("/api/v1/competencias-generales")
@RequiredArgsConstructor
public class CompetenciaGeneralController {

    private final CompetenciaGeneralService competenciaGeneralService;

    @GetMapping
    public List<CompetenciaGeneralResponse> listar() {
        return competenciaGeneralService.listar();
    }

    @GetMapping("/{id}")
    public CompetenciaGeneralResponse obtener(@PathVariable Long id) {
        return competenciaGeneralService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompetenciaGeneralResponse crear(@Valid @RequestBody CompetenciaGeneralRequest request) {
        return competenciaGeneralService.crear(request);
    }

    @PutMapping("/{id}")
    public CompetenciaGeneralResponse actualizar(@PathVariable Long id, @Valid @RequestBody CompetenciaGeneralRequest request) {
        return competenciaGeneralService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        competenciaGeneralService.eliminar(id);
    }
}
