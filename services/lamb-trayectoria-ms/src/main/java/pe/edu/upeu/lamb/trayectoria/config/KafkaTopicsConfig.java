package pe.edu.upeu.lamb.trayectoria.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

// Solo consume: declara los topics para que existan aunque arranque antes que los productores
@Configuration
public class KafkaTopicsConfig {

    @Bean
    public NewTopic admisionEventos(@Value("${app.kafka.topic.admision}") String nombre) {
        return TopicBuilder.name(nombre).partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic evaluacionEventos(@Value("${app.kafka.topic.evaluacion}") String nombre) {
        return TopicBuilder.name(nombre).partitions(3).replicas(1).build();
    }
}
