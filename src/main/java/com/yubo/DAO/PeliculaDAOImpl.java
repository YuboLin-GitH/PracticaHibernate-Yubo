package com.yubo.DAO;


import com.yubo.Model.Pelicula;
import org.hibernate.Session;


import java.util.List;


public class PeliculaDAOImpl implements PeliculaDAO {

    @Override
    public void insertarPelicula(Session session, Pelicula p)
    {

        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
    }

    @Override
    public void modificarPelicula(Session session,Pelicula p)
    {
        session.beginTransaction();
        session.update(p);
        session.getTransaction().commit();
    }

    @Override
    public void borrarPelicula(Session session,Pelicula p)
    {

        session.beginTransaction();
        session.delete(p);
        session.getTransaction().commit();
    }

    @Override
    public List<Pelicula> listarPelicula(Session session)
    {
        List<Pelicula> lista = session.createQuery("from Pelicula", Pelicula.class).list();
        return lista;

        //list.forEach(System.out::println);//version 1.8 de java
    }



}
