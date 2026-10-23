package pe.edu.upeu.lamb.planificacion.client;

import pe.edu.upeu.lamb.planificacion.dto.VigenciaCursoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Resuelve lamb-curriculo-ms por su nombre en Eureka, sin URL fija
@FeignClient(name = "lamb-curriculo-ms")
public interface CurriculoClient {

    @GetMapping("/api/v1/planes-estudio/cursos/{codigoCurso}/vigencia")
    VigenciaCursoDto consultarVigencia(@PathVariable("codigoCurso") String codigoCurso);
}
