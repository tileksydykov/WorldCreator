package controllers;

import com.sun.javafx.collections.ObservableListWrapper;
import database.models.Author;
import filesystem.models.Project;
import helpers.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
        a.add("Custom");
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
        String authorName = nameField.getText();
        String auhtorEmail = emailField.getText();
        String projectName = bookNameField.getText();
        ArrayList<Author> a = new ArrayList<Author>();
        a.add(new Author(authorName, auhtorEmail));
        Project p = new Project();
        p.setAuthors(a);
        p.setFileName(projectName);
        p.setName(projectName);
        p.setType(bookType.getValue());
        fileSystem.saveProject(p);

        Node source = (Node)  event.getSource();
        Stage s  = (Stage) source.getScene().getWindow();
        s.close();

        Stage stage = new Stage();
        try{
            stage.setMaximized(true);
            stage.setTitle(projectName);
            FXMLLoader l =loader.getLoader("MainScene");
            stage.setScene(l.load());
            MainController c = l.getController();
            c.initProject(projectName);
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }
}
