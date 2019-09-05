/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.stp.mb; 
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct; 
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import mx.org.stp.controller.PerfilController;
import mx.org.stp.controller.UsuarioController;
import mx.org.stp.entity.Perfil;
import mx.org.stp.entity.Usuario;

/**
 *
 * @author luisa
 */ 
@ManagedBean(name = "userMB") 
@SessionScoped
public class UsuarioMB  implements Serializable 
{
    private final FacesContext context = FacesContext.getCurrentInstance();
    private List<Usuario> todos; 
    private Usuario bean;
    private UsuarioController controller;
    private int     id;
    private String  nombre;
    private String  paterno;
    private String  materno;
    private String  password1;
    private String  password2;
    private int     perfil;
    private String  login;
    private boolean activo;
    private List<Perfil> perfiles; 
    @PostConstruct
    public void init() {
        bean=new Usuario();
        controller=new UsuarioController();
    }
    public void  setPerfiles(List<Perfil> listae)
    {
        this.perfiles =listae;
    }
    public List<Perfil> getPerfiles()
    {
        PerfilController localC = new PerfilController();
        this.perfiles = localC.getAll();
        return this.perfiles;
    }
    public List<Usuario> getTodos()
    {    
        this.todos= controller.getAll();
        return this.todos;
    } 
    public void seleccionEditar(Usuario editar )
    {
        this.bean=new Usuario();
        this.bean.setId(editar.getId());
        this.bean.setNombre(editar.getNombre());
        this.bean.setApellidos(editar.getApellidos());
        this.bean.setPaterno(editar.getPaterno());
        this.bean.setMaterno(editar.getMaterno());
        this.bean.setLogin(editar.getLogin());
        this.bean.setPerfil(editar.getPerfil()); 
        this.bean.setActivo(editar.getActivo());
        System.out.println("seleccion Editar "+editar.toString());
    } 
    public void editarUsuario(int id,String nombre,
                        String paterno,String materno,
                        String login,int perfil,boolean estatus)
    { 
        this.bean=new Usuario();
        this.bean.setId(id);
        this.bean.setNombre(nombre);
        this.bean.setApellidos(paterno + " " + materno);
        this.bean.setPaterno(paterno);
        this.bean.setMaterno(materno);
        this.bean.setLogin(login);
        this.bean.setPerfil(perfil); 
        this.bean.setActivo(estatus?1:0);
        controller.editar(this.bean, 1); 
    }
    
    public void  Anexar(
                String nombre,
                String paterno,
                String materno,
                String login,
                String password1,
                String password2,
                int perfil)
    {
        //try 
        {
            int id=1;
            System.out.println("nombre::::"+nombre);
            System.out.println("paterno:::"+paterno);
            System.out.println("materno:::"+materno);
            System.out.println("login:::::"+login);
            System.out.println("password1:"+password1);
            System.out.println("password2:"+password2);
            System.out.println("perfil::::"+perfil);
            Usuario user = controller.crea(this.login,this.password1,this.nombre,this.paterno,this.materno,this.perfil,1,id);
            FacesContext context = FacesContext.getCurrentInstance();
            //context.getExternalContext().redirect("Lista.xhtml?faces-redirect=true");
            context.addMessage(null, new FacesMessage("Insertar", user.getMsg()));
        } 
        //catch (IOException ex) {
        //    Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        //}

    }
    
    public void borrarUsuario(Usuario editar,int id)
    {
        try 
        {
            controller.borrar(editar, id);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("Lista.xhtml?faces-redirect=true");
            context.addMessage(null, new FacesMessage("Borrar", "Registro borrado con exito :  "+id));
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Usuario getBean() {
        return bean;
    }

    public void setBean(Usuario bean) {
        this.bean = bean;
    }

    public UsuarioController getController() {
        return controller;
    }

    public void setController(UsuarioController controller) {
        this.controller = controller;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
