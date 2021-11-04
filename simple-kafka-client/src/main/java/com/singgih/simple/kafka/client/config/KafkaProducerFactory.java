package com.singgih.simple.kafka.client.config;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import com.singgih.simple.kafka.client.util.Util;

public class KafkaProducerFactory {

	public KafkaProducer<String, String> createProducer(KafkaConfig kafkaConfig) {
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getKafkaBrokersAddress());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

		props.put(ProducerConfig.ACKS_CONFIG, Integer.toString(-1));
		props.put("key.serializer", StringSerializer.class.getName());
		props.put("value.serializer", StringSerializer.class.getName());
//
//	    props.put("request.timeout.ms", "25000");
//	    props.put("delivery.timeout.ms", "27000");
		
		props.put("max.block.ms", "29000");
		props.put(ProducerConfig.CLIENT_ID_CONFIG, Util.getID());
		if ((kafkaConfig.getUser() != null) && (!kafkaConfig.getUser().equals(""))) {
			props.put("sasl.mechanism", "PLAIN");
			props.put("security.protocol", "SASL_PLAINTEXT");
			String jaasTemplate = "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"%s\" password=\"%s\";";
			String jaasCfg = String.format(jaasTemplate, new Object[] { kafkaConfig.getUser(), kafkaConfig.getPass() });
			props.put("sasl.jaas.config", jaasCfg);
		}
		if ((kafkaConfig.getPrincipalName() != null) && (!kafkaConfig.getPrincipalName().equals(""))) {
			props.put("sasl.mechanism", "GSSAPI");
			props.put("security.protocol", "SASL_PLAINTEXT");
			String jaasTemplate = "com.sun.security.auth.module.Krb5LoginModule required doNotPrompt=true useKeyTab=true storeKey=false useTicketCache=false keyTab=\"%s\" principal=\"%s\";";
			String jaasCfg = String.format(jaasTemplate, new Object[] { kafkaConfig.getKeyTabFileLocation(), kafkaConfig.getPrincipalName() });
			props.put("sasl.jaas.config", jaasCfg);
			props.put("sasl.kerberos.service.name", kafkaConfig.getServiceName());

			if ((kafkaConfig.getKrb5ConfigFileLocation() != null) && (!kafkaConfig.getKrb5ConfigFileLocation().trim().isEmpty())) {
				System.setProperty("java.security.krb5.conf", kafkaConfig.getKrb5ConfigFileLocation());
			}
		}
		return new KafkaProducer(props);
	}
}