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
public class ResponseBean {
    private String fecha_pago;
    private String clave_rastreo;
    private String clave_banco_emisor;;
    private String clave_banco_receptor;
    private String monto;
    private String rfc_cep;
    private String nombre_beneficiario_cep;
    private String sello_cep;
}
