module com.yubo.practicahibernateyubo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.yubo.practicahibernateyubo to javafx.fxml;
    exports com.yubo.practicahibernateyubo;
}