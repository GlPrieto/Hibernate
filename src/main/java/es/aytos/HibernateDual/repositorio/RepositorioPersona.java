package es.aytos.HibernateDual.repositorio;

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
		} catch (Exception e) {
			System.out.println("Se ha generado un error al crear persona con el id ");
			e.printStackTrace();
			sesion.getTransaction().rollback();
			throw new RuntimeException(e);

		}

		finally {
			sesion.close();
		}
		return persona.getId();
	}
}
