package es.aytos.HibernateDual.repositorio;

import java.util.List;

import org.hibernate.Session;

import es.aytos.HibernateDual.modelo.Persona;
import es.aytos.HibernateDual.util.HibernateUtil;

public class RepositorioPersona {

	public static Integer crearPersona(final Persona persona) {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			final Integer personaBBDD = (Integer) sesion.save(persona);
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

}
