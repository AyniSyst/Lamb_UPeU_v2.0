package pe.edu.upeu.lamb.admision.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.admision.entity.ModalidadAdmision;
import pe.edu.upeu.lamb.admision.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.admision.repository.ModalidadAdmisionRepository;

import java.util.List;

@Tag(name = "Modalidades de Admision", description = "Catalogo de modalidades (ORDINARIA, TRASLADO, CONVENIO, DEPORTISTA)")
@RestController
@RequestMapping("/api/v1/modalidades-admision")
@RequiredArgsConstructor
public class ModalidadAdmisionController {

    private final ModalidadAdmisionRepository repository;

    @GetMapping
    public List<ModalidadAdmision> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ModalidadAdmision obtener(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Modalidad no encontrada: " + id));
    }
}
