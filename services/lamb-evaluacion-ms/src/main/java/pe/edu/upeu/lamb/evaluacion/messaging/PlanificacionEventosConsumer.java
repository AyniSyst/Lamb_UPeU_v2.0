package pe.edu.upeu.lamb.evaluacion.messaging;

import pe.edu.upeu.lamb.evaluacion.event.GrupoAbiertoEvento;
import pe.edu.upeu.lamb.evaluacion.service.ActaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlanificacionEventosConsumer {

    private static final String GRUPO_ABIERTO = "grupo.abierto";

    private final ActaService actaService;

    @KafkaListener(topics = "${app.kafka.topic.planificacion}")
    public void alRecibirGrupo(GrupoAbiertoEvento evento) {
        if (!GRUPO_ABIERTO.equals(evento.getTipoEvento())) {
            log.warn("component=consumer eventType={} status=ignored", evento.getTipoEvento());
            return;
        }
        log.info("component=consumer eventType={} grupoId={} status=consumed",
                evento.getTipoEvento(), evento.getGrupoId());
        actaService.procesar(evento);
    }
}
