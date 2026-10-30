package pe.edu.upeu.lamb.admision.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lamb.admision.entity.TipoDocumento;
import pe.edu.upeu.lamb.admision.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.admision.repository.TipoDocumentoRepository;

import java.util.List;

@Tag(name = "Tipos de Documento", description = "Catalogo de tipos de documento aceptados en admision")
@RestController
@RequestMapping("/api/v1/tipos-documento")
@RequiredArgsConstructor
public class TipoDocumentoController {

    private final TipoDocumentoRepository repository;

    @GetMapping
    public List<TipoDocumento> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public TipoDocumento obtener(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento no encontrado: " + id));
    }
}
