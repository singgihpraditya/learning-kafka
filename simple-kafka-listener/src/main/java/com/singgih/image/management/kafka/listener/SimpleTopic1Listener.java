package com.singgih.image.management.kafka.listener;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.singgih.image.management.entity.RequestEntity;
import com.singgih.image.management.util.Constants;
import com.singgih.image.management.util.Util;

@Component
public class SimpleTopic1Listener {
	private final String SERVICE_NAME = "SIMPLE_TOPIC_1_LISTENER";
	private Logger logger = LoggerFactory.getLogger(SimpleTopic1Listener.class);
	private CountDownLatch latch = new CountDownLatch(3);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@KafkaListener(topics = "${message.simple.topic.1.name}", groupId = "${message.topic.groupid}")
	public void listen(String message) {
		String hashCode = Util.getHashCode(SERVICE_NAME);
		long start = System.currentTimeMillis();
		try {
			logger.info(hashCode + "---------- Start " + SERVICE_NAME + " ----------");
			logger.debug(hashCode + "Received Message : " + message);

			RequestEntity requestEntity = new Gson().fromJson(message, RequestEntity.class);
			logger.debug(hashCode + "requestEntity: " + requestEntity);

			logger.debug(hashCode + "Send message to : " + Constants.SIMPLE_TOPIC_2);
			kafkaTemplate.send(Constants.SIMPLE_TOPIC_2, message);
		} catch (Throwable ex) {
			logger.error("Error", ex);
		} finally {
			long execTime = (System.currentTimeMillis() - start);
			logger.info(hashCode + "----------End " + SERVICE_NAME + " execute on " + execTime + " millis ----------");
			logger.info(hashCode);
		}
		latch.countDown();
	}

}
