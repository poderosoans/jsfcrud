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

/**
 *
 * @author portatil
 */
@ManagedBean(name="clienteBean")
public class ClienteBean {
    
    private Cliente cliente=new Cliente();
    
    private String mensaje;
    private DaoCliente dao = new PersistenciaBDDaoCliente();
    
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
    
    public String registrar(){
       if(cliente.getCodigo() != 0) {
            dao.modificarCliente(cliente);
            return "resultado"; 
        }
       dao.insertarCliente(cliente); 
       return "resultado";
      
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
