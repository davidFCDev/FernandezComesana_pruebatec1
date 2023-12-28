package com.softtek.pruebatec1;

import com.softtek.pruebatec1.servicios.ControladoraPersistencia;

public class PruebaTec1 {

    public static void main(String[] args) {

        ControladoraPersistencia controlPersis = new ControladoraPersistencia();

        ControladorMenu.iniciarTerminal(controlPersis);

    }
}