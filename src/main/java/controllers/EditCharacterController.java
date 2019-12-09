package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

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