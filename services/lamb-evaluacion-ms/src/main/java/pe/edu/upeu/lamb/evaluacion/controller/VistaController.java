package pe.edu.upeu.lamb.evaluacion.controller;

import pe.edu.upeu.lamb.evaluacion.dto.ActaNotasResponse;
import pe.edu.upeu.lamb.evaluacion.dto.EstadisticaGrupoResponse;
import pe.edu.upeu.lamb.evaluacion.dto.HistorialActaResponse;
import pe.edu.upeu.lamb.evaluacion.dto.MeritoResponse;
import pe.edu.upeu.lamb.evaluacion.dto.NotaFinalResponse;
import pe.edu.upeu.lamb.evaluacion.dto.ResultadoPreguntaResponse;
import pe.edu.upeu.lamb.evaluacion.service.VistaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Vistas calculadas: solo lectura, se arman con datos de otros CRUD y no tienen tabla propia
@Tag(name = "Vistas", description = "Vistas calculadas de evaluacion (solo lectura)")
@RestController
@RequestMapping("/api/v1/vistas")
@RequiredArgsConstructor
public class VistaController {

    private final VistaService vistaService;

    @GetMapping("/acta-notas/{idActa}")
    public ActaNotasResponse actaNotas(@PathVariable Long idActa) {
        return vistaService.actaNotas(idActa);
    }

    @GetMapping("/historial-acta/{idActa}")
    public List<HistorialActaResponse> historialActa(@PathVariable Long idActa) {
        return vistaService.historialActa(idActa);
    }

    @GetMapping("/nota-final")
    public NotaFinalResponse notaFinal(@RequestParam Long idGrupo, @RequestParam Long idPersona) {
        return vistaService.notaFinal(idGrupo, idPersona);
    }

    // Promedio de grupo + estadistica de aprobados y desaprobados
    @GetMapping("/estadistica-grupo/{idGrupo}")
    public EstadisticaGrupoResponse estadisticaGrupo(@PathVariable Long idGrupo) {
        return vistaService.estadisticaGrupo(idGrupo);
    }

    // Cuadro de meritos / tercio superior del periodo
    @GetMapping("/cuadro-meritos")
    public List<MeritoResponse> cuadroMeritos(@RequestParam String periodo) {
        return vistaService.cuadroMeritos(periodo);
    }

    @GetMapping("/resultado-evaluacion-docente")
    public List<ResultadoPreguntaResponse> resultadoEvaluacionDocente(@RequestParam Long idEncuesta, @RequestParam Long idDocente) {
        return vistaService.resultadoEvaluacionDocente(idEncuesta, idDocente);
    }
}
