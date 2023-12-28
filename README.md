# Prueba técnica Java básico || Hack a Boss & Softtek

Primera prueba técnica del Bootcamp de Java con la implementación de un CRUD con JPA en Java.

## Contenido

- [Características](#características)
- [Requisitos](#requisitos)
- [Estructura](#estructura)
- [Uso](#uso)
- [Supuestos](#supuestos)

## Características

- Acceso a base de datos SQL mediante JPA.
- Operaciones CRUD para la gestión de empleados.
- Validación de datos, manejo de errores y excepciones.
- Interfaz intuitiva mediante comandos en la terminal.

[![interfaz.png](https://i.postimg.cc/3JBm8nNk/interfaz.png)](https://postimg.cc/4mYmwvzG)

## Requisitos

- Java Development Kit (JDK) 17
- Apache Maven
- Base de datos SQL (archivo incluído):
    * DB: "empleados"
    * Usuario: "root"
    * Contraseña: ""

## Estructura

- src/com.softtek.pruebatec1 - Archivo de arranque y controlador del terminal.
- src/com.softtek.pruebatec1.modelos - Clase Empleado.
- src/com.softtek.pruebatec1.persistencia - Controlador JPA de persistencia.
- src/com.softtek.pruebatec1.persistencia.excepciones - Excepciones persistencia.
- src/com.softtek.pruebatec1.servicios - Controladora de persistencia y métodos CRUD

## Uso

- Navegar por el terminal de forma intuitiva.
- Escribir "0" para cancelar la ejecución que estemos realizando.
- Errores controlados por terminal. En caso de hacer algo incorrecto, aparecerá un mensaje con ***.

## Supuestos
- Se supone que la fecha de inicio corresponde con la fecha de la creación del empleado, por lo tanto la fecha se crea automáticamente.
