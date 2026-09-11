package pe.edu.upeu.lamb.admision.controller;

import pe.edu.upeu.lamb.admision.dto.EvaluarPostulacionRequest;
import pe.edu.upeu.lamb.admision.dto.PostulacionRequest;
import pe.edu.upeu.lamb.admision.dto.PostulacionResponse;
import pe.edu.upeu.lamb.admision.service.PostulacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/postulaciones")
@RequiredArgsConstructor
public class PostulacionController {

    private final PostulacionService postulacionService;

    @GetMapping
    public List<PostulacionResponse> listar() {
        return postulacionService.listar();
    }

    @GetMapping("/{id}")
    public PostulacionResponse obtener(@PathVariable Long id) {
        return postulacionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostulacionResponse crear(@Valid @RequestBody PostulacionRequest request) {
        return postulacionService.crear(request);
    }

    @PostMapping("/{id}/evaluar")
    public PostulacionResponse evaluar(@PathVariable Long id, @Valid @RequestBody EvaluarPostulacionRequest request) {
        return postulacionService.evaluar(id, request);
    }

    @PostMapping("/{id}/admitir")
    public PostulacionResponse admitir(@PathVariable Long id) {
        return postulacionService.admitir(id);
    }

    @PostMapping("/{id}/rechazar")
    public PostulacionResponse rechazar(@PathVariable Long id) {
        return postulacionService.rechazar(id);
    }
}
