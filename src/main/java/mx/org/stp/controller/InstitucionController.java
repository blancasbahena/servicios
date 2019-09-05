/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.stp.controller;

import java.util.ArrayList;
import java.util.List;
import mx.org.stp.entity.InstitucionBean;
import mx.org.stp.entity.dao.InstitucionDAO; 
/**
 *
 * @author Luis Blancas
 */
public class InstitucionController {
    private InstitucionDAO dao = new InstitucionDAO();
    public List<InstitucionBean> getAll() 
    {
        List<InstitucionBean> l = new ArrayList();
        l = dao.findAll();
        return l;
    }

    public void modifica(int id, String corta,String nombre,String clave , int usuario ) 
    {
        dao.update(id, corta,nombre,clave,usuario);
    }

    public void borrar(int id) {
        dao.delete(id);
    }
 

    public void crea(String clave, String corta, String nombre, boolean activo) {
        dao.insert(corta, nombre, clave, 1);
    }
    
}
