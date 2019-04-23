package es.aytos.HibernateDual.modelo;

import java.io.Serializable;
import java.util.Date;

public class Persona implements Serializable {

	private Integer id;
	private String nombre;
	private String apellidos;
	private String nif;
	private Date fechaNacimiento;
	private Integer estadoCivil;
}
