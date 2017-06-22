package com.affey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Config {

	@Value("${aether.db.driver}")
	  private String aetherDbDriver;

	
}
