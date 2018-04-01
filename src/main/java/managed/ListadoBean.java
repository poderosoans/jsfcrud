/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

/**
 *
 * @author Java-LM
 */
@ManagedBean(name="listado")
@ApplicationScoped
public class ListadoBean {
    
    private List<SelectItem> generos;
    private List<SelectItem> profesiones;

    public List<SelectItem> getGeneros() {
        return generos;
    }

    public void setGeneros(List<SelectItem> generos) {
        this.generos = generos;
    }

    public List<SelectItem> getProfesiones() {
        return profesiones;
    }

    public void setProfesiones(List<SelectItem> profesiones) {
        this.profesiones = profesiones;
    }
    
    public ListadoBean() {
        generos = new ArrayList<SelectItem>();
        generos.add(new SelectItem("M","Masculino"));
        generos.add(new SelectItem("F","Femenino"));
        
        profesiones = new ArrayList<SelectItem>();
        profesiones.add(new SelectItem("001","Arquitecto"));
        profesiones.add(new SelectItem("002","Ingeniero")); 
    }
}
