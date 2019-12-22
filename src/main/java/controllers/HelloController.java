package controllers;

import filesystem.models.ProjectFile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloController extends ControllerBase {

    @FXML
    private Label OpenProjects;

    @FXML
    private ListView list;

    @FXML
    void Close(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        for (ProjectFile proj : fileSystem.getProjects()) {
            GridPane p = new GridPane();

            p.setOnMouseClicked(new EventHandler<MouseEvent>() {
                ProjectFile p = proj;

                @Override
                public void handle(MouseEvent event) {
                    Node source = (Node) event.getSource();
                    Stage s = (Stage) source.getScene().getWindow();
                    s.close();
                    Stage stage = new Stage();
                    try {
                        stage.setMaximized(true);
                        stage.setTitle(p.getName());
                        FXMLLoader l = loader.getLoader("MainScene");
                        stage.setScene(new Scene(l.load()));
                        MainController c = l.getController();
                        c.initProject(p.getName());
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            Label l1 = new Label();
            l1.setText(proj.getName());
            l1.setFont(Font.font(20.0));
            p.add(l1, 1, 1);
            Label l2 = new Label();
            l2.setText(cropLocation(proj.getLocation()));
            l2.setTextFill(Paint.valueOf("#acacac"));
            l2.setTooltip(new Tooltip(proj.getLocation()));
            p.add(l2, 1, 2);
            list.getItems().add(p);
        }
    }

    public String cropLocation(String s) {
        int length = s.length();
        if (length > 30) {
            return s.substring(0, 15) + "..." + s.substring(length - 15, length - 1);
        } else {
            return s;
        }
    }

    @FXML
    void CreateNewProject(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        try {
            stage.setScene(loader.getScene("NewProjectScene"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}