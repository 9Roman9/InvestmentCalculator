package com.investment_calculator;

import java.sql.*;

/**
 * This class requests the name of that country where investor is going to invest money,
 * and retrieve the value of central bank's rate of such country from the database.
 */
public class RateOfCentralBankIndicator implements Indicator{
    @Override
    public double request() {
        CountryForInvestment countryForInvestment = new CountryForInvestment();
        String country = countryForInvestment.request();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/InvestmentCalculator", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CentralBanksRates WHERE Country= '"+country+"'");
            resultSet.next();
            return resultSet.getDouble(3);
        } catch (SQLException e) {
            try {
                throw new CountryException("Unfortunately, the chosen country is absent in our database yet. Please, try another country.");
            } catch (CountryException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
