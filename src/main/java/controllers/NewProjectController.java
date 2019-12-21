package controllers;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class NewProjectController extends ControllerBase {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField bookNameField;

    @FXML
    private ChoiceBox<String> bookType;

    @FXML
    public void initialize(){
        ArrayList<String> a = new ArrayList<>();
        a.add("Web novel");
        a.add("Light novel");
        a.add("Novel");
        a.add("Short story");
        bookType.setItems(new ObservableListWrapper<>(a));
    }

    @FXML
    void Back(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        try{
            stage.setScene(loader.getScene("HelloScene"));
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    @FXML
    void Close(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Next(ActionEvent event) {

    }

}
