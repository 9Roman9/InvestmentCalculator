package com.investment_calculator;

/**
 * This class throw exception in case of absence of country in the database
 * where the investor is going to invest money.
 */
public class CountryException extends Exception{
    public CountryException(String message) {
        super(message);
    }
}
