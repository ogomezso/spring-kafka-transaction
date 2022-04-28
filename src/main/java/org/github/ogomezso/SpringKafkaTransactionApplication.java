package org.github.ogomezso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableJpaRepositories
public class SpringKafkaTransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaTransactionApplication.class, args);
    }

}
