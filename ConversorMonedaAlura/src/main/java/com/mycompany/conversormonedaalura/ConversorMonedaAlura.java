/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.conversormonedaalura;

import com.mycompany.conversormonedaalura.client.CurrencyClient;
import com.mycompany.conversormonedaalura.client.CurrencyExchangeClient;
import com.mycompany.conversormonedaalura.utils.InputValidator;
import java.io.IOException;
import java.util.Scanner;

public class ConversorMonedaAlura {

    private static final String[] CODIGOS_MONEDAS = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};
    private static final String[] NOMBRES_MONEDAS = {"Peso argentino", "Boliviano boliviano", "Real brasileño",
                                                      "Peso chileno", "Peso colombiano", "Dólar estadounidense"};

    public static void main(String[] args) {
        CurrencyClient client = new CurrencyExchangeClient(); // Utilizando la interfaz
        Scanner scanner = new Scanner(System.in);

        try {
            client.fetchExchangeRates(); // Obtener tasas de cambio al inicio

            boolean salir = false;
            while (!salir) {
                System.out.println("\n--- Menú de Conversión de Moneda ---");
                listarOpcionesMonedas(); // Llama al método estático listarOpcionesMonedas

                int opcion = InputValidator.getValidIntegerInput("Ingrese la opción deseada (" + 1 + "-" + (CODIGOS_MONEDAS.length + 1) + "): ", 1, CODIGOS_MONEDAS.length + 1);
                if (opcion == CODIGOS_MONEDAS.length + 1) {
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    continue;
                }

                String monedaOrigen = CODIGOS_MONEDAS[opcion - 1];
                double cantidadOrigen = InputValidator.getPositiveDoubleInput("Ingrese el valor en " + monedaOrigen + " a convertir: ");

                System.out.println("Seleccione la moneda de destino:");
                listarOpcionesMonedas(); // Llama al método estático listarOpcionesMonedas

                opcion = InputValidator.getValidIntegerInput("Ingrese la opción deseada (" + 1 + "-" + (CODIGOS_MONEDAS.length + 1) + "): ", 1, CODIGOS_MONEDAS.length + 1);
                if (opcion == CODIGOS_MONEDAS.length + 1) {
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    continue;
                }

                String monedaDestino = CODIGOS_MONEDAS[opcion - 1];

                double cantidadConvertida = client.convertCurrency(cantidadOrigen, monedaOrigen, monedaDestino);
                System.out.println(cantidadOrigen + " " + monedaOrigen + " equivale a " + cantidadConvertida + " " + monedaDestino);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error al obtener las tasas de cambio: " + e.getMessage());
            e.printStackTrace(); // Mostrar el rastro de la excepción para diagnóstico
        } finally {
            InputValidator.cerrarScanner();
        }
    }

    // Método estático para listar las opciones de moneda
    private static void listarOpcionesMonedas() {
        System.out.println("Seleccione una moneda para convertir (o 'Salir' para terminar programa):");
        for (int i = 0; i < CODIGOS_MONEDAS.length; i++) {
            System.out.println((i + 1) + "-" + CODIGOS_MONEDAS[i] + " - " + NOMBRES_MONEDAS[i]);
        }
        System.out.println((CODIGOS_MONEDAS.length + 1) + "-Salir - Terminar programa");
    }
}
