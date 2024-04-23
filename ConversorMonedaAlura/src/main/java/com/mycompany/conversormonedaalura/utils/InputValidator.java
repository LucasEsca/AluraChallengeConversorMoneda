/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conversormonedaalura.utils;

import java.util.Scanner;

public class InputValidator {

    private static Scanner scanner = new Scanner(System.in);

    public static int getValidIntegerInput(String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            if (!scanner.hasNextInt()) {
                System.out.println("Error: Por favor, ingresa un número entero válido.");
                scanner.next(); // Consumir la entrada inválida
                continue;
            }
            int input = scanner.nextInt();
            if (input < min || input > max) {
                System.out.println("Error: Por favor, ingresa un número entre " + min + " y " + max + ".");
                continue;
            }
            return input;
        }
    }

    public static double getPositiveDoubleInput(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (!scanner.hasNextDouble()) {
                System.out.println("Error: Por favor, ingresa un número válido.");
                scanner.next(); // Consumir la entrada inválida
                continue;
            }
            double input = scanner.nextDouble();
            if (input <= 0) {
                System.out.println("Error: La cantidad debe ser un número positivo mayor que cero.");
                continue;
            }
            return input;
        }
    }

    public static void cerrarScanner() {
        scanner.close();
    }
}

