package pe.edu.upeu.lamb.matricula.client;

import pe.edu.upeu.lamb.matricula.dto.SeccionDisponibleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Resuelve lamb-planificacion-ms por su nombre en Eureka, sin URL fija
@FeignClient(name = "lamb-planificacion-ms")
public interface PlanificacionClient {

    // La seccion de matricula es un grupo de planificacion (V3 renombro secciones -> grupos)
    @GetMapping("/api/v1/grupos/{id}")
    SeccionDisponibleDto obtenerSeccion(@PathVariable("id") Long id);
}
