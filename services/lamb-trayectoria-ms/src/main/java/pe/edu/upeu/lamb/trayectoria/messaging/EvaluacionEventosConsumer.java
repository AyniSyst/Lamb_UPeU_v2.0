package pe.edu.upeu.lamb.trayectoria.messaging;

import pe.edu.upeu.lamb.trayectoria.event.ActaCerradaEvento;
import pe.edu.upeu.lamb.trayectoria.service.TrayectoriaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EvaluacionEventosConsumer {

    private static final String ACTA_CERRADA = "acta.cerrada";

    private final TrayectoriaService trayectoriaService;

    // acta.abierta y acta.rechazada son para planificacion: aqui se ignoran
    @KafkaListener(topics = "${app.kafka.topic.evaluacion}",
            properties = "spring.json.value.default.type=pe.edu.upeu.lamb.trayectoria.event.ActaCerradaEvento")
    public void alRecibirActa(ActaCerradaEvento evento) {
        if (!ACTA_CERRADA.equals(evento.getTipoEvento())) {
            log.debug("component=consumer eventType={} status=ignored", evento.getTipoEvento());
            return;
        }
        log.info("component=consumer eventType={} actaId={} seccionId={} status=consumed",
                evento.getTipoEvento(), evento.getActaId(), evento.getSeccionId());
        trayectoriaService.registrarActa(evento);
    }
}
