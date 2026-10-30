package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.entity.Acta;
import pe.edu.upeu.lamb.evaluacion.service.ActaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Acta", description = "Acta de notas del grupo: nace del evento grupo.abierto; por HTTP se consulta y se cierra")
// Las actas no se crean por HTTP: nacen del evento grupo.abierto. Por HTTP se consultan y se cierran
@RestController
@RequestMapping("/api/v1/actas")
@RequiredArgsConstructor
public class ActaController {

    private final ActaService actaService;

    @GetMapping
    public List<Acta> listar() {
        return actaService.listar();
    }

    @GetMapping("/{id}")
    public Acta obtener(@PathVariable Long id) {
        return actaService.obtener(id);
    }

    @PostMapping("/{id}/cerrar")
    public Acta cerrar(@PathVariable Long id) {
        return actaService.cerrar(id);
    }

    @GetMapping("/grupo/{idGrupo}")
    public Acta obtenerPorGrupo(@PathVariable Long idGrupo) {
        return actaService.obtenerPorGrupo(idGrupo);
    }
}
