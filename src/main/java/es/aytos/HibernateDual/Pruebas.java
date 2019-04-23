package es.aytos.HibernateDual;

import es.aytos.HibernateDual.modelo.Persona;
import es.aytos.HibernateDual.repositorio.RepositorioPersona;

public class Pruebas {

	public static void main(String[] args) {
		Persona persona = new Persona();
		final Integer idPersona = RepositorioPersona.crearPersona(persona);
		System.out.println("Se ha creado la persona con id: " + idPersona);
	}

}
