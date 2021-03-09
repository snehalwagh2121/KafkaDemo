package com.bitstobytes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitstobytes.model.SomeModel;
import com.google.gson.Gson;


@Controller
@RequestMapping("kafka")
public class KafkaController {

	@Autowired
	KafkaTemplate<String, SomeModel> kafkaTemplate;
	
	static final String producer="DEMO_PRODUCER_TOPIC";
	static final String  consumer="DEMO_CONSUMER_TOPIC";
	
	SomeModel model=new SomeModel("demouser",24);
	String modelString= model.toString();
	
	@GetMapping("test")
	public String testApplication() {
		System.out.println("Its working !!!!");
		return "test";
	}
	
	@GetMapping("sendKakfaMessage")
	public String sendMessage() {
		kafkaTemplate.send(producer,model);
		System.out.println("message sent to producer");
		return "test";
	}

	@KafkaListener(topics = producer)
	public String consumeMessage(String message) {
		System.out.println("MESSAGE RECEIVED : "+message);	
		Gson gson=new Gson();
		SomeModel model= gson.fromJson(message, SomeModel.class);
		System.out.println("name : "+model.getName());
		System.out.println("age : "+model.getAge());
		System.out.println(" Model after gson convertion "+model.toString());
		kafkaTemplate.send(consumer,model);
		return "test";
	}
}
