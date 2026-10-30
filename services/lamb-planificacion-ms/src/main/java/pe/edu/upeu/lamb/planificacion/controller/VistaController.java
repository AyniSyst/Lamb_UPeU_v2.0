package pe.edu.upeu.lamb.planificacion.controller;

import pe.edu.upeu.lamb.planificacion.dto.HorarioEstudianteResponse;
import pe.edu.upeu.lamb.planificacion.service.VistaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Vistas calculadas: solo lectura, se arman con datos de otros CRUD y no tienen tabla propia
@Tag(name = "Vistas", description = "Vistas calculadas de planificacion (solo lectura)")
@RestController
@RequestMapping("/api/v1/vistas")
@RequiredArgsConstructor
public class VistaController {

    private final VistaService vistaService;

    // FUSION: hoy recibe los grupos del estudiante; al fusionar, obtenerlos de lamb-matricula-ms por el idPersona del JWT
    @GetMapping("/horario-estudiante")
    public List<HorarioEstudianteResponse> horarioEstudiante(@RequestParam List<Long> idsGrupo) {
        return vistaService.horarioEstudiante(idsGrupo);
    }
}
