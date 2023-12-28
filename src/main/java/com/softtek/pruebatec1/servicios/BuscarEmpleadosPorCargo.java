package com.softtek.pruebatec1.servicios;

import com.softtek.pruebatec1.modelos.Empleado;
import java.util.List;
import java.util.Scanner;

public class BuscarEmpleadosPorCargo {

    private static final Scanner scanner = new Scanner(System.in);

    public static void buscarEmpleadosPorCargo(ControladoraPersistencia controlPersis) {

        System.out.println("Ingresa el cargo que deseas buscar (escribe 0 para salir): ");
        String cargo = scanner.nextLine();
        
        if ("0".equals(cargo)) {
            return;
        }
        
        if (cargo == null || cargo.trim().isEmpty()) {
            System.out.println("*** Cargo ingresado no válido.");
            return;
        }

        try {
            List<Empleado> empleados = controlPersis.buscarEmpleadosPorCargo(cargo);

            if (empleados != null && !empleados.isEmpty()) {
                for (Empleado empleado : empleados) {
                    System.out.println("----------------------------------------------");
                    System.out.println("Mostrando empleados por cargo: " + cargo);
                    System.out.println("----------------------------------------------");
                    System.out.println(empleado.toString());
                }
            } else {
                System.out.println("*** No se han encontrado empleados con ese cargo.");
            }
        } catch (Exception e) {
            System.out.println("*** Error al buscar empleados por cargo.");
            e.printStackTrace();
        }
    }
}
