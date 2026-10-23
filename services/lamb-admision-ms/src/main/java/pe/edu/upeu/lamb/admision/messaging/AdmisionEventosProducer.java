package pe.edu.upeu.lamb.admision.messaging;

import pe.edu.upeu.lamb.admision.event.PostulanteAdmitidoEvento;
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
public class AdmisionEventosProducer {

    private final KafkaTemplate<String, PostulanteAdmitidoEvento> kafkaTemplate;

    @Value("${app.kafka.topic.admision}")
    private String topicAdmision;

    // Se envia solo si la admision quedo guardada (afterCommit)
    public void publicarTrasCommit(PostulanteAdmitidoEvento evento) {
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

    private void enviar(PostulanteAdmitidoEvento evento) {
        kafkaTemplate.send(topicAdmision, String.valueOf(evento.getIdPersona()), evento)
                .whenComplete((resultado, ex) -> {
                    if (ex != null) {
                        log.error("component=producer topic={} eventType={} idPersona={} status=error error=\"{}\"",
                                topicAdmision, evento.getTipoEvento(), evento.getIdPersona(), ex.getMessage());
                        return;
                    }
                    log.info("component=producer topic={} partition={} offset={} eventType={} idPersona={} status=published",
                            resultado.getRecordMetadata().topic(),
                            resultado.getRecordMetadata().partition(),
                            resultado.getRecordMetadata().offset(),
                            evento.getTipoEvento(), evento.getIdPersona());
                });
    }
}
