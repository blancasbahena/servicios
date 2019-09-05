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
public class RegistroBean
{
    private String  fecha_pago;
    private String  clave_rastreo;
    private String  clave_banco_emisor;
    private String  clave_banco_receptor;
    private String  cuenta_beneficiario;
    private String  monto_transferencia;
}
