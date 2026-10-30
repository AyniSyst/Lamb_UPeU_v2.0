package pe.edu.upeu.lamb.curriculo.controller;

import pe.edu.upeu.lamb.curriculo.dto.CreditosCicloRequest;
import pe.edu.upeu.lamb.curriculo.dto.CreditosCicloResponse;
import pe.edu.upeu.lamb.curriculo.service.CreditosCicloService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Creditos por ciclo", description = "CRUD 7 · Creditos minimos y maximos por ciclo")
@RestController
@RequestMapping("/api/v1/creditos-ciclo")
@RequiredArgsConstructor
public class CreditosCicloController {

    private final CreditosCicloService creditosCicloService;

    @GetMapping
    public List<CreditosCicloResponse> listar(@RequestParam(required = false) Long idCicloPlan) {
        return creditosCicloService.listar(idCicloPlan);
    }

    @GetMapping("/{id}")
    public CreditosCicloResponse obtener(@PathVariable Long id) {
        return creditosCicloService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreditosCicloResponse crear(@Valid @RequestBody CreditosCicloRequest request) {
        return creditosCicloService.crear(request);
    }

    @PutMapping("/{id}")
    public CreditosCicloResponse actualizar(@PathVariable Long id, @Valid @RequestBody CreditosCicloRequest request) {
        return creditosCicloService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        creditosCicloService.eliminar(id);
    }
}
