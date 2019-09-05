/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.stp.controller;

import java.util.List;
import mx.org.stp.entity.Estatus;
import mx.org.stp.entity.dao.EstatusDAO;
/**
 *
 * @author Luis Blancas
 */
public class ActivoController {

    public List<Estatus> getAll() 
    {
        EstatusDAO dao=new EstatusDAO();
        List<Estatus> lista=  dao.findAll();
        return lista;
    }
    public Estatus getOne(int id) 
    {
        EstatusDAO dao=new EstatusDAO();
        Estatus bean = dao.findById(id);
        return bean;
    }
}
