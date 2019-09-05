/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.stp.mb;

import java.io.Serializable;

/**
 *
 * @author Luis Blancas
 */
public class Response  implements Serializable{
    private static final long serialVersionUID = 1000013L;
    private String menssage;
    private String materno;
    private String paterno;
    private String caduca;
    private String usuario;
    private String nombre;
    private String email;
    private String token;
    public Response(String menssage12,
			String materno12,
			String paterno12,
			String caduca12,
			String usuario12,
			String nombre12,
			String email12,
			String token12)
    {
            this.menssage=menssage12;
            this.materno=materno12;
            this.paterno =  paterno12;
            this.caduca =  caduca12;
            this.usuario =  usuario12;
            this.nombre =  nombre12;
            this.email =  email12;
            this.token =  token12;
    }
    public Response(String menssage1)
    {
            this.menssage = menssage1;
    }

    public String getMenssage() {
        return menssage;
    }

    public void setMenssage(String menssage) {
        this.menssage = menssage;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getCaduca() {
        return caduca;
    }

    public void setCaduca(String caduca) {
        this.caduca = caduca;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public  String toEncoder()
    {
	return EncryptUtil.encryptDES(this.toString());
    }
}