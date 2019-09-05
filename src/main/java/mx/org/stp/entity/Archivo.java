/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.stp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Luis Blancas
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Archivo { 
    private long    id;
    private String  nombre;
    private String  fecha_pago_i;
    private String  fecha_pago_f;
    private long    creado;
    private String  fecha_creacion;
    private long    activo;
    public Archivo( String  nombre,
     String  fecha_pago_i,
    String  fecha_pago_f,
    long    creado,
    String  fecha_creacion,
    long    activo)
    {
        this.activo = activo;
        this.nombre=nombre;
        this.fecha_creacion = fecha_creacion;
        this.fecha_pago_f =  fecha_pago_f;
        this.fecha_pago_i =  fecha_pago_i;
        this.creado=  creado ;
                
    }
}
