package com.roman.InvestmentCalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class InvestmentCalculatorApplication {
	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(InvestmentCalculatorApplication.class, args);
	}

	public static void finishApp(){
		context.close();
	}
}
