package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.entity.Acta;
import pe.edu.upeu.lamb.evaluacion.service.ActaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Las actas no se crean por HTTP: nacen del evento seccion.abierta. Por HTTP se consultan y se cierran
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

    @GetMapping("/seccion/{idSeccion}")
    public Acta obtenerPorSeccion(@PathVariable Long idSeccion) {
        return actaService.obtenerPorSeccion(idSeccion);
    }
}
