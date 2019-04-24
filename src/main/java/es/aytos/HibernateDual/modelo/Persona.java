package es.aytos.HibernateDual.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity(name = "persona")
public class Persona implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "idPersona")
	private Integer id;

	private String nombre;

	private String apellidos;

	private String nif;

	private LocalDate fechaNacimiento;

	@Enumerated
	private EstadoCivil estadoCivil;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "persona")
	private List<Direccion> direcciones;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idAficion")
	private List<Aficion> aficiones;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	public Persona() {
	}

	public Persona(String nombre, String apellidos, String nif, LocalDate fechaNacimiento, EstadoCivil estadoCivil) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nif = nif;
		this.fechaNacimiento = fechaNacimiento;
		this.estadoCivil = estadoCivil;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public List<Direccion> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public List<Aficion> getAficiones() {
		return aficiones;
	}

	public void setAficiones(List<Aficion> aficiones) {
		this.aficiones = aficiones;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
