package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class EditCharacterController extends ControllerBase{
    int id;

    @FXML
    private TextArea historyField;

    @FXML
    private TextArea relationField;

    @FXML
    private TextField nameField;

    @FXML
    void save(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHistoryField(String history) {
        this.historyField.setText(history);
    }

    public void setRelationField(String relation) {
        this.relationField.setText(relation);
    }

    public void setNameField(String name) {
        this.nameField.setText(name);
    }
}