/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.stp.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class DetailFile {
    private String fecha_pago;
    private String clave_rastreo;
    private String clave_banco_emisor;
    private String clave_banco_receptor;
    private String cuenta_beneficiario;
    private String monto_transferencia;

    @Override
    public String toString()
    {
        try {
            String pattern = "yyyyMMdd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date date = simpleDateFormat.parse(fecha_pago);
            pattern = "yyyy-MM-dd";
            simpleDateFormat = new SimpleDateFormat(pattern);
            fecha_pago = simpleDateFormat.format(date);
        } catch (ParseException ex) {
            Logger.getLogger(DetailFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "" + fecha_pago + "," + clave_rastreo + "," + clave_banco_emisor + "," + clave_banco_receptor + "," + cuenta_beneficiario + "," + monto_transferencia;
    }
    
}
