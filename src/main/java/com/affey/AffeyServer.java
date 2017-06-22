package com.affey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@ComponentScan("com.affey")
@PropertySource("classpath:local.properties")
@EnableAutoConfiguration(exclude = VelocityAutoConfiguration.class)
@EnableTransactionManagement
@EnableSwagger2
public class AffeyServer {
	public static void main(String[] args) {
	    SpringApplication.run(AffeyServer.class, args);
	  }
	

	  @Bean
	  public Docket affeyApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	        .paths(PathSelectors.ant("/v1/api/**"))
	        .build()
	        .apiInfo(
	            new ApiInfo("Affey APIs", "APIs to book tickets in theatres", "v1", "",
	                "saugupta@adobe.com", "", ""));
	  }
	  
}
