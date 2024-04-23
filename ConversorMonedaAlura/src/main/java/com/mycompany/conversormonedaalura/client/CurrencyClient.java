/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.conversormonedaalura.client;

import java.io.IOException;

public interface CurrencyClient {
    void fetchExchangeRates() throws IOException, InterruptedException;
    double convertCurrency(double amount, String fromCurrency, String toCurrency);
}
