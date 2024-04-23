/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conversormonedaalura.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CurrencyExchangeClient implements CurrencyClient {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/b0f81e62862c9466f7d63944/latest/USD";
    private HttpClient httpClient;
    private Gson gson;
    private JsonObject exchangeRates;

    public CurrencyExchangeClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    @Override
public void fetchExchangeRates() throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL))
            .GET()
            .build();

    try {
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            this.exchangeRates = gson.fromJson(response.body(), JsonObject.class)
                    .getAsJsonObject("conversion_rates");
        } else {
            throw new IOException("Failed to fetch exchange rates. Status code: " + response.statusCode());
        }
    } catch (IOException e) {
        throw new IOException("Error occurred while fetching exchange rates: " + e.getMessage(), e);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restore interrupted status
        throw new InterruptedException("Fetch operation was interrupted: " + e.getMessage());
    }
}


    @Override
public double convertCurrency(double amount, String fromCurrency, String toCurrency) {
    if (exchangeRates == null) {
        throw new IllegalStateException("Exchange rates have not been fetched. Please call fetchExchangeRates() first.");
    }

    if (!exchangeRates.has(fromCurrency)) {
        throw new IllegalArgumentException("Invalid 'fromCurrency' provided: " + fromCurrency);
    }

    if (!exchangeRates.has(toCurrency)) {
        throw new IllegalArgumentException("Invalid 'toCurrency' provided: " + toCurrency);
    }

    double fromRate = exchangeRates.get(fromCurrency).getAsDouble();
    double toRate = exchangeRates.get(toCurrency).getAsDouble();

    return (amount / fromRate) * toRate;
}

}
