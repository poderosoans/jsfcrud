/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import bean.Usuario;
import java.util.ResourceBundle;
import javax.el.ResourceBundleELResolver;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="login")
@SessionScoped
public class LoginBean {
    
    private Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

 
    
    public String autenticar(){
        
         //invocar registro a servicio de negocio
        //boolean resultado=true;
        
        ResourceBundle rb=
                ResourceBundle.getBundle("recursos.mensajes",
                 FacesContext.getCurrentInstance().getViewRoot().getLocale());
        
        //si servicio responde ok
        if("pepe".equals(usuario.getUsername()) && "123".equals(usuario.getPassword())){
            return "principal";
            
        }else{
            //error en autentificación
            FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_ERROR,
                   rb.getString("validacion_login_incorrecto"),
                   rb.getString("validacion_login_incorrecto_detail"));
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "login";
        }
        
    }
}
