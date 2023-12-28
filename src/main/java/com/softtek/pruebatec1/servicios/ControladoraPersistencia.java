package com.softtek.pruebatec1.servicios;

import com.softtek.pruebatec1.modelos.Empleado;
import com.softtek.pruebatec1.persistencia.EmpleadoJpaController;
import com.softtek.pruebatec1.persistencia.excepciones.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

    // Método para buscar empleados por cargo mediante query
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

    // Método para buscar en la base de datos un empleado por ID
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

    // Método para la comprobación en la base de datos de si ya existe un empleado con ese DNI
    public boolean existeEmpleadoPorDni(String dni) {
        EntityManager em = empleadoJPA.getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(e) FROM Empleado e WHERE e.dni = :dni", Long.class
            );
            query.setParameter("dni", Integer.parseInt(dni.substring(0, 8)));
            Long count = query.getSingleResult();
            return count > 0;
        } catch (NumberFormatException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
            return false; 
        } finally {
            em.close();
        }
    }

}
