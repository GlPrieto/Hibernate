package es.aytos.HibernateDual.repositorio;

import java.util.List;

import org.hibernate.Session;

import es.aytos.HibernateDual.modelo.Aficion;
import es.aytos.HibernateDual.modelo.Direccion;
import es.aytos.HibernateDual.modelo.Persona;
import es.aytos.HibernateDual.modelo.Usuario;
import es.aytos.HibernateDual.util.HibernateUtil;

public class RepositorioPersona {

	public static Integer crearPersona(final Persona persona, final Usuario usuario, List<Direccion> direcciones,
			List<Aficion> aficiones) {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			final Integer personaBBDD = (Integer) sesion.save(persona);

			persona.setId(personaBBDD);
			persona.setUsuario(usuario);
			usuario.setPersona(persona);
			// sesion.flush
			persona.setDirecciones(direcciones);
			direcciones.stream().forEach(d -> d.setPersona(persona));
			persona.setAficiones(aficiones);
			sesion.getTransaction().commit();
			return personaBBDD;
		} catch (Exception e) {
			System.out.println("Se ha generado un error al crear persona con el id " + persona.getId());
			e.printStackTrace();
			sesion.getTransaction().rollback();
			throw new RuntimeException(e);
		}

		finally {
			sesion.close();
		}
	}

	public static Persona getPersona(final Integer idPersona) {
		try (Session sesion = HibernateUtil.getSessionFactory().getCurrentSession()) {
			sesion.beginTransaction();
			return (Persona) sesion.createQuery("from persona where idPersona = :idPersona")
					.setParameter("idPersona", idPersona).uniqueResult();
		} catch (Exception e) {
			System.out.println("Se ha producido un error consultando la persona");
			throw new RuntimeException(e);
		}
	}

	public static Persona getPersona2(final String nombre) {
		try (Session sesion = HibernateUtil.getSessionFactory().getCurrentSession()) {
			sesion.beginTransaction();
			return (Persona) sesion.createQuery("from persona where nombre = :nombre").setParameter("nombre", nombre)
					.uniqueResult();
		} catch (Exception e) {
			System.out.println("Se ha producido un error consultando la persona");
			throw new RuntimeException(e);
		}
	}

	public static List<Persona> consultarPersonas() {
		try (Session sesion = HibernateUtil.getSessionFactory().getCurrentSession()) {
			sesion.beginTransaction();
			final List<Persona> resultados = sesion.createQuery("from persona").list();
			return resultados;
		} catch (Exception e) {
			System.out.println("Se ha producido un error consultando la persona");
			throw new RuntimeException(e);
		}
	}

	public static void actualizarPersona(final Integer idPersona, final String nif) {

		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			sesion.beginTransaction();
			final Persona persona = (Persona) sesion.createQuery("from persona where idPersona = :idPersona")
					.setParameter("idPersona", idPersona).uniqueResult();
			persona.setNif(nif);
			// Actualizar persona

			// sesion.createQuery("Update persona set nif = :nif where idPersona =
			// :idPersona").setParameter("nif", nif)
			// .setParameter("idPersona", idPersona).executeUpdate();
			sesion.getTransaction().commit();
		} catch (Exception e) {

			sesion.getTransaction().rollback();
			System.out.println("Se ha producido un error actualizando la persona");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			sesion.close();
		}
	}

	// public static void eliminarPersona(final Integer idPersona) {
	//
	// Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
	//
	// try {
	// sesion.beginTransaction();
	// final Persona persona = (Persona) sesion.createQuery("from persona where
	// idPersona = :idPersona")
	// .setParameter("idPersona", idPersona).uniqueResult();
	//
	// // sesion.createQuery("delete from Usuario whereUpdate persona set nif = :nif
	// // where idPersona =
	// // :idPersona").setParameter("nif", nif)
	// // .setParameter("idPersona", idPersona).executeUpdate();
	// sesion.getTransaction().commit();
	// } catch (Exception e) {
	//
	// sesion.getTransaction().rollback();
	// System.out.println("Se ha producido un error actualizando la persona");
	// e.printStackTrace();
	// throw new RuntimeException(e);
	// } finally {
	// sesion.close();
	// }
	// }

}
