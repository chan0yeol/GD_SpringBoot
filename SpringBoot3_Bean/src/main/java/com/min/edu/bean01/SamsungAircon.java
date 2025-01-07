package com.min.edu.bean01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SamsungAircon implements RemoteController {

	@Override
	@Bean(name="airconON")
	public FunctionExecution powerOn() {
		log.info("@Configuration 으로 생성한 Aircon powerOn");
		return new FunctionExecution("에어켠 킴", "에어컨");
	}

	@Override
	@Bean
	public FunctionExecution powerOff() {
		log.info("@Configuration 으로 생성한 Aircon powerOff");
		return new FunctionExecution("에어켠 끔", "에어컨");
	}

}
