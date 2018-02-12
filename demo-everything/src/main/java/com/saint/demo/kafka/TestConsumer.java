package com.saint.demo.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class TestConsumer {
    public static void main(String[] args){
        Properties properties = new Properties();

        properties.put("bootstrap.servers", "localhost:9092");
        System.out.println("this is the group part test 1");

        //消费者的组id
        properties.put("group.id", "GroupA");//这里是GroupA或者GroupB

        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");

        //从poll(拉)的回话处理时长
        properties.put("session.timeout.ms", "30000");
        //poll的数量限制
        //props.put("max.poll.records", "100");

        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        //订阅主题列表topic
        consumer.subscribe(Arrays.asList("foo"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                //　正常这里应该使用线程池处理，不应该在这里处理
                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value()+"\n");

        }
    }
}
