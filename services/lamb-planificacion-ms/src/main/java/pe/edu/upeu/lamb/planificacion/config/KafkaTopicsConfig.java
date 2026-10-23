package pe.edu.upeu.lamb.planificacion.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

// Declara los topics que publica y consume: existen con 3 particiones sin importar quien arranque primero
@Configuration
public class KafkaTopicsConfig {

    @Bean
    public NewTopic planificacionEventos(@Value("${app.kafka.topic.planificacion}") String nombre) {
        return TopicBuilder.name(nombre).partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic evaluacionEventos(@Value("${app.kafka.topic.evaluacion}") String nombre) {
        return TopicBuilder.name(nombre).partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic curriculoEventos(@Value("${app.kafka.topic.curriculo}") String nombre) {
        return TopicBuilder.name(nombre).partitions(3).replicas(1).build();
    }
}
