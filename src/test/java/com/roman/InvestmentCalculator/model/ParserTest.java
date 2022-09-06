package com.roman.InvestmentCalculator.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void findBankRate() throws IOException {
        Parser parser = new Parser();
        String rate = parser.findBankRate();
        assertEquals("25.0",rate);
    }
}