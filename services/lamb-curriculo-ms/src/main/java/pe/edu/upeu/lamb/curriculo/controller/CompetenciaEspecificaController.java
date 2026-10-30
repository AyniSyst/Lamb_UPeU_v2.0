package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.CompetenciaEspecificaRequest;
import pe.edu.upeu.lamb.curriculo.dto.CompetenciaEspecificaResponse;
import pe.edu.upeu.lamb.curriculo.service.CompetenciaEspecificaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Competencia especifica", description = "CRUD 32 · Competencia especifica del perfil de egreso")
@RestController
@RequestMapping("/api/v1/competencias-especificas")
@RequiredArgsConstructor
public class CompetenciaEspecificaController {

    private final CompetenciaEspecificaService competenciaEspecificaService;

    @GetMapping
    public List<CompetenciaEspecificaResponse> listar(@RequestParam(required = false) Long idPerfilEgreso) {
        return competenciaEspecificaService.listar(idPerfilEgreso);
    }

    @GetMapping("/{id}")
    public CompetenciaEspecificaResponse obtener(@PathVariable Long id) {
        return competenciaEspecificaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompetenciaEspecificaResponse crear(@Valid @RequestBody CompetenciaEspecificaRequest request) {
        return competenciaEspecificaService.crear(request);
    }

    @PutMapping("/{id}")
    public CompetenciaEspecificaResponse actualizar(@PathVariable Long id, @Valid @RequestBody CompetenciaEspecificaRequest request) {
        return competenciaEspecificaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        competenciaEspecificaService.eliminar(id);
    }
}
