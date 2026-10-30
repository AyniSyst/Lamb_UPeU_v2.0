package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.CursoResponse;
import pe.edu.upeu.lamb.planificacion.service.CursoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Solo lectura: los cursos se definen en curriculo (CRUD 12) y llegan aqui con el evento plan.publicado
@Tag(name = "Curso", description = "Copia local (solo lectura) del catalogo de cursos de curriculo")
@RestController
@RequestMapping("/api/v1/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    public List<CursoResponse> listar() {
        return cursoService.listar();
    }

    @GetMapping("/{id}")
    public CursoResponse obtener(@PathVariable Long id) {
        return cursoService.obtener(id);
    }
}
