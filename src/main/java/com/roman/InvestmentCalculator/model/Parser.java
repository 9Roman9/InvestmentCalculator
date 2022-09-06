package com.roman.InvestmentCalculator.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("parser")
public class Parser {
    public String findBankRate() throws IOException {
        Document document = Jsoup.connect("https://bank.gov.ua").get();
        String[] values = document.body().getElementsByClass("value").text().split(" ");
        String bigRate = values[2].replace(",","");
        values = document.body().getElementsByTag("small").text().split(" ");
        String smallRate = values[3];
        return bigRate.concat(".").concat(smallRate);
    }
}
