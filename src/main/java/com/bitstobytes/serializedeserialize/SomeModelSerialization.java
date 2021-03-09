package com.bitstobytes.serializedeserialize;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.bitstobytes.model.SomeModel;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SomeModelSerialization implements Serializer<SomeModel>{

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] serialize(String topic, SomeModel data) {
		byte[] realval=null;
		
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			realval= objectMapper.writeValueAsString(data).getBytes();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return realval;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub		
	}

}
