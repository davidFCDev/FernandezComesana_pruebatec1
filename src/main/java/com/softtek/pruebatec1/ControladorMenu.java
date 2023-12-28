package com.softtek.pruebatec1;

import com.softtek.pruebatec1.servicios.ControladoraPersistencia;
import com.softtek.pruebatec1.servicios.ServiciosEmpleado;
import static com.softtek.pruebatec1.servicios.ServiciosEmpleado.limpiarConsola;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ControladorMenu {

    private static final Scanner scanner = new Scanner(System.in);

    // Método para iniciar la aplicación a través de la cual accedemos a todos los servicios
    public static void iniciarTerminal(ControladoraPersistencia controlPersis) {
        int opcion;
        boolean continuar = true;

        do {
            mostrarMenu();
            System.out.print("Selecciona una opción: ");
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1 -> {
                        limpiarConsola();
                        ServiciosEmpleado.mostrarEmpleados(controlPersis);
                        System.out.print("Presiona cualquier tecla para avanzar...");
                        Scanner scanner = new Scanner(System.in);
                        scanner.nextLine();
                        limpiarConsola();
                    }
                    case 2 -> {
                        limpiarConsola();
                        ServiciosEmpleado.crearEmpleado(controlPersis);
                    }
                    case 3 -> {
                        limpiarConsola();
                        ServiciosEmpleado.borrarEmpleado(controlPersis);
                    }
                    case 4 -> {
                        limpiarConsola();
                        ServiciosEmpleado.actualizarEmpleado(controlPersis);
                    }
                    case 5 -> {
                        limpiarConsola();
                        ServiciosEmpleado.buscarEmpleadosPorCargo(controlPersis);
                    }
                    case 0 -> {
                        System.out.println("Saliendo de la terminal, ¡nos vemos pronto!");
                        continuar = false;
                    }
                    default ->
                        System.out.println("*** Opción no válida, inténtalo de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("*** Error: Ingresa un número válido.");
                scanner.nextLine();
            }
        } while (continuar);
        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println("----------------------------------------------");
        System.out.println("               Terminal");
        System.out.println("----------------------------------------------");
        System.out.println("1. Mostrar lista de empleados");
        System.out.println("2. Crear un empleado");
        System.out.println("3. Borrar un empleado");
        System.out.println("4. Modificar un empleado");
        System.out.println("5. Buscar empleados por cargo");
        System.out.println("0. Salir");
        System.out.println("----------------------------------------------");
    }

}
