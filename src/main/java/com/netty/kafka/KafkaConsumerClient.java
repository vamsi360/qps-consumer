package com.netty.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Properties;

/**
 * Created by vamsi on 10/7/17.
 */
public class KafkaConsumerClient {
    public static final String testTopic_1 = "test_topic_1";
    public static final int partitionsForTestTopic = 3;
    public static final int pollTimeMs = 100;
    public static final String kafkaBrokerHost = "localhost";
    public static final int kafkaBrokerPort = 9092;
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerClient.class);

    public static void main(String args[]) throws InterruptedException {
        Properties properties = new Properties();
        properties.put("bootstrap.servers",
                String.format("%s:%d", kafkaBrokerHost, kafkaBrokerPort));
        properties.put("group.id", "group-1");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(testTopic_1));

//        List<TopicPartition> topicPartitions = new ArrayList<>();
//        IntStream.range(0, partitionsForTestTopic).forEach(i -> {
//            topicPartitions.add(new TopicPartition(testTopic_1, i));
//        });
//        consumer.assign(topicPartitions);
//        consumer.seekToBeginning(topicPartitions);

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(pollTimeMs);
            for (ConsumerRecord<String, String> record : records) {
                logger.info("" + record);
            }
        }
    }
}
