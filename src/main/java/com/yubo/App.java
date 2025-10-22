package com.yubo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/ui/pelicula-Hibernate.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Json Yubo");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
