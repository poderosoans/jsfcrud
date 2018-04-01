/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Java-LM
 */
@FacesValidator(value="fechaLimite")
public class FechaLimiteValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Date fecha = (Date) value;
        Calendar c1 = Calendar.getInstance();
        c1.setTime(fecha);
        
        if(c1.after(getFechaActual(Calendar.getInstance()))){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Fecha incorrecta", "Fecha ingresada no puede ser mayor a la fecha actual");
            throw new ValidatorException(fm);
        }
    }

    private Calendar getFechaActual(Calendar c) {
        c.set(Calendar.AM_PM, Calendar.AM);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }
}
