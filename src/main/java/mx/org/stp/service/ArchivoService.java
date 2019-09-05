/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.stp.service;
import mx.org.stp.repository.ArchivoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 *
 * @author Luis Blancas
 */
@Component
public class ArchivoService 
{
    @Autowired
	private ArchivoRepository archivoRepository;

	public ArchivoRepository getArchivoRepository() {
		return archivoRepository;
	}

	public void setArchivoRepository(ArchivoRepository archivoRepository) {
		this.archivoRepository = archivoRepository;
	}
}
