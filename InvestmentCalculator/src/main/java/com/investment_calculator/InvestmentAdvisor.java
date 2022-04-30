package com.investment_calculator;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * It's a main class that coordinates other classes.
 */
public class InvestmentAdvisor {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        String investmentMethodName;
        InvestmentMethodRequest investmentMethodRequest = context.getBean("investmentMethodRequest",InvestmentMethodRequest.class);
        investmentMethodName = investmentMethodRequest.request();

        InvestmentMethodFactory investmentMethodFactory = context.getBean("investmentMethodFactory",InvestmentMethodFactory.class);
        InvestmentMethod investmentMethod = investmentMethodFactory.create(investmentMethodName);
        System.out.println(investmentMethod.calculate());
    }
}