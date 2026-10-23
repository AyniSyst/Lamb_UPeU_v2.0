package pe.edu.upeu.lamb.evaluacion.messaging;

import pe.edu.upeu.lamb.evaluacion.event.ActaEvento;
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
public class EvaluacionEventosProducer {

    private final KafkaTemplate<String, ActaEvento> kafkaTemplate;

    @Value("${app.kafka.topic.evaluacion}")
    private String topicEvaluacion;

    // Se envia solo si el acta quedo guardada (afterCommit)
    public void publicarTrasCommit(ActaEvento evento) {
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

    private void enviar(ActaEvento evento) {
        kafkaTemplate.send(topicEvaluacion, String.valueOf(evento.getSeccionId()), evento)
                .whenComplete((resultado, ex) -> {
                    if (ex != null) {
                        log.error("component=producer topic={} eventType={} seccionId={} status=error error=\"{}\"",
                                topicEvaluacion, evento.getTipoEvento(), evento.getSeccionId(), ex.getMessage());
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
