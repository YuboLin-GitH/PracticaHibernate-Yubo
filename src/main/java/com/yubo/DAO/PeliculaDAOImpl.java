package com.yubo.DAO;


import com.yubo.Model.Pelicula;
import org.hibernate.Session;


import java.util.List;


public class PeliculaDAOImpl {

    public static  void insertarPelicula(Session session, Pelicula p)
    {

        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
    }
    public static  void modificarPelicula(Session session,Pelicula p)
    {
        session.beginTransaction();
        session.update(p);
        session.getTransaction().commit();
    }
    public static  void borrarPelicula(Session session,Pelicula p)
    {

        session.beginTransaction();

        session.delete(p);

        session.getTransaction().commit();
    }
    public static List<Pelicula> listarPelicula(Session session)
    {
        List<Pelicula> lista = session.createQuery("from Pelicula", Pelicula.class).list();
        return lista;

        //list.forEach(System.out::println);//version 1.8 de java
    }
    public static  Pelicula buscarPelicula(Session session,int id)
    {
        Pelicula p;

        p=(Pelicula) session.get(Pelicula.class,id);
        System.out.println(p.getId());
        System.out.println(p.getTitulo());
        System.out.println(p.getFecha());
        System.out.println(p.getDirector());
        System.out.println(p.getGenero());
        return p;
    }
}
