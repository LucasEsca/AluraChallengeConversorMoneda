/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conversormonedaalura.model;

import com.mycompany.conversormonedaalura.model.ExchangeRates;
import java.util.Map;
import com.google.gson.Gson;


public class CurrencyConverter {
    private Map<String, Double> rates;

    public CurrencyConverter(String json) {
        Gson gson = new Gson();
        ExchangeRates exchangeRates = gson.fromJson(json, ExchangeRates.class);
        this.rates = exchangeRates.getRates();
    }

    public double convert(double amount, String fromCurrency, String toCurrency) {
        double fromRate = rates.get(fromCurrency);
        double toRate = rates.get(toCurrency);

        return (amount / fromRate) * toRate;
    }
    
}