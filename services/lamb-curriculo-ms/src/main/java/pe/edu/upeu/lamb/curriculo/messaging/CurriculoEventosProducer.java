package pe.edu.upeu.lamb.curriculo.messaging;

import pe.edu.upeu.lamb.curriculo.event.PlanPublicadoEvento;
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
public class CurriculoEventosProducer {

    private final KafkaTemplate<String, PlanPublicadoEvento> kafkaTemplate;

    @Value("${app.kafka.topic.curriculo}")
    private String topicCurriculo;

    // Se envia solo si la transaccion que publico el plan hizo commit
    public void publicarTrasCommit(PlanPublicadoEvento evento) {
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

    private void enviar(PlanPublicadoEvento evento) {
        kafkaTemplate.send(topicCurriculo, String.valueOf(evento.getPlanId()), evento)
                .whenComplete((resultado, ex) -> {
                    if (ex != null) {
                        log.error("component=producer topic={} eventType={} planId={} status=error error=\"{}\"",
                                topicCurriculo, evento.getTipoEvento(), evento.getPlanId(), ex.getMessage());
                        return;
                    }
                    log.info("component=producer topic={} partition={} offset={} eventType={} planId={} status=published",
                            resultado.getRecordMetadata().topic(),
                            resultado.getRecordMetadata().partition(),
                            resultado.getRecordMetadata().offset(),
                            evento.getTipoEvento(), evento.getPlanId());
                });
    }
}
