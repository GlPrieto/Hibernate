package es.aytos.HibernateDual;

import java.time.LocalDate;
import java.util.Arrays;

import es.aytos.HibernateDual.modelo.Aficion;
import es.aytos.HibernateDual.modelo.Direccion;
import es.aytos.HibernateDual.modelo.EstadoCivil;
import es.aytos.HibernateDual.modelo.Persona;
import es.aytos.HibernateDual.modelo.Provincia;
import es.aytos.HibernateDual.modelo.Usuario;
import es.aytos.HibernateDual.repositorio.RepositorioPersona;

public class Pruebas {

	public static void main(String[] args) {
		// Persona persona = new Persona("Pepito", "Perez", "44433322P",
		// LocalDate.now(), EstadoCivil.SOLTERO);
		// Persona persona2 = new Persona("Pepa", "Perico", "44411122Q",
		// LocalDate.now(), EstadoCivil.CASADO);
		// final Integer idPersona = RepositorioPersona.crearPersona(persona2);
		// System.out.println("Se ha creado la persona con id: " + idPersona);

		// final Persona persona = RepositorioPersona.getPersona(2);
		// System.out.println(
		// "Persona con id" + persona.getId() + ": " + persona.getNombre() + " " +
		// persona.getApellidos());

		// final Persona persona2 = RepositorioPersona.getPersona2("Pepito");
		// System.out.println("Persona con nombre " + persona2.getNombre() + ": " +
		// persona.getNombre() + " "
		// + persona.getApellidos());

		// final List<Persona> personas = RepositorioPersona.consultarPersonas();
		// personas.stream().forEach(p -> System.out.println("Persona: " + p.getNombre()
		// + " " + p.getApellidos()));
		//
		// final Direccion direccion = RepositorioDireccion.getDireccion(1);
		// System.out.println(direccion.getDireccion());
		// System.out.println(direccion.getCodigoPostal());
		// System.out.println(direccion.getProvincia());
		// System.out.println(direccion.getPersona());

		// final Persona persona1 = RepositorioPersona.getPersona(2);
		// System.out.println(
		// "Persona con id" + persona1.getId() + ": " + persona1.getNombre() + " " +
		// persona1.getApellidos());
		// System.out.println("Persona con id" + persona1.getId() + ", y direcciones: "
		// + persona1.getDirecciones());
		// System.out.println("Aficiones: ");
		// persona1.getAficiones().stream().forEach(a ->
		// System.out.print(a.getDescripcion() + " "));

		final Integer idPersona = crearPersona();
		System.out.println("Se ha creado la persona");

	}

	public static Integer crearPersona() {

		// 1. Creacion persona

		final Persona persona = new Persona();
		persona.setNombre("Gl");
		persona.setApellidos("Prieto");
		persona.setNif("44444444T");
		persona.setEstadoCivil(EstadoCivil.SOLTERO);
		persona.setFechaNacimiento(LocalDate.now());

		// 2. Creacion direcciones

		Direccion direccion1 = new Direccion();
		direccion1.setCodigoPostal(41400L);
		direccion1.setDireccion("av.Genil, 16");
		direccion1.setProvincia(Provincia.SEVILLA);

		Direccion direccion2 = new Direccion();
		direccion2.setCodigoPostal(41400L);
		direccion2.setDireccion("Plaza Blas Infante, 1");
		direccion2.setProvincia(Provincia.SEVILLA);

		persona.setDirecciones(Arrays.asList(direccion1, direccion2));

		// 3. Creacion aficiones

		Aficion aficion1 = new Aficion();
		aficion1.setIdAficion(1);

		Aficion aficion2 = new Aficion();
		aficion2.setIdAficion(5);

		persona.setAficiones(Arrays.asList(aficion1, aficion2));

		// 4. Creacion usuario

		Usuario usuario = new Usuario();
		usuario.setContraseña("1234");
		usuario.setEmail("email@gmail.com");

		persona.setUsuario(usuario);

		return RepositorioPersona.crearPersona(persona);

	}

}
