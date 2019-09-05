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
public class Zip 
{
    private long    id;
    private String  nombre;
    private int     pdf;
    private int     xml;
    private int     pro;
    private int     creacion;
    private String  fcreacion;
    private int     activo;
}
