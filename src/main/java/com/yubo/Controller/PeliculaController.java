package com.yubo.Controller;


import com.yubo.DAO.PeliculaDAO;
import com.yubo.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.yubo.DAO.PeliculaDAOImpl;
import com.yubo.Model.Pelicula;
import com.yubo.util.AlertUtils;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;



public class PeliculaController {

    private final PeliculaDAO peliculaDAO = new PeliculaDAOImpl();

    @FXML
    public TextField tfTitulo, tfFecha, tfGenero, tfDirector;

    @FXML
    public ListView<Pelicula> lvListaPelicula;
    @FXML
    public Button btImportar, btNuevo, btModificar, btEliminar, btLimpiar;

    private Pelicula peliculaSeleccionada;

    public  void initialize() {
        try{
            configurarListView();
            enlazarSeleccionDeTabla();
            lvListaPelicula.setOnMouseClicked(this::onPeliculaSeleccionada);
        }catch (Exception e){ // Fallo en la base de datos
            System.out.println("Error de programa");
        }

    }

    private void configurarListView() {
        lvListaPelicula.setCellFactory(param -> new ListCell<Pelicula>() {
            @Override
            protected void updateItem(Pelicula pelicula, boolean empty) {
                super.updateItem(pelicula, empty);
                if (empty || pelicula == null) {
                    setText(null);
                } else {
                    setText(pelicula.getTitulo());
                }
            }
        });
    }

    public void importaDatos(ActionEvent event) {
        cargarDatos();
    }


    public void cargarDatos() {
        //modoEdicion(false);
        lvListaPelicula.getItems().clear();

        try (Session session = HibernateUtil.getSession()){
            List<Pelicula> pelicula = peliculaDAO.listarPelicula(session);
            lvListaPelicula.setItems(FXCollections.observableList(pelicula));

        }
    }
    private void enlazarSeleccionDeTabla() {
        lvListaPelicula.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                peliculaSeleccionada = newVal;
            }
        });
    }


    private void onPeliculaSeleccionada(MouseEvent event) {
        Pelicula p = lvListaPelicula.getSelectionModel().getSelectedItem();
        if (p != null) {
            mostrarPelicula(p);
        }
    }

    private void mostrarPelicula(Pelicula p) {
        tfTitulo.setText(p.getTitulo());
        tfFecha.setText(String.valueOf(p.getFecha()));
        tfGenero.setText(p.getGenero());
        tfDirector.setText(p.getDirector());
    }

    @FXML
    public void nuevoPelicula() {

        if (tfTitulo.getText().isEmpty() || tfFecha.getText().isEmpty() || tfGenero.getText().isEmpty() || tfDirector.getText().isEmpty()) {
            AlertUtils.mostrarError("No puede tiene los campos vacios");
            return;
        }
        try(Session session = HibernateUtil.getSession()) {
            Pelicula p = new Pelicula();
            p.setTitulo(tfTitulo.getText());
            p.setFecha(LocalDate.parse(tfFecha.getText()));
            p.setGenero(tfGenero.getText());
            p.setDirector(tfDirector.getText());

            peliculaDAO.insertarPelicula(session, p);

            AlertUtils.mostrarInformacion("Película insertada correctamente");
            limpiarPelicula();
            cargarDatos();

        }catch (Exception e){
            System.out.println("Error de Insertar Pelicula");
        }
    }


    @FXML
    public void modificarPelicula() {
        if (peliculaSeleccionada == null) {
            AlertUtils.mostrarError("el seleccionado no existe");
            return;
        }
        try(Session session = HibernateUtil.getSession())  {

            peliculaSeleccionada.setTitulo(tfTitulo.getText());
            peliculaSeleccionada.setFecha(LocalDate.parse(tfFecha.getText()));
            peliculaSeleccionada.setGenero(tfGenero.getText());
            peliculaSeleccionada.setDirector(tfDirector.getText());

            peliculaDAO.modificarPelicula(session, peliculaSeleccionada);


            AlertUtils.mostrarInformacion("Pelicula modificada correctamente");
            limpiarPelicula();
            cargarDatos();

        } catch (Exception e) {
            AlertUtils.mostrarError("Error：" + e.getMessage());
        }

    }

    @FXML
    public void eliminarPelicula() {
        if (peliculaSeleccionada == null) {
            AlertUtils.mostrarError("el seleccionado no existe");
            return;
        }
        try(Session session = HibernateUtil.getSession())  {

            peliculaDAO.borrarPelicula(session, peliculaSeleccionada);


            AlertUtils.mostrarInformacion("Pelicula eliminada correctamente");
            limpiarPelicula();
            cargarDatos();

        } catch (Exception e) {
            AlertUtils.mostrarError("Error：" + e.getMessage());
        }

    }


    @FXML
    public void limpiarPelicula() {
        tfTitulo.setText("");
        tfFecha.setText("");
        tfGenero.setText("");
        tfDirector.setText("");
    }

    /*  no necesito
    private void modoEdicion(boolean activar) {
        btImportar.setDisable(activar);

        tfTitulo.setEditable(activar);
        tfFecha.setEditable(activar);
        tfGenero.setEditable(activar);
        tfDirector.setEditable(activar);

        lvListaPelicula.setDisable(activar);
    }
    */
}