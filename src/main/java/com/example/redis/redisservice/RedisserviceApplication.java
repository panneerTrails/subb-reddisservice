package com.example.redis.redisservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableEurekaClient
public class RedisserviceApplication implements CommandLineRunner{

	RedisTemplate  redisTemplate;
    private static final Logger LOGGER= LoggerFactory.getLogger(RedisserviceApplication.class);

	public RedisserviceApplication(RedisTemplate  redisTemplate,RedisConnectionFactory  connectionFactory){

		this.redisTemplate=redisTemplate;
		this.redisTemplate.setConnectionFactory(connectionFactory);

	}



	public static void main(String[] args) {
		SpringApplication.run(RedisserviceApplication.class, args);
	}

   @Override
   public void run(String ...s )throws Exception{

	   LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$INSIDE SPRING BOOT APPLICATION ****************REDIS -{} " ,redisTemplate );
	   HashOperations<String, String, String> hash = redisTemplate.opsForHash();




	   String empJoeKey = "emp:joe";

	   String empJohnKey = "emp:john";

	   Map<String, String> empJoeMap = new HashMap<String, String>();
	   empJoeMap.put("name", "Joe");
	   empJoeMap.put("age", "32");
	   empJoeMap.put("id", "01");
	  Map<String, String> empJohnMap = new HashMap<String, String>();

	   empJohnMap.put("name", "John");
	   empJohnMap.put("age", "42");
	   empJohnMap.put("id", "02");



	   hash.putAll(empJoeKey, empJoeMap);
	   hash.putAll(empJohnKey, empJohnMap);




	   LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$  Get emp joe details **************** -{} " ,hash.entries(empJoeKey) );

	   LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$  Get emp john details **************** -{} " ,hash.entries(empJohnKey) );


   }
}
