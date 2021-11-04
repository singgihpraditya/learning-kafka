package com.singgih.simple.kafka.client.util;

import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.singgih.simple.kafka.client.config.KafkaConfig;
import com.singgih.simple.kafka.client.config.KafkaProducerFactory;

public class KafkaService {
	private Logger logger = LoggerFactory.getLogger(KafkaService.class);
	private String hashCode = Util.getHashCode();

	public KafkaService() {
	}

	public KafkaService(Logger logger, String hashCode) {
		this.logger = logger;
		this.hashCode = hashCode;
	}

	public RecordMetadata sendMessageToTopic(String topicName, String message) throws Exception {
		logger.info(hashCode + " Send message to topic");
		logger.debug(hashCode + " Topic name 	: " + topicName);
		logger.debug(hashCode + " Message 		: " + message);

		KafkaProducerFactory kafkaProducerFactory = new KafkaProducerFactory();
		KafkaConfig kafkaConfig = new KafkaConfig();
		Properties properties = Util.getProperties();
		kafkaConfig.setKafkaBrokersAddress(properties.getProperty("kafka.brokers", ""));

		try {
			KafkaProducer<String, String> kafkaProducer = kafkaProducerFactory.createProducer(kafkaConfig);
			RecordMetadata recMetadata = kafkaProducer.send(new ProducerRecord<String, String>(topicName, message)).get();
			logger.info(hashCode + " Success send message to topic");
			logger.debug(hashCode + " Topic name 	: " + topicName);
			logger.debug(hashCode + " Partition 	: " + recMetadata.partition());
			logger.debug(hashCode + " Timestamp 	: " + (recMetadata.hasTimestamp()?new Date(recMetadata.timestamp()):""));
			logger.debug(hashCode + " Offset 		: " + (recMetadata.hasOffset()?recMetadata.offset():""));
					
			return recMetadata;
		} catch (Exception ex) {
			logger.error(hashCode + " Failed send message, error : "+ex.getMessage());
			throw ex;
		}
	}
}
