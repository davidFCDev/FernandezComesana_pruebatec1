package com.softtek.pruebatec1;

import com.softtek.pruebatec1.servicios.ModificarEmpleado;
import com.softtek.pruebatec1.servicios.BorrarEmpleado;
import com.softtek.pruebatec1.servicios.BuscarEmpleadosPorCargo;
import com.softtek.pruebatec1.servicios.ControladoraPersistencia;
import com.softtek.pruebatec1.servicios.CrearEmpleado;
import com.softtek.pruebatec1.servicios.MostrarEmpleados;
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
                    case 1 ->
                        MostrarEmpleados.mostrarEmpleados(controlPersis);
                    case 2 ->
                        CrearEmpleado.crearEmpleado(controlPersis);
                    case 3 ->
                        BorrarEmpleado.borrarEmpleado(controlPersis);
                    case 4 ->
                        ModificarEmpleado.actualizarEmpleado(controlPersis);
                    case 5 ->
                        BuscarEmpleadosPorCargo.buscarEmpleadosPorCargo(controlPersis);
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
