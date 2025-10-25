package com.yubo.DAO;

import com.yubo.Model.Pelicula;
import org.hibernate.Session;
import java.util.List;

public interface PeliculaDAO {
    void insertarPelicula(Session session, Pelicula p);
    void modificarPelicula(Session session, Pelicula p);
    void borrarPelicula(Session session, Pelicula p);
    List<Pelicula> listarPelicula(Session session);
}
