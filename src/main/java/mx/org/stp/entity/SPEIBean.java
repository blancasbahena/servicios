/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.stp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author Luis Blancas
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SPEIBean
{
    private String  fechaOperacion;
    private String  hora;
    private String  claveSPEI;
    private String  sello;
    private String  claveRastreo;
    private Beneficiario beneficiario;
    private Ordenante ordenante;
}
