package pe.edu.upeu.lamb.trayectoria.messaging;

import pe.edu.upeu.lamb.trayectoria.event.PostulanteAdmitidoEvento;
import pe.edu.upeu.lamb.trayectoria.service.TrayectoriaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdmisionEventosConsumer {

    private static final String POSTULANTE_ADMITIDO = "postulante.admitido";

    private final TrayectoriaService trayectoriaService;

    @KafkaListener(topics = "${app.kafka.topic.admision}",
            properties = "spring.json.value.default.type=pe.edu.upeu.lamb.trayectoria.event.PostulanteAdmitidoEvento")
    public void alRecibirAdmision(PostulanteAdmitidoEvento evento) {
        if (!POSTULANTE_ADMITIDO.equals(evento.getTipoEvento())) {
            log.warn("component=consumer eventType={} status=ignored", evento.getTipoEvento());
            return;
        }
        log.info("component=consumer eventType={} idPersona={} status=consumed",
                evento.getTipoEvento(), evento.getIdPersona());
        trayectoriaService.registrarIngreso(evento);
    }
}
