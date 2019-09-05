/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.stp.mb;
import java.io.IOException; 
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext; 
import javax.servlet.http.HttpSession; 
import mx.org.stp.entity.Usuario;
/**
 * @author luisa
 */
@ManagedBean (name = "sessionMB")
@SessionScoped
public class SessionMB  implements Serializable{
    private Usuario usuariofinal; 
    private Usuario usuariomovil; 
    @PostConstruct
    public void init() 
    { 
        usuariofinal=new Usuario();
        usuariomovil=new Usuario();
    }
    public Usuario getUsuariomovil() 
    {
        try
        {
            FacesContext facesContext = FacesContext.getCurrentInstance(); 
            if (facesContext == null) 
            {
                System.out.println
                        ("Se intentá acceder al FacesContext fuera del contexto "
                                + "de Java Server Faces, se regresará¡ una instancia nula.FacesContext");
                facesContext.addMessage(null, new FacesMessage("Error", 
                "Se intentá acceder al FacesContext fuera del contexto "
                                + "de Java Server Faces, se regresará¡ una instancia nula.: FacesContext"));
                facesContext.addMessage(null, new FacesMessage("Acceso denegado: FacesContext", usuariofinal.getMsg())); 
            }
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            if(session==null)
            {
                System.out.println
                        ("Se intentá acceder al FacesContext fuera del contexto "
                                + "de Java Server Faces, se regresará¡ una instancia nula.Session Nula(1)");
                facesContext.addMessage(null, new FacesMessage("Error", 
                "Se intentá acceder al FacesContext fuera del contexto "
                                + "de Java Server Faces, se regresará¡ una instancia nula.: FacesContext"));
                facesContext.addMessage(null, new FacesMessage("Acceso denegado: FacesContext", usuariomovil.getMsg())); 
            }
            this.usuariomovil = (Usuario) session.getAttribute("usuario");
        }
        catch(Exception ed)
        {
            try
            {
               FacesContext facesContext = FacesContext.getCurrentInstance(); 
               System.out.println("Se intentá acceder al session:"+ed.getMessage());
               facesContext.addMessage(null, new FacesMessage("Error","Error."+ed.getMessage()));
               facesContext.addMessage(null, new FacesMessage("Acceso denegado: FacesContext", usuariomovil.getMsg()));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
                
        }
        if(usuariomovil==null)
        {
            try { 
                FacesContext facesContext = FacesContext.getCurrentInstance(); 
                facesContext.getExternalContext().redirect("./LoginExp.xhtml?faces-redirect=true"); 
            } catch (IOException ex) {
                Logger.getLogger(SessionMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuariomovil; 
    }
    public Usuario getUsuariofinal() 
    {
        try
        {
            FacesContext facesContext = FacesContext.getCurrentInstance(); 
            if (facesContext == null) 
            {
                System.out.println
                        ("Se intentá acceder al FacesContext fuera del contexto "
                                + "de Java Server Faces, se regresará¡ una instancia nula.FacesContext");
                facesContext.addMessage(null, new FacesMessage("Error", 
                "Se intentá acceder al FacesContext fuera del contexto "
                                + "de Java Server Faces, se regresará¡ una instancia nula.: FacesContext"));
                facesContext.addMessage(null, new FacesMessage("Acceso denegado: FacesContext", usuariofinal.getMsg())); 
            }
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            if(session==null)
            {
                System.out.println
                        ("Se intentá acceder al FacesContext fuera del contexto "
                                + "de Java Server Faces, se regresará¡ una instancia nula.Session Nula(1)");
                facesContext.addMessage(null, new FacesMessage("Error", 
                "Se intentá acceder al FacesContext fuera del contexto "
                                + "de Java Server Faces, se regresará¡ una instancia nula.: FacesContext"));
                facesContext.addMessage(null, new FacesMessage("Acceso denegado: FacesContext", usuariofinal.getMsg())); 
            }
            this.usuariofinal = (Usuario) session.getAttribute("usuario");
        }
        catch(Exception ed)
        {
            try
            {
               FacesContext facesContext = FacesContext.getCurrentInstance(); 
               System.out.println("Se intentá acceder al session:"+ed.getMessage());
               facesContext.addMessage(null, new FacesMessage("Error","Error."+ed.getMessage()));
               facesContext.addMessage(null, new FacesMessage("Acceso denegado: FacesContext", usuariofinal.getMsg()));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
                
        }
        if(usuariofinal==null)
        {
            try { 
                FacesContext facesContext = FacesContext.getCurrentInstance(); 
                facesContext.getExternalContext().redirect("../../../LoginExp.xhtml?faces-redirect=true"); 
            } catch (IOException ex) {
                Logger.getLogger(SessionMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuariofinal; 
    }
    public void salir() 
    {
        try
        {
            FacesContext facesContext = FacesContext.getCurrentInstance(); 
            if (facesContext == null) 
            {
                System.out.println("Se intentá acceder al FacesContext fuera del contexto "
                                + "de Java Server Faces, se regresará¡ una instancia nula.FacesContext");
                facesContext.addMessage(null, new FacesMessage("Error", 
                "Se intentá acceder al FacesContext fuera del contexto "
                                + "de Java Server Faces, se regresará¡ una instancia nula.: FacesContext"));
                facesContext.addMessage(null, new FacesMessage("Acceso denegado: FacesContext", usuariofinal.getMsg())); 
            }
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            if(session==null)
            {
                System.out.println
                        ("Se intentá acceder al FacesContext fuera del contexto "
                                + "de Java Server Faces, se regresará¡ una instancia nula.Session Nula(1)");
                facesContext.addMessage(null, new FacesMessage("Error", 
                "Se intentá acceder al FacesContext fuera del contexto "
                                + "de Java Server Faces, se regresará¡ una instancia nula.: FacesContext"));
                facesContext.addMessage(null, new FacesMessage("Acceso denegado: FacesContext", usuariofinal.getMsg())); 
            }
            session.setAttribute("usuario",null);
            facesContext.getExternalContext().redirect("../../../LoginExp.xhtml?faces-redirect=true"); 
        }
        catch(Exception ed)
        {
           ed.printStackTrace();
        }
    }
    public void salir_mobile() 
    {
        try
        {
            FacesContext facesContext = FacesContext.getCurrentInstance(); 
            if (facesContext == null) 
            {
                System.out.println
                        ("Se intentá acceder al FacesContext fuera del contexto "
                                + "de Java Server Faces, se regresará¡ una instancia nula.FacesContext");
                facesContext.addMessage(null, new FacesMessage("Error", 
                "Se intentá acceder al FacesContext fuera del contexto "
                                + "de Java Server Faces, se regresará¡ una instancia nula.: FacesContext"));
                facesContext.addMessage(null, new FacesMessage("Acceso denegado: FacesContext", usuariofinal.getMsg())); 
            }
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            if(session==null)
            {
                System.out.println
                        ("Se intentá acceder al FacesContext fuera del contexto "
                                + "de Java Server Faces, se regresará¡ una instancia nula.Session Nula(1)");
                facesContext.addMessage(null, new FacesMessage("Error", 
                "Se intentá acceder al FacesContext fuera del contexto "
                                + "de Java Server Faces, se regresará¡ una instancia nula.: FacesContext"));
                facesContext.addMessage(null, new FacesMessage("Acceso denegado: FacesContext", usuariofinal.getMsg())); 
            }
            session.setAttribute("usuario",null);
            facesContext.getExternalContext().redirect("./index.xhtml?faces-redirect=true"); 
        }
        catch(Exception ed)
        {
           ed.printStackTrace();
        }
    }
    public void setUsuariofinal(Usuario usuariofinal) {
        this.usuariofinal = usuariofinal;
    }

}
