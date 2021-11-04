package com.singgih.image.management.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	@Value(value = "${kafka.consumer.concurrency:}")
	private String concurrency;
	@Value("${kafka.brokers:}")
	private String kafkaBrokersAddress;
	@Value("${kafka.user:}")
	private String user;
	@Value("${kafka.password:}")
	private String pass;
	@Value("${kafka.principal.name:}")
	private String principalName;
	@Value("${kafka.service.name:}")
	private String serviceName;
	@Value("${kafka.keytab.file.location:}")
	private String keyTabFileLocation;
	@Value("${kafka.config.file.location:}")
	private String krb5ConfigFileLocation;

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokersAddress);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		props.put(ConsumerConfig.ACKS_CONFIG, Integer.toString(-1));

//		if ((user != null) && (!user.equals(""))) {
//			props.put("sasl.mechanism", "PLAIN");
//			props.put("security.protocol", "SASL_PLAINTEXT");
//			String jaasTemplate = "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"%s\" password=\"%s\";";
//			String jaasCfg = String.format(jaasTemplate, new Object[] { user, pass });
//			props.put("sasl.jaas.config", jaasCfg);
//		}
//		if ((principalName != null) && (!principalName.equals(""))) {
//			props.put("sasl.mechanism", "GSSAPI");
//			props.put("security.protocol", "SASL_PLAINTEXT");
//			String jaasTemplate = "com.sun.security.auth.module.Krb5LoginModule required doNotPrompt=true useKeyTab=true storeKey=false useTicketCache=false keyTab=\"%s\" principal=\"%s\";";
//			String jaasCfg = String.format(jaasTemplate, new Object[] { keyTabFileLocation, principalName });
//			props.put("sasl.jaas.config", jaasCfg);
//			props.put("sasl.kerberos.service.name", serviceName);
//
//			if ((krb5ConfigFileLocation != null) && (!krb5ConfigFileLocation.trim().isEmpty())) {
//				System.setProperty("java.security.krb5.conf", krb5ConfigFileLocation);
//			}
//		}

		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setConcurrency(Integer.parseInt(concurrency));
//		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
//		factory.getContainerProperties().setSyncCommits(true);
		return factory;
	}
}