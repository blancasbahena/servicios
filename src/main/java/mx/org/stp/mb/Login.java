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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext; 
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession; 
import mx.org.stp.controller.UsuarioController;
import mx.org.stp.entity.Usuario;
@ManagedBean (name = "login")
@RequestScoped
public class Login implements Serializable
{ 
    private final int Activo =1;
    private String usuariom="";
    private String password="";
    private Usuario user;
    private UsuarioController controller;
    
    @PostConstruct
    public void init() {
    	user=new Usuario();
        controller=new UsuarioController();
        usuariom=new String();
        password=new String();
        
    }

    public String LoginControl(String user01,String pass01)
    { 
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        
        
        
        if (facesContext == null) 
        {
            System.out.println
                    ("Se intentá acceder al FacesContext fuera del contexto "
                            + "de Java Server Faces, se regresará¡ una instancia nula.FacesContext");
            facesContext.addMessage(null, new FacesMessage("Error", 
            "Se intentá acceder al FacesContext fuera del contexto "
                            + "de Java Server Faces, se regresará¡ una instancia nula.: FacesContext"));
            facesContext.addMessage(null, new FacesMessage("Acceso denegado: FacesContext", user.getMsg()));
            return "";
        }
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setMaxInactiveInterval(5*60);
        if(session==null)
        {
            System.out.println
                    ("Se intentá acceder al FacesContext fuera del contexto "
                            + "de Java Server Faces, se regresará¡ una instancia nula.Session Nula(1)");
            facesContext.addMessage(null, new FacesMessage("Error", 
            "Se intentá acceder al FacesContext fuera del contexto "
                            + "de Java Server Faces, se regresará¡ una instancia nula.: FacesContext"));
            facesContext.addMessage(null, new FacesMessage("Acceso denegado: FacesContext", user.getMsg()));
            return "";   
        }
        try
        {
            Usuario userBuscadoLogin = controller.getOneByLogin(usuariom);
            if(userBuscadoLogin.getMsg().toUpperCase().equals("OK"))
            {
                if(userBuscadoLogin.getActivo()==Activo)
                {
                    Usuario userBuscadoLoginPss = controller.getOneByLoginAndPassword(usuariom,pass01);
                    if(userBuscadoLoginPss.getMsg().toUpperCase().equals("OK"))
                    {
                        facesContext.addMessage(null, new FacesMessage("Exito", "Logeo con exito del usuario "+usuariom));
                        facesContext.addMessage(null, new FacesMessage("Exito", "Usuario con exito "+usuariom));
                        this.user =userBuscadoLogin;
                        
                        this.user.setPagina("/content/admin/grafica/Lista.xhtml?faces-redirect=true");
                        session.setAttribute("usuario", this.user);  
                        return  this.user.getPagina();
                    }
                    else
                    {
                        facesContext.addMessage(null, new FacesMessage("Error", "Problemas con usuario (1000) "+usuariom));
                        facesContext.addMessage(null, new FacesMessage("Error", "Contacte a su administrador de usuarios(1000) "+usuariom));
                    }
                }
                else
                {
                    facesContext.addMessage(null, new FacesMessage("Error", "Problemas con usuario (2000) "+usuariom));
                    facesContext.addMessage(null, new FacesMessage("Error", "Contacte a su administrador de usuarios(2000) "+usuariom));
                }
            }
            else
            {
                facesContext.addMessage(null, new FacesMessage("Error", "Problemas con usuario (3000) "+usuariom));
                facesContext.addMessage(null, new FacesMessage("Error", "Contacte a su administrador de usuarios(3000) "+usuariom));

            }
        }
        catch(Exception ex)
        {
            facesContext.addMessage(null, new FacesMessage("Error", "Problemas con Base de Datos (4000) "+usuariom));
            facesContext.addMessage(null, new FacesMessage("Error", "Contacte a su administrador de usuarios(4000) "+usuariom));
            ex.printStackTrace();
        }
        return "";
    }
    public void logout()
    {
        try 
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("usuario", null);
            session.invalidate();
            facesContext.getExternalContext().invalidateSession();
            facesContext.addMessage(null, new FacesMessage("Logout", "Se cerro con exito la sesion"));
            facesContext.getExternalContext().redirect("../../../Login.xhtml?faces-redirect=true");
        }
        catch (IOException ex) 
        {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUsuariom() {
        return usuariom;
    }

    public void setUsuariom(String usuariom) {
        this.usuariom = usuariom;
    }
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
     
}