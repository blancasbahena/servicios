/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.stp.controller;

import java.util.ArrayList;
import java.util.List;
import mx.org.stp.entity.Zip;
import mx.org.stp.entity.dao.ZipDAO;

/**
 *
 * @author Luis Blancas
 */
public class ZipController {
    private ZipDAO dao= new ZipDAO();
    public List<Zip> getAll() 
    {
        List<Zip> lista=new ArrayList();
        lista = dao.findAll();
        return lista;
    }
    public void limpiar()
    {
        dao.limpiar();
    }
    
    public Zip cargaZIP(String nombre,int conteoPDF,int conteoXML,int  size,int usuario,String context) 
    {
        return dao.insert(nombre, usuario, conteoPDF, conteoXML, size,context);
    }
    public List<Zip> getDataGraph() 
    {
        List<Zip> lista=new ArrayList();
        lista = dao.findAllForGraph();
        return lista;
    }
}
