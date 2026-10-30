package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.PlantillaCertificadoRequest;
import pe.edu.upeu.lms.dto.PlantillaCertificadoResponse;
import pe.edu.upeu.lms.service.PlantillaCertificadoService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/lms")
@RequiredArgsConstructor
public class CertificadosController {

    private final PlantillaCertificadoService plantillaCertificadoService;

    // ---- Plantillas de certificado ----

    @GetMapping("/plantillas-certificado")
    public List<PlantillaCertificadoResponse> listarPlantillasCertificado() {
        return plantillaCertificadoService.listar();
    }

    @GetMapping("/plantillas-certificado/{id}")
    public PlantillaCertificadoResponse obtenerPlantillaCertificado(@PathVariable Long id) {
        return plantillaCertificadoService.obtener(id);
    }

    @PostMapping("/plantillas-certificado")
    @ResponseStatus(HttpStatus.CREATED)
    public PlantillaCertificadoResponse crearPlantillaCertificado(@Valid @RequestBody PlantillaCertificadoRequest request) {
        return plantillaCertificadoService.crear(request);
    }

    @PutMapping("/plantillas-certificado/{id}")
    public PlantillaCertificadoResponse actualizarPlantillaCertificado(@PathVariable Long id, @Valid @RequestBody PlantillaCertificadoRequest request) {
        return plantillaCertificadoService.actualizar(id, request);
    }

    @DeleteMapping("/plantillas-certificado/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPlantillaCertificado(@PathVariable Long id) {
        plantillaCertificadoService.eliminar(id);
    }
}
