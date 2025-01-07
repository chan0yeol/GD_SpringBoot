package com.min.edu.bean01;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SamsungTelevision implements RemoteController {

	@Override
	public FunctionExecution powerOn() {
		log.info("@Component로 생성한 powerOn");
		return new FunctionExecution("tv 켜다", "텔레비전");
	}

	@Override
	public FunctionExecution powerOff() {
		log.info("@Component로 생성한 powerOff");
		return new FunctionExecution("tv 끄다", "텔레비전");
	}

}
