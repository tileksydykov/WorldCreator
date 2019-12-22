package controllers;

import database.models.BookCharacter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class EditCharacterController extends ControllerBase{
    BookCharacter character;
    ControllerBase parent;

    @FXML
    private TextArea historyField;

    @FXML
    private TextArea relationField;

    @FXML
    private TextField nameField;

    @FXML
    void save(ActionEvent event) {
        character.setName(nameField.getText());
        character.setRelation(relationField.getText());
        character.setHistory(historyField.getText());

        parent.fileSystem.saveCharacter(character);

        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void init(BookCharacter character, ControllerBase context) {
        this.character = character;
        parent = context;
        nameField.setText(character.getName());
        historyField.setText(character.getHistory());
        relationField.setText(character.getRelation());
    }
}