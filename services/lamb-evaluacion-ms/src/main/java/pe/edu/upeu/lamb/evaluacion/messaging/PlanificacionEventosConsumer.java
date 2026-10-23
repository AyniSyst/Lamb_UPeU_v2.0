package pe.edu.upeu.lamb.evaluacion.messaging;

import pe.edu.upeu.lamb.evaluacion.event.SeccionAbiertaEvento;
import pe.edu.upeu.lamb.evaluacion.service.ActaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlanificacionEventosConsumer {

    private static final String SECCION_ABIERTA = "seccion.abierta";

    private final ActaService actaService;

    @KafkaListener(topics = "${app.kafka.topic.planificacion}")
    public void alRecibirSeccion(SeccionAbiertaEvento evento) {
        if (!SECCION_ABIERTA.equals(evento.getTipoEvento())) {
            log.warn("component=consumer eventType={} status=ignored", evento.getTipoEvento());
            return;
        }
        log.info("component=consumer eventType={} seccionId={} status=consumed",
                evento.getTipoEvento(), evento.getSeccionId());
        actaService.procesar(evento);
    }
}
