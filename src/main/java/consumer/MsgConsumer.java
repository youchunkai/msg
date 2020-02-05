package consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;

/**
 * Desc:
 * Author:Kevin
 * Date:2020/2/5
 **/
public class MsgConsumer{

    static KafkaConsumer consumer;

    static{
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.16.146.121:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"epserver");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,"10000");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "5000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumer = new KafkaConsumer(properties);
    }

    public List<MsgRecord> subscribe(String topic) {
        List<MsgRecord> msgRecords = new ArrayList<MsgRecord>();

        consumer.subscribe(Collections.singleton(topic));
        ConsumerRecords<String, String> consumerRecords = consumer.poll(1000);
        for (ConsumerRecord<String, String> record : consumerRecords) {
            msgRecords.add(new MsgRecord(record.key(), record.value(), record.offset()));
        }
        return msgRecords;
    }

}
