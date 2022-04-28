package org.github.ogomezso.kafka;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.github.ogomezso.h2.H2Entity;
import org.github.ogomezso.h2.H2Repository;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = "tx-input-topic")
public class TxMessageProcessor {

    private final H2Repository h2Repository;
    private final KafkaTemplate<Long, String> kafkaTemplate;

    @SneakyThrows
    @KafkaHandler
    public void processMessages(String msg) {

        saveToDb(msg);
    }

    @Transactional("dstm")
    public void saveToDb(String msgToSave) {
        H2Entity entityToSave = new H2Entity(msgToSave);
        h2Repository.save(entityToSave);
        sendToKafka(msgToSave);
    }

    @Transactional("kafkaTransactionManager")
    public void sendToKafka(String msgToSend) {
        kafkaTemplate.send("tx-output-topic", msgToSend);
    }

}
