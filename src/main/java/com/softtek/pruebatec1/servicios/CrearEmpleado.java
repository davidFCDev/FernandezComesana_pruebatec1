package com.softtek.pruebatec1.servicios;

import com.softtek.pruebatec1.modelos.Empleado;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CrearEmpleado {

    private static final Scanner scanner = new Scanner(System.in);

    public static void crearEmpleado(ControladoraPersistencia controlPersis) {
        boolean crearOtroEmpleado;

        do {
            System.out.println("----------------------------------------------");
            System.out.println("Creación de empleado - Escribe 0 para salir");
            System.out.println("----------------------------------------------");

            String nombre = obtenerEntrada("Ingresa el nombre (solo letras): ");
            if (nombre.equals("0")) {
                return; 
            }

            String primerApellido = obtenerEntrada("Ingresa el primer apellido (solo letras): ");
            if (primerApellido.equals("0")) {
                return;
            }

            String segundoApellido = obtenerEntrada("Ingresa el segundo apellido (solo letras): ");
            if (segundoApellido.equals("0")) {
                return;
            }

            String cargo = obtenerEntrada("Ingresa el cargo (solo letras): ");
            if (cargo.equals("0")) {
                return;
            }

            double salario = obtenerSalario("Ingresa el salario: ");
            if (salario == 0.0) {
                return;
            }

            try {
                Empleado empleado = new Empleado(nombre, primerApellido, segundoApellido, cargo, salario, LocalDate.now());
                controlPersis.crearEmpleado(empleado);
                System.out.println("¡Empleado creado con éxito!");
            } catch (IllegalArgumentException e) {
                System.out.println("Error al crear el empleado: " + e.getMessage());
            }

            crearOtroEmpleado = preguntarCrearOtroEmpleado();

        } while (crearOtroEmpleado);
    }

    private static String obtenerEntrada(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();

            if (input.equals("0")) {
                return "0";
            }

            if (input.matches("[a-zA-Z ]+")) {
                return input;
            } else {
                System.out.println("*** Error: Introduce solo letras sin espacios ni símbolos");
                System.out.println("*** El campo es obligatorio");
            }
        }
    }

    private static double obtenerSalario(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String input = scanner.nextLine().trim();

                if (input.equals("0")) {
                    return 0.0;
                }

                double salario = Double.parseDouble(input);

                if (salario < 0) {
                    throw new IllegalArgumentException("El salario no puede ser negativo.");
                }

                return salario;
            } catch (NumberFormatException e) {
                System.out.println("*** Error: Por favor, ingresa un número válido para el salario.");
                System.out.println("*** El campo es obligatorio");
            } catch (IllegalArgumentException e) {
                System.out.println("Error al ingresar el salario: " + e.getMessage());
            }
        }
    }

    private static boolean preguntarCrearOtroEmpleado() {
        while (true) {
            try {
                System.out.print("¿Desea crear otro empleado? (Si/No): ");
                String respuesta = scanner.nextLine().trim().toLowerCase();

                if (respuesta.equals("0")) {
                    return false;
                }

                if ("si".equals(respuesta) || "s".equals(respuesta)) {
                    return true;
                } else if ("no".equals(respuesta) || "n".equals(respuesta)) {
                    return false;
                } else {
                    throw new InputMismatchException("Por favor, ingresa 'Si' o 'No'.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error al ingresar la respuesta: " + e.getMessage());
            }
        }
    }
}
