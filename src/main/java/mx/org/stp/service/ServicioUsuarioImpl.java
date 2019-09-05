/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.stp.service;

import mx.org.stp.entity.Usuario;
/**
 *
 * @author Luis Blancas
 */

public class ServicioUsuarioImpl implements ServicioUsuarioInterface
{
    @Override
    public Usuario findControl(String usuariom, String password) 
    {
        Usuario user =new Usuario();
        user.setActivo(1);
        user.setActivostr("Activo");
        user.setApellidos("Blancas");
        user.setCabecera("Example");
        user.setId(1);
        user.setLogin("Login");
        user.setMsg("OK");
        user.setNombre("Luis");
        user.setPassword("******");
        user.setPerfil(1);
        user.setPerfilstr("ADMON");
        user.setPagina("./content/admin/activo/ListaActivo.xhtml?faces-redirect=true");
        return user;
    }
    
}
