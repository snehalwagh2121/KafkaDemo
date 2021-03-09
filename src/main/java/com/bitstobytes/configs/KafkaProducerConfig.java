package com.bitstobytes.configs;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.bitstobytes.model.SomeModel;
import com.bitstobytes.serializedeserialize.SomeModelSerialization;

@Configuration
public class KafkaProducerConfig {

	@Value(value = "${kafka.bootstrapaddr}")
	private String bootstrapaddr;
	@Bean
	public ProducerFactory<String, SomeModel> producerFactory(){
		Map<String, Object> configProperties =new HashMap<String, Object>();
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapaddr);
		configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, SomeModelSerialization.class);
		configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, SomeModelSerialization.class);
		
		return new DefaultKafkaProducerFactory<String, SomeModel>(configProperties);
	}
	
	@Bean
	public KafkaTemplate<String, SomeModel> kafkaTemplate(){
		return new KafkaTemplate<String, SomeModel>(producerFactory());
	}
}
