package es.aytos.HibernateDual.repositorio;

import org.hibernate.Session;

import es.aytos.HibernateDual.modelo.Direccion;
import es.aytos.HibernateDual.util.HibernateUtil;

public class RepositorioDireccion {

	public static Direccion getDireccion(Integer idDireccion) {
		try (Session sesion = HibernateUtil.getSessionFactory().getCurrentSession()) {
			sesion.beginTransaction();
			return (Direccion) sesion.createQuery("from Direccion where idDireccion = :idDireccion")
					.setParameter("idDireccion", idDireccion).uniqueResult();
		} catch (Exception e) {
			System.out.println("Se ha producido un error consultando la direccion " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
