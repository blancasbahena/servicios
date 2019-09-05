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
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Luis Blancas
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArchivoBean
{
    private StreamedContent file;
    private int    id;
    private String  nombre;
    private String  nombreZip;
    private String  fecha_pago_i;
    private String  fecha_pago_f;
    private int     creado;
    private String  fecha_creacion;
    private boolean activo;
    public ArchivoBean (int id,String nombre,String fecha_pago_i,
            String fecha_pago_f,int creado,String  fecha_creacion,
            boolean activo,String nombreZip)
    {
        this.id=id;
        this.nombre=nombre;
        this.fecha_pago_i = fecha_pago_i;
        this.fecha_pago_f = fecha_pago_f;
        this.creado =  creado;
        this.fecha_creacion =  fecha_creacion;
        this.activo  = activo;
        this.nombreZip=nombreZip;
    }
    
    public StreamedContent getFile() {
        System.out.println("seleccion Editar (getFile) "+this.toString());
        return file;
    }
}
