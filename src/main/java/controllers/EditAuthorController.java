package controllers;

import database.models.Author;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditAuthorController extends ControllerBase {
    private Author a;
    private ControllerBase parent;

    @FXML
    private TextField NameField;

    @FXML
    private TextField EmailField;

    @FXML
    void Save(ActionEvent event) {
        a.setName(NameField.getText());
        a.setEmail(EmailField.getText());
        parent.fileSystem.saveAuthor(a);
    }

    public void init(Author author, ControllerBase parent) {
        this.a = author;
        this.parent = parent;
        NameField.setText(a.getName());
        EmailField.setText(a.getEmail());
    }
}
