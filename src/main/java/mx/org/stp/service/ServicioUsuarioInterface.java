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
public interface ServicioUsuarioInterface {
    public Usuario findControl(String usuariom, String password);
}
