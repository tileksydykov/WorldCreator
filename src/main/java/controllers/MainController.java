package controllers;

import controllers.controllerHelpers.MainDataHolder;
import database.models.BookCharacter;
import filesystem.models.Project;
import helpers.Loader;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

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

        initCharactes();
        mainPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
    }

    private void initCharactes() {
        ArrayList<BookCharacter> character = project.getCharacters();
        for (BookCharacter c : character) {
            addCharacterTolist(c);
        }
    }

    private void addCharacterTolist(BookCharacter character) {
        Label label = new Label();
        label.setText(character.getName());
        label.setOnMouseClicked((MouseEvent e) -> {
            if (e != null) {
                Stage stage = new Stage();
                stage.setTitle("Edit character: " + character.getName());
                Loader l = loader.getLoader("EditCharacterScene");
                try {
                    stage.setScene(new Scene(l.load()));
                } catch (IOException exc) {
                    System.out.println(exc.toString());
                }
                EditCharacterController c = l.getController();
                c.init(character, context);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            }
        });

        characterList.getItems().add(characterList.getItems().size(), label);
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
    void addCharacter(ActionEvent event) {
        String s = addCharacterField.getText();
        addCharacterField.setText("");

        if (s.isEmpty()) {
            return;
        }

        BookCharacter character = new BookCharacter();
        character.setName(s);

        fileSystem.addCharacter(character);
        addCharacterTolist(character);
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

    public void save() {
        fileSystem.saveProject(project);
    }

    public void updateUI(){
        String projName = project.getName();
        project = fileSystem.readProject(projName);
        mainTree.setRoot(helper.getTree());
        characterList.getItems().removeAll();
        initCharactes();
    }
}

