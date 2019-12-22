package controllers;

import database.WorldCreatorDatabase;
import filesystem.ProjectFileSystem;
import helpers.Loader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

abstract class ControllerBase {
    public Loader loader = new Loader();
    ProjectFileSystem fileSystem = new ProjectFileSystem();
    public ControllerBase() {}

    void alert(String messageToUser){
        Stage stage = new Stage();
        try {
            stage.setTitle("Alert");
            Loader l = loader.getLoader("Alert");
            stage.setScene(new Scene(l.load()));
            AlertController control = l.getController();
            control.setText(messageToUser);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
