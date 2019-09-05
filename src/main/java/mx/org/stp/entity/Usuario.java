/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.stp.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author BID
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String  nombre;
    private String  apellidos;
    private String  paterno;
    private String  materno;
    private String  login; 
    private String  password;
    private Integer perfil;
    private Integer activo;
    private String  perfilstr;
    private String  activostr;
    private String  cabecera;
    private String  msg;
    private String  pagina; 
    private boolean estatus;
     
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", login=" + login + ", password=" + password + ", perfil=" + perfil + ", activo=" + activo + ", perfilstr=" + perfilstr + ", activostr=" + activostr + '}';
    }
    
}
