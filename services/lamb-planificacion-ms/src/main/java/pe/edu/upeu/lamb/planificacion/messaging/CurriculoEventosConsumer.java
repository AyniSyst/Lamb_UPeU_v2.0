package pe.edu.upeu.lamb.planificacion.messaging;

import pe.edu.upeu.lamb.planificacion.event.PlanPublicadoEvento;
import pe.edu.upeu.lamb.planificacion.service.SeccionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurriculoEventosConsumer {

    private static final String PLAN_PUBLICADO = "plan.publicado";

    private final SeccionService seccionService;

    @KafkaListener(topics = "${app.kafka.topic.curriculo}",
            properties = "spring.json.value.default.type=pe.edu.upeu.lamb.planificacion.event.PlanPublicadoEvento")
    public void alRecibirPlan(PlanPublicadoEvento evento) {
        if (!PLAN_PUBLICADO.equals(evento.getTipoEvento())) {
            log.warn("component=consumer eventType={} status=ignored", evento.getTipoEvento());
            return;
        }
        log.info("component=consumer eventType={} planId={} status=consumed",
                evento.getTipoEvento(), evento.getPlanId());
        seccionService.revalidarPendientes(evento.getCodigosCursos());
    }
}
