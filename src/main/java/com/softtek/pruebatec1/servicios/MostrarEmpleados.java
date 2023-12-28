package com.softtek.pruebatec1.servicios;

import com.softtek.pruebatec1.modelos.Empleado;
import java.util.List;

public class MostrarEmpleados {

    public static boolean mostrarEmpleados(ControladoraPersistencia controlPersis) {

        List<Empleado> listaEmpleados = controlPersis.traerEmpleados();

        if (listaEmpleados.isEmpty()) {
            System.out.println("----------------------------------------------");
            System.out.println("*** No hay empleados en la base de datos");
            System.out.println("*** Debes añadirlos primero");
            return false;
        } else {
            System.out.println("----------------------------------------------");
            System.out.println("Lista de empleados");
            System.out.println("----------------------------------------------");
            for (Empleado empleado : listaEmpleados) {
                System.out.println(empleado.toString());
            }
            System.out.println("----------------------------------------------");
            return true;
        }
    }
}
