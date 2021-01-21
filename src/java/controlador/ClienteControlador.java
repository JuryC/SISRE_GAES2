/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Cliente;
import facade.ClienteFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Jury
 */
@Named(value = "clienteControlador")
@ManagedBean
@SessionScoped
public class ClienteControlador implements Serializable {

    /**
     * Creates a new instance of ClienteControlador
     */
    public ClienteControlador() {
        cliente = new Cliente();

    }
    @Inject
    MensajeControlador mensaje;
    private Cliente cliente;

    @EJB
    ClienteFacade clienteFacade;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void registrar() {
        cliente.setEstado(1);
        clienteFacade.create(cliente);
        mensaje.setMensaje("MensajeRedirect('ListarCliente.xhtml','Registro','El registro ha sido exitoso','success');");
        
        this.cliente = new Cliente();
    }
    

    public String preActualizar(Cliente clienteActualizar) {
        cliente = clienteActualizar;
        return "ActualizarCliente";
    }

    public void actualizar() {
        clienteFacade.edit(cliente);
         mensaje.setMensaje("MensajeRedirect('ListarCliente.xhtml','Actualizacion','La actualizacion se ha realizado con exito','success');");
    }

    public void eliminar(Cliente clienteEliminar) {
        cliente = clienteEliminar;
        cliente.setEstado(2);
        clienteFacade.edit(cliente);
    }

    public List<Cliente> consultarTodos() {
        return clienteFacade.consultarTodos(1);
    }
    public List<Cliente> obtenerCliente(int id){
        return clienteFacade.obtenerCliente(id);
    }
}
