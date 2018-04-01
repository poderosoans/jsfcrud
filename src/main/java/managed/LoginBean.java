/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import bean.Usuario;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.el.ResourceBundleELResolver;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean {

    private Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String autenticar() {
        try {
            //invocar registro a servicio de negocio
            ResourceBundle rb = ResourceBundle.getBundle("recursos.mensajes",
            FacesContext.getCurrentInstance().getViewRoot().getLocale());

            if ("pepe".equals(usuario.getUsername()) && "123".equals(usuario.getPassword())) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario.getUsername());
                //FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
                return "principal";
            }

            //error en autentificación
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                rb.getString("validacion_login_incorrecto"),
                rb.getString("validacion_login_incorrecto_detail"));
            FacesContext.getCurrentInstance().addMessage(null, fm);

        } catch (Exception e) {
        }
        return "login";
    }

    public void logout() throws IOException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        String patch = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(patch+"/faces/login.xhtml");

    }
}
