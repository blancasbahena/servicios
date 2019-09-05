/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.stp.controller;

import java.util.List;
import mx.org.stp.entity.Perfil;
import mx.org.stp.entity.dao.PerfilDAO;
/**
 *
 * @author Luis Blancas
 */
public class PerfilController {

    public List<Perfil> getAll() 
    {
        PerfilDAO dao=new PerfilDAO();
        List<Perfil> lista=  dao.findAll();
        return lista;
    }
    public Perfil getOne(int id) 
    {
        PerfilDAO dao=new PerfilDAO();
        Perfil bean = dao.findById(id);
        return bean;
    }
}
