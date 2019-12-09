package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class AlertController {

    @FXML
    private TextFlow text;

    @FXML
    public void initialize(){

    }

    @FXML
    void close(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setText(String textToSet){
        Text text_1 = new Text(textToSet);
        text_1.setFill(Color.RED);
        text_1.setFont(Font.font("Verdana", 15));
        text.getChildren().add(text_1);
    }
}
