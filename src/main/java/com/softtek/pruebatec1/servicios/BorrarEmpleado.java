package com.softtek.pruebatec1.servicios;

import com.softtek.pruebatec1.modelos.Empleado;
import java.util.Scanner;

public class BorrarEmpleado {

    private static final Scanner scanner = new Scanner(System.in);

    public static void borrarEmpleado(ControladoraPersistencia controlPersis) {
        boolean eliminarOtro = true;

        while (eliminarOtro) {
           
            System.out.println("----------------------------------------------");
            System.out.println("Eliminación de empleado");
            
             // Comprobamos si hay usuarios en la base de datos
             // De lo contrario rompemos la ejecución
            if (!MostrarEmpleados.mostrarEmpleados(controlPersis)) {
                break;
            }

            int idEmpleado = obtenerIdEmpleado();
            
            if (idEmpleado == 0) {
                break;
            }

            try {
                Empleado empleado = controlPersis.buscarEmpleadoPorId(idEmpleado);

                if (empleado != null) {
                    controlPersis.borrarEmpleado(idEmpleado);
                    System.out.println("¡Empleado eliminado con éxito!");
                } else {
                    System.out.println("*** No se encontró ningún empleado con ese ID.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("*** Error al intentar borrar el empleado: " + ex.getMessage());
            }
            eliminarOtro = preguntarSiEliminarOtro();
        }
    }

    // Hacemos la búsqueda por ID para luego poder borrarlo
    private static int obtenerIdEmpleado() {
        int idEmpleado;
        do {
            System.out.println("Ingresa el ID del empleado que quieres borrar (escribe 0 para salir): ");
            while (!scanner.hasNextInt()) {
                System.out.println("*** Por favor, ingresa un número válido para el ID del empleado.");
                scanner.next();
            }
            idEmpleado = scanner.nextInt();
        } while (idEmpleado < 0);
        return idEmpleado;
    }

    private static boolean preguntarSiEliminarOtro() {
        System.out.println("¿Quieres eliminar otro empleado? (Si/No): ");
        String respuesta = scanner.next().trim().toLowerCase();
        return respuesta.equals("si");
    }
}
