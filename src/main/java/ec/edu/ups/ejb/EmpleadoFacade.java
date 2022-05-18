/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Empleado;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Jose
 */
@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado>{
    @PersistenceContext(name="my_persistence_unit")
    private EntityManager em;

    public EmpleadoFacade(){
        super(Empleado.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Empleado>getEmpleado() {
        Query query = em.createNamedQuery("getEmpleado");
        List<Empleado> res = query.getResultList();
        return res;
    }
    
    public void guardarEmpleado(Empleado empleado){
        if(empleado.getId() != 0) {
            em.merge(empleado);
        } else {
            em.persist(empleado);
        }
    }
    
    //Buscar al empleado por la ID
    public Empleado BuscoPorId(int id){
        return em.find(Empleado.class, id);
    }
    
    //Elimiar al empleado por ID
    public void eliminar(int id){
        Empleado empleado = BuscoPorId(id);
        em.remove(empleado);
    }
    
    //Para que no exista error al no existir empleados en la base
    public Optional<Empleado> opcional(int id){
        return Optional.ofNullable(BuscoPorId(id));
    }
    
}
