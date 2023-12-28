package com.softtek.pruebatec1.servicios;

import com.softtek.pruebatec1.modelos.Empleado;
import com.softtek.pruebatec1.persistencia.EmpleadoJpaController;
import com.softtek.pruebatec1.persistencia.excepciones.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ControladoraPersistencia {

    EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();

    public void crearEmpleado(Empleado empleado) {
        empleadoJPA.create(empleado);
    }

    public void borrarEmpleado(int id) {
        try {
            empleadoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Empleado> traerEmpleados() {
        return empleadoJPA.findEmpleadoEntities();
    }

    public void modificarEmpleado(Empleado empleado) {
        try {
            empleadoJPA.edit(empleado);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Búsqueda de empleados por cargo con query
    public List<Empleado> buscarEmpleadosPorCargo(String cargo) {
        EntityManager em = empleadoJPA.getEntityManager();
        try {
            Query query = em.createQuery("SELECT e FROM Empleado e WHERE e.cargo = :cargo");
            query.setParameter("cargo", cargo);
            return query.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            em.close();
        }
    }
    
    // Búsqueda de empleado por ID
    public Empleado buscarEmpleadoPorId(int id) {
        EntityManager em = empleadoJPA.getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            em.close();
        }
    }

}
