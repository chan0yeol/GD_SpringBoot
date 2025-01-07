package com.min.etc;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Television implements IFunctionTelevision {

	@Override
	public void powerOn() {
		log.info("텔레비전 켜기");
	}

	@Override
	public void powerOff() {
		log.info("텔레비전 끄기");
	}

}
