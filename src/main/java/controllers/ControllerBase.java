package controllers;

import helpers.Loader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

abstract class ControllerBase {
    Loader loader = new Loader();
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
