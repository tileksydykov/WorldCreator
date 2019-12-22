package controllers;

import database.models.Author;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditAuthorController extends ControllerBase {
    private Author a;

    @FXML
    private TextField NameField;

    @FXML
    private TextField EmailField;

    @FXML
    void Save(ActionEvent event) {

    }

    public void setAuthor(Author author) {
        this.a = author;
        NameField.setText(a.getName());
        EmailField.setText(a.getEmail());
    }

}
