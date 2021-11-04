package test.com.singgih.simple.kafka.client;

import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.singgih.simple.kafka.client.entity.RequestEntity;
import com.singgih.simple.kafka.client.util.KafkaService;
import com.singgih.simple.kafka.client.util.Util;

import junit.framework.Assert;


public class TestPutMessage {
	private Logger logger = LoggerFactory.getLogger(TestPutMessage.class);
	@Test
	public void putMessage() {
		String testServiceName = "TestPutMessage";
		String hashCode = Util.getHashCode(testServiceName);
		long start = System.currentTimeMillis();
		KafkaService kafkaService = new KafkaService(logger, hashCode);
		try {
			logger.info(hashCode + "---------- Start " + testServiceName + " ----------");
			Properties properties = Util.getProperties();
			RequestEntity requestEntity = new RequestEntity();
			requestEntity.setId(Util.getID());
			String message = new Gson().toJson(requestEntity);
			kafkaService.sendMessageToTopic(properties.getProperty("message.simple.topic.1.name"), message);
			Assert.assertTrue(true);
		} catch (Throwable ex) {
			logger.error("Error", ex);
			Assert.assertTrue(false);
		} finally {
			long execTime = (System.currentTimeMillis() - start);
			logger.info(hashCode + "----------End " + testServiceName + " execute on " + execTime + " millis ----------");
			logger.info(hashCode);
		}
	}
}
