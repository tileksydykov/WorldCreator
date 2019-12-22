package controllers;

import controllers.controllerHelpers.MainDataHolder;
import database.models.BookCharacter;
import filesystem.models.Project;
import helpers.Loader;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends ControllerBase {
    public Project project;
    private MainDataHolder helper;
    private MainController context;

    @FXML
    private TreeView<?> mainTree;

    @FXML
    public TabPane mainPane;

    @FXML
    private TextField addCharacterField;

    @FXML
    private ListView<Label> characterList;

    @FXML
    public void initialize() {
        context = this;
    }

    void saveProject() {
        fileSystem.saveProject(project);
    }

    void refreshProject() {
        project = fileSystem.readProject(project.getFileName());
    }

    void initProject(String projectName) {
        fileSystem.setProjectName(projectName);
        project = fileSystem.readProject(projectName);
        helper = new MainDataHolder(this);
        mainTree.setRoot(helper.getTree());

        mainPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
    }

    @FXML
    void addCharacter(ActionEvent event) {
        String s = addCharacterField.getText();
        addCharacterField.setText("");

        if (s.isEmpty()) {
            return;
        }

        BookCharacter character = new BookCharacter();
        character.setName(s);

        fileSystem.addCharacter(character);

        Label c = new Label();
        c.setText(s);
        c.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event != null) {
                    Stage stage = new Stage();
                    stage.setTitle("Edit character: " + s);
                    Loader l = loader.getLoader("EditCharacterScene");
                    try {
                        stage.setScene(new Scene(l.load()));
                    } catch (IOException e) {
                        System.out.println(e.toString());
                    }
                    EditCharacterController c = l.getController();
                    c.init(character, context);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();

                }
            }
        });

        characterList.getItems().add(characterList.getItems().size(), c);
        characterList.scrollTo(characterList.getItems().size() - 1);

        new AnimationTimer() {
            int frameCount = 0;

            @Override
            public void handle(long now) {
                frameCount++;
                if (frameCount > 1) {
                    characterList.edit(characterList.getItems().size() - 1);
                    stop();
                }
            }

        }.start();
    }

    @FXML
    void openNewProject(ActionEvent event) {
        Stage newWindow = new Stage();
        Scene scene;
        try {
            scene = loader.getScene("NewProjectScene");
        } catch (Exception e) {
            scene = new Scene(new Label("error"));
        }
        newWindow.setTitle("New Project Initialization");
        newWindow.setScene(scene);
        newWindow.show();
    }

    @FXML
    void openAbout(ActionEvent event) {
        Stage newWindow = new Stage();
        Scene scene;
        try {
            scene = loader.getScene("AboutScene");
        } catch (Exception e) {
            scene = new Scene(new Label("error"));
        }
        newWindow.setTitle("New Project Initialization");
        newWindow.setScene(scene);
        newWindow.show();
    }
}

