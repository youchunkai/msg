package producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * Desc:
 * Author:Kevin
 * Date:2020/2/5
 **/
public class MsgProducer {

    private static KafkaProducer producer;

    static{
        Properties properties=new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"10.16.146.121:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"xasync-producer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producer=new KafkaProducer<Integer, String>(properties);
    }

    /**
     * @param topic 主题
     * @param msg   the msg to send
     **/
    public void sendMsg(String topic,String msg){
        producer.send(new ProducerRecord(topic, msg), new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                System.out.println(recordMetadata.topic()+"->"+recordMetadata.partition()+"->"+recordMetadata.offset());
            }
        });
    }
}
