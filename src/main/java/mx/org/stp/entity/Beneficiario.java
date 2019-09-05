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
public class Beneficiario {
    private String  bancoReceptor;
    private String  nombre;
    private String  tipoCuenta;
    private String  cuenta;
    private String  rfc;
    private String  concepto;
    private String  iva;
    private String  montoPago;
}
