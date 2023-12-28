package com.softtek.pruebatec1.servicios;

import com.softtek.pruebatec1.modelos.Empleado;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ServiciosEmpleado {

    private static final Scanner scanner = new Scanner(System.in);

    // ************************************************************************** \\
    //                       Métodos principales del CRUD                        \\
    // *************************************************************************** \\
    public static boolean mostrarEmpleados(ControladoraPersistencia controlPersis) {

        try {
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
        } catch (InputMismatchException e) {
            System.out.println("Error de entrada: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
            return false;
        }
    }

    public static void crearEmpleado(ControladoraPersistencia controlPersis) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("----------------------------------------------");
            System.out.println("Creación de empleado - Escribe 0 para salir");
            System.out.println("----------------------------------------------");

            String nombre = obtenerEntrada("Ingresa el nombre (solo letras): ");
            if (nombre.equals("0")) {
                limpiarConsola();
                return;
            }

            String primerApellido = obtenerEntrada("Ingresa el primer apellido (solo letras): ");
            if (primerApellido.equals("0")) {
                limpiarConsola();
                return;
            }

            String segundoApellido = obtenerEntrada("Ingresa el segundo apellido (solo letras): ");
            if (segundoApellido.equals("0")) {
                limpiarConsola();
                return;
            }

            String cargo = obtenerEntrada("Ingresa el cargo (solo letras): ");
            if (cargo.equals("0")) {
                limpiarConsola();
                return;
            }

            int dni = obtenerDni("Ingresa el dni: ", controlPersis);
            if (dni == 0) {
                limpiarConsola();
                return;
            }

            double salario = obtenerSalario("Ingresa el salario: ");
            if (salario == 0.0) {
                limpiarConsola();
                return;
            }

            try {

                Empleado empleado = new Empleado(nombre, primerApellido, segundoApellido, dni, cargo, salario, LocalDate.now());
                controlPersis.crearEmpleado(empleado);
                limpiarConsola();
                System.out.println("¡Empleado creado con éxito!");

            } catch (IllegalArgumentException e) {

                System.out.println("Error al crear el empleado: " + e.getMessage());
                e.printStackTrace();

            }

            System.out.print("¿Quieres crear otro empleado? (Si/No): ");
            continuar = hacerPregunta();
            scanner.nextLine();

        }
    }

    public static void actualizarEmpleado(ControladoraPersistencia controlPersis) {
        boolean continuar = true;

        while (continuar) {
            //Comprobamos si hay usuarios en la base de datos
            if (!ServiciosEmpleado.mostrarEmpleados(controlPersis)) {
                break;
            }

            System.out.println("Ingresa el ID del empleado que quieres actualizar (escribe 0 para salir): ");
            int idEmpleado = scanner.nextInt();
            scanner.nextLine();

            if (idEmpleado == 0) {
                limpiarConsola();
                return;
            }

            Empleado empleado = controlPersis.buscarEmpleadoPorId(idEmpleado);

            if (empleado != null) {
                String nuevoNombre = obtenerEntrada("Ingresa el nombre: ");
                if (nuevoNombre.equals("0")) {
                    limpiarConsola();
                    return;
                }

                String nuevoPrimerApellido = obtenerEntrada("Ingresa el primer apellido: ");
                if (nuevoPrimerApellido.equals("0")) {
                    limpiarConsola();
                    return;
                }

                String nuevoSegundoApellido = obtenerEntrada("Ingresa el segundo apellido: ");
                if (nuevoSegundoApellido.equals("0")) {
                    limpiarConsola();
                    return;
                }

                int nuevoDni = obtenerDni("Ingresa el dni: ", controlPersis);
                if (nuevoDni == 0) {
                    limpiarConsola();
                    return;
                }

                String nuevoCargo = obtenerEntrada("Ingresa el cargo: ");
                if (nuevoCargo.equals("0")) {
                    limpiarConsola();
                    return;
                }

                double nuevoSalario = obtenerSalario("Ingresa el salario: ");
                if (nuevoSalario == 0.0) {
                    limpiarConsola();
                    return;
                }

                empleado.setNombre(nuevoNombre);
                empleado.setPrimerApellido(nuevoPrimerApellido);
                empleado.setSegundoApellido(nuevoSegundoApellido);
                empleado.setCargo(nuevoCargo);
                empleado.setSalario(nuevoSalario);

                controlPersis.modificarEmpleado(empleado);
                System.out.println("¡Empleado actualizado exitosamente!");
                System.out.println(empleado.toString());
            } else {
                System.out.println("*** No se encontró un empleado con el ID proporcionado.");
            }

            System.out.print("¿Quieres actualizar otro empleado? (Si/No): ");
            continuar = hacerPregunta();
        }
    }

    public static void borrarEmpleado(ControladoraPersistencia controlPersis) {
        boolean eliminarOtro = true;

        while (eliminarOtro) {

            System.out.println("----------------------------------------------");
            System.out.println("Eliminación de empleado");

            // Comprobamos si hay usuarios en la base de datos
            // De lo contrario rompemos la ejecución
            if (!ServiciosEmpleado.mostrarEmpleados(controlPersis)) {
                break;
            }

            int idEmpleado = obtenerIdEmpleado();

            if (idEmpleado == 0) {
                limpiarConsola();
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

            System.out.println("¿Quieres eliminar otro empleado? (Si/No): ");
            eliminarOtro = hacerPregunta();
        }
    }

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
                    System.out.println("----------------------------------------------");

                    System.out.print("Presiona cualquier tecla para avanzar...");

                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();

                    limpiarConsola();
                }
            } else {
                System.out.println("*** No se han encontrado empleados con ese cargo.");
            }
        } catch (Exception e) {
            System.out.println("*** Error al buscar empleados por cargo.");
            e.printStackTrace();
        }
    }

    // *************************************************************************** \\
    //                        Otros métodos complementarios                     \\
    // *************************************************************************** \\
    // Búsqueda de empleado por ID para luego borrarlo
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

    // Obtención de entradas de texto por scanner y validación
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

    // Obtención de entrada del salario por scanner y validación
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
                System.out.println("*** Error al ingresar el salario: " + e.getMessage());
            }
        }
    }

    // Obtención de entrada del dni por scanner y validación
    public static int obtenerDni(String mensaje, ControladoraPersistencia controlPersis) {
        while (true) {
            try {
                System.out.print(mensaje);
                String dniStr = scanner.nextLine();

                if (dniStr.equals("0")) {
                    return 0;
                }

                // Validación del DNI
                if (!dniStr.matches("\\d{8}[a-zA-Z]")) {
                    System.out.println("*** Formato de DNI incorrecto. Debe tener 8 números seguidos de una letra.");
                    continue;
                }

                // Comprobar en la base de datos si ya existe el DNI
                if (controlPersis.existeEmpleadoPorDni(dniStr)) {
                    System.out.println("*** Ya existe un empleado con ese DNI. Ingresa un DNI diferente.");
                    continue;
                }

                return Integer.parseInt(dniStr.substring(0, 8));
            } catch (NumberFormatException e) {
                System.out.println("*** Error al leer el DNI. Ingresa solo números y una letra al final.");
            }
        }
    }

    // Pregunta para seguir en los bucles o salir de ellos
    private static boolean hacerPregunta() {
        while (true) {
            String respuesta = scanner.next().trim().toLowerCase();
            if (respuesta.equals("si")) {
                limpiarConsola();
                return true;
            } else if (respuesta.equals("no")) {
                limpiarConsola();
                return false;
            } else {
                System.out.println("Respuesta no válida. Por favor, ingresa 'Si' o 'No'.");
            }
        }
    }

    // Método para limpiar la pantalla de la consola
    public static void limpiarConsola() {
        try {
            Robot limpiar = new Robot();
            limpiar.keyPress(KeyEvent.VK_CONTROL);
            limpiar.keyPress(KeyEvent.VK_L);
            limpiar.keyRelease(KeyEvent.VK_CONTROL);
            limpiar.keyRelease(KeyEvent.VK_L);
            limpiar.delay(100);

        } catch (AWTException e) {
            System.out.println("Error al limpiar la pantalla" + e.getMessage());
        }
    }
}
