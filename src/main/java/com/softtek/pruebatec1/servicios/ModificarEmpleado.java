package com.softtek.pruebatec1.servicios;

import com.softtek.pruebatec1.modelos.Empleado;
import java.util.Scanner;

public class ModificarEmpleado {

    private static final Scanner scanner = new Scanner(System.in);

    public static void actualizarEmpleado(ControladoraPersistencia controlPersis) {
        boolean continuar = true;

        while (continuar) {
            //Comprobamos si hay usuarios en la base de datos
            if (!MostrarEmpleados.mostrarEmpleados(controlPersis)) {
                break;
            }

            System.out.println("Ingresa el ID del empleado que quieres actualizar (escribe 0 para salir): ");
            int idEmpleado = scanner.nextInt();
            scanner.nextLine();

            if (idEmpleado == 0) {
                return;
            }

            Empleado empleado = controlPersis.buscarEmpleadoPorId(idEmpleado);

            if (empleado != null) {
                String nuevoNombre = obtenerEntrada("Ingresa el nombre: ");
                if (nuevoNombre.equals("0")) {
                    return;
                }

                String nuevoPrimerApellido = obtenerEntrada("Ingresa el primer apellido: ");
                if (nuevoPrimerApellido.equals("0")) {
                    return;
                }

                String nuevoSegundoApellido = obtenerEntrada("Ingresa el segundo apellido: ");
                if (nuevoSegundoApellido.equals("0")) {
                    return;
                }

                String nuevoCargo = obtenerEntrada("Ingresa el cargo: ");
                if (nuevoCargo.equals("0")) {
                    return;
                }

                double nuevoSalario = obtenerSalario("Ingresa el salario: ");
                if (nuevoSalario == 0.0) {
                    return;
                }

                empleado.setNombre(nuevoNombre);
                empleado.setPrimerApellido(nuevoPrimerApellido);
                empleado.setSegundoApellido(nuevoSegundoApellido);
                empleado.setCargo(nuevoCargo);
                empleado.setSalario(nuevoSalario);

                controlPersis.modificarEmpleado(empleado);
                System.out.println("Empleado actualizado exitosamente.");
                System.out.println(empleado.toString());
            } else {
                System.out.println("*** No se encontró un empleado con el ID proporcionado.");
            }

            System.out.print("¿Quieres actualizar otro empleado? (Si/No): ");
            String respuesta = scanner.nextLine().toLowerCase();

            if (!respuesta.equals("si")) {
                continuar = false;
            }
        }
    }

    // Método para la validación de entrada del String
    private static String obtenerEntrada(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();

            if (input.equals("0")) {
                return "0";
            }

            if (input.matches("[a-zA-Z]+")) {
                return input;
            } else {
                System.out.println("*** Error: Introduce solo letras sin espacios ni símbolos.");
            }
        }
    }

    // Método para la validación de entrada del Double
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
}
