package pe.edu.upeu.lamb.planificacion.messaging;

import pe.edu.upeu.lamb.planificacion.event.ActaEvento;
import pe.edu.upeu.lamb.planificacion.service.SeccionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EvaluacionEventosConsumer {

    private static final String ACTA_ABIERTA = "acta.abierta";
    private static final String ACTA_RECHAZADA = "acta.rechazada";

    private final SeccionService seccionService;

    @KafkaListener(topics = "${app.kafka.topic.evaluacion}",
            properties = "spring.json.value.default.type=pe.edu.upeu.lamb.planificacion.event.ActaEvento")
    public void alRecibirActa(ActaEvento evento) {
        switch (evento.getTipoEvento()) {
            case ACTA_ABIERTA -> {
                log.info("component=consumer eventType={} seccionId={} status=consumed",
                        evento.getTipoEvento(), evento.getSeccionId());
                seccionService.marcarAbierta(evento.getSeccionId());
            }
            case ACTA_RECHAZADA -> {
                log.info("component=consumer eventType={} seccionId={} status=consumed",
                        evento.getTipoEvento(), evento.getSeccionId());
                seccionService.compensar(evento.getSeccionId(), evento.getMotivo());
            }
            default -> log.warn("component=consumer eventType={} status=ignored", evento.getTipoEvento());
        }
    }
}
