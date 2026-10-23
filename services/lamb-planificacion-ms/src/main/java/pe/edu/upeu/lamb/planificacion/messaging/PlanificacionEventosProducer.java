package pe.edu.upeu.lamb.planificacion.messaging;

import pe.edu.upeu.lamb.planificacion.event.SeccionAbiertaEvento;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlanificacionEventosProducer {

    private final KafkaTemplate<String, SeccionAbiertaEvento> kafkaTemplate;

    @Value("${app.kafka.topic.planificacion}")
    private String topicPlanificacion;

    // Se envia solo si la seccion quedo guardada (afterCommit)
    public void publicarTrasCommit(SeccionAbiertaEvento evento) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    enviar(evento);
                }
            });
        } else {
            enviar(evento);
        }
    }

    private void enviar(SeccionAbiertaEvento evento) {
        kafkaTemplate.send(topicPlanificacion, String.valueOf(evento.getSeccionId()), evento)
                .whenComplete((resultado, ex) -> {
                    if (ex != null) {
                        log.error("component=producer topic={} eventType={} seccionId={} status=error error=\"{}\"",
                                topicPlanificacion, evento.getTipoEvento(), evento.getSeccionId(), ex.getMessage());
                        return;
                    }
                    log.info("component=producer topic={} partition={} offset={} eventType={} seccionId={} status=published",
                            resultado.getRecordMetadata().topic(),
                            resultado.getRecordMetadata().partition(),
                            resultado.getRecordMetadata().offset(),
                            evento.getTipoEvento(), evento.getSeccionId());
                });
    }
}
