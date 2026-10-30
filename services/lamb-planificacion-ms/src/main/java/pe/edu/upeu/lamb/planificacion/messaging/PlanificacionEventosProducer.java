package pe.edu.upeu.lamb.planificacion.messaging;

import pe.edu.upeu.lamb.planificacion.event.GrupoAbiertoEvento;
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

    private final KafkaTemplate<String, GrupoAbiertoEvento> kafkaTemplate;

    @Value("${app.kafka.topic.planificacion}")
    private String topicPlanificacion;

    // Se envia solo si el grupo quedo guardado (afterCommit)
    public void publicarTrasCommit(GrupoAbiertoEvento evento) {
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

    private void enviar(GrupoAbiertoEvento evento) {
        kafkaTemplate.send(topicPlanificacion, String.valueOf(evento.getGrupoId()), evento)
                .whenComplete((resultado, ex) -> {
                    if (ex != null) {
                        log.error("component=producer topic={} eventType={} grupoId={} status=error error=\"{}\"",
                                topicPlanificacion, evento.getTipoEvento(), evento.getGrupoId(), ex.getMessage());
                        return;
                    }
                    log.info("component=producer topic={} partition={} offset={} eventType={} grupoId={} status=published",
                            resultado.getRecordMetadata().topic(),
                            resultado.getRecordMetadata().partition(),
                            resultado.getRecordMetadata().offset(),
                            evento.getTipoEvento(), evento.getGrupoId());
                });
    }
}
