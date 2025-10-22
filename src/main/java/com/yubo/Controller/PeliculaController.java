package com.yubo.Controller;


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

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;



public class PeliculaController {

    private final PeliculaDAOImpl peliculaDAO = new PeliculaDAOImpl();

    @FXML
    public TextField tfTitulo, tfFecha, tfGenero, tfDirector;

    @FXML
    public ListView<Pelicula> lvListaPelicula;
    @FXML
    public Button btImportar, btNuevo, btModificar, btEliminar, btLimpiar;


    public  void initialize() {
        try{}catch (Exception e){ // Fallo en la base de datos
            System.out.println("Error de programa");
        }

        lvListaPelicula.setCellFactory(param -> new ListCell<Pelicula>() {
            @Override
            protected void updateItem(Pelicula pelicula, boolean empty) {
                super.updateItem(pelicula, empty);
                if (empty || pelicula == null) {
                    setText(null);
                }else {
                    setText(pelicula.getTitulo());
                }
            }
        });

        lvListaPelicula.setOnMouseClicked(this::onPeliculaSeleccionada);
    }

    public void onImportarClick(ActionEvent event) {
        cargarDatos();
    }


    public void cargarDatos() {
        //modoEdicion(false);
        lvListaPelicula.getItems().clear();

        try (Session session = HibernateUtil.getSession()){
            List<Pelicula> pelicula = PeliculaDAOImpl.listarPelicula(session);
            lvListaPelicula.setItems(FXCollections.observableList(pelicula));

        }
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

            PeliculaDAOImpl.insertarPelicula(session, p);


        }catch (Exception e){
            System.out.println("Error de Insertar Pelicula");
        }
    }


    @FXML
    public void limpiarPelicula() {
        tfTitulo.setText("");
        tfFecha.setText("");
        tfGenero.setText("");
        tfDirector.setText("");
    }

    private void modoEdicion(boolean activar) {
        btImportar.setDisable(activar);

        tfTitulo.setEditable(activar);
        tfFecha.setEditable(activar);
        tfGenero.setEditable(activar);
        tfDirector.setEditable(activar);

        lvListaPelicula.setDisable(activar);
    }

}