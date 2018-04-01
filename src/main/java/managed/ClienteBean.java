/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import bean.Cliente;
import dao.DaoCliente;
import dao.implement.PersistenciaBDDaoCliente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author portatil
 */
@ManagedBean(name="clienteBean")
@SessionScoped
public class ClienteBean {
    
    private Cliente cliente;
    private String mensaje;
    private List<Cliente> clientList;
    private DaoCliente dao;
    
    public ClienteBean() {
        cliente= new Cliente();
        clientList = new ArrayList<Cliente>();
        dao = new PersistenciaBDDaoCliente();
    }

    public List<Cliente> getClientList() {
        return clientList;
    }

    public void setClientList(List<Cliente> clientList) {
        this.clientList = clientList;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public void nuevo() throws IOException {
        cliente = new Cliente();
        String patch = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(patch+"/faces/cliente/cliente.xhtml");
    }
    public String registrar() throws Exception {
        if(cliente.getCodigo() != 0) {
            dao.modificarCliente(cliente);
            listar();
            return "lista_clientes?faces-redirect=true"; 
        }
        dao.insertarCliente(cliente);
        listar();
        return "lista_clientes?faces-redirect=true";  
    }
    
    public String editar(int studentId) {
	cliente = dao.obtenerCliente(studentId);
        return "cliente?faces-redirect=true";
    }
    
    public String eliminar(int studentId) throws Exception {
	dao.eliminarCliente(studentId);
        listar();
        return "lista_clientes?faces-redirect=true";
    }
    
    public void listar() throws Exception {
        clientList = dao.listarCliente();
        
        String patch = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(patch+"/faces/cliente/lista_clientes.xhtml");
    }
    
     public void mensajeProfesion(){
         ResourceBundle rb=
                 ResourceBundle.getBundle("recursos.mensajes", FacesContext.getCurrentInstance().getViewRoot().getLocale());
         
        String valor= getCliente().getProfesion();
        if(valor.equals("001")){
            FacesContext.getCurrentInstance().addMessage("nuevoCliente:profesion",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    rb.getString("mensaje_profesion_arquitectura"), null));
        }
        if(valor.equals("002")){
            FacesContext.getCurrentInstance().addMessage("nuevoCliente:profesion",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    rb.getString("mensaje_profesion_ingenieria"), null));
        }

    }
      
    
   
    
}
