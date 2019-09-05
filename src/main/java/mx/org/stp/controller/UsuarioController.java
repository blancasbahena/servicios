/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.stp.controller;

import java.util.ArrayList;
import java.util.List;
import mx.org.stp.entity.Usuario; 
import mx.org.stp.entity.dao.UsuarioDAO;

/**
 *
 * @author Luis Blancas
 */
public class UsuarioController {

    public List<Usuario> getAll() 
    {
        UsuarioDAO dao=new UsuarioDAO();
        List<Usuario> lista=  dao.findAll();
        return lista;
    }
    public Usuario getOne(int id) 
    {
        UsuarioDAO dao=new UsuarioDAO();
        Usuario bean = dao.findById(id);
        return bean;
    }
    public Usuario getOneByLogin(String login) 
    {
        UsuarioDAO dao=new UsuarioDAO();
        Usuario bean = dao.findByLogin(login);
        if(bean!=null)
        {
            PerfilController controllerP=new PerfilController();
            bean.setActivostr("Activo");
            bean.setPerfilstr((controllerP.getOne(bean.getPerfil())).getDescripcion());
            bean.setMsg("Ok");
            return bean;
        }
        bean =new  Usuario();
        bean.setMsg("Error.Elemento no encontrado");
        return bean;
    }
    public Usuario getOneByLoginAndPassword(String login,String password) 
    {
        UsuarioDAO dao=new UsuarioDAO();
        Usuario bean = dao.findByLoginAndPassword(login, password);
        if(bean!=null)
        {
            PerfilController controllerP=new PerfilController();
            bean.setActivostr("Activo");
            bean.setPerfilstr((controllerP.getOne(bean.getPerfil())).getDescripcion());
            bean.setMsg("Ok");
            return bean;
        }
        bean =new  Usuario();
        bean.setMsg("Error.Elemento no encontrado");
        return bean;
    }
    public Usuario editar(Usuario bean,int usuario) 
    {
        UsuarioDAO dao=new UsuarioDAO();
        Usuario  modifica =dao.update(bean.getLogin(), bean.getNombre(), bean.getPaterno(), bean.getMaterno(),
                bean.getPerfil(), bean.getActivo(), usuario, bean.getId());
        modifica.setMsg("Modificado con exito ["+bean.getId()+"]");
        return modifica;
    }

    public Usuario borrar(Usuario bean,int usuario) {
        UsuarioDAO dao=new UsuarioDAO();
        int  borra =dao.delete(bean.getId());
        bean.setMsg("Modificado con exito ["+bean.getId()+"]");
        return bean;
    }
 

    public Usuario crea(String login,String password,String nombre, 
            String paterno,String materno,int perfil,int estatus,int usuario) 
    {
        UsuarioDAO dao=new UsuarioDAO();
        Usuario  creado =dao.insert(login,password,nombre, 
            paterno,materno,perfil,estatus,usuario);
        creado.setMsg("Creado con exito ["+creado.getId()+"]");
        return creado;
    }

    
}
