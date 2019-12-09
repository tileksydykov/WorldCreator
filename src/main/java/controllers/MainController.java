package controllers;

import controllers.controllerHelpers.MainControllerHelper;
import helpers.SceneLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MainController {
    private SceneLoader sceneLoader;

    @FXML
    private TreeView<?> mainTree;

    @FXML
    private TabPane mainPane;

    @FXML
    private TextField addCharacterField;

    @FXML
    private Button adCharacterButton;

    @FXML
    private ListView<?> characterList;

    @FXML
    public void initialize() {
        sceneLoader = new SceneLoader();

        MainControllerHelper helper = new MainControllerHelper();

        mainTree.setRoot(helper.getTree());

        mainTree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<?>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<?>> observable, TreeItem<?> oldValue, TreeItem<?> newValue) {
                String tabName = newValue.getValue().toString();
                boolean exist = false;
                Tab t = new Tab();
                for (Tab b : mainPane.getTabs()) {
                    if (b.getText().equals(tabName)) {
                        t = b;
                        exist = true;
                    }
                }
                if (!exist) {
                    t.setText(tabName);
                    t.setContent(helper.getPane(tabName));
                    mainPane.getTabs().add(t);
                }
                mainPane.getSelectionModel().select(t);
            }
        });

        mainPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
    }

    @FXML
    void openNewProject(ActionEvent event) {
        Stage newWindow = new Stage();
        Scene scene;
        try {
            scene = sceneLoader.getScene("NewProjectScene");
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
            scene = sceneLoader.getScene("AboutScene");
        } catch (Exception e) {
            scene = new Scene(new Label("error"));
        }
        newWindow.setTitle("New Project Initialization");
        newWindow.setScene(scene);
        newWindow.show();
    }
}

