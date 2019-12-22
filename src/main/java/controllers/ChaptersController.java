package controllers;

import database.models.Chapter;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ChaptersController extends ControllerBase {
    MainController parent;

    @FXML
    private ListView<Label> list;

    @FXML
    private TextField titleField;

    @FXML
    void add(ActionEvent event) {
        String title = titleField.getText();
        if(title.isEmpty()){
            return;
        }
        titleField.setText("");

        Chapter chapter = new Chapter();
        chapter.setTitle(title);

        parent.project.addChapter(chapter);
        parent.save();
        parent.updateUI();

        Label t = new Label(title);
        list.getItems().add(list.getItems().size(), t);
        list.scrollTo(list.getItems().size() - 1);
    }

    public void init(MainController context) {
        parent = context;
        for (Chapter c: parent.project.getChapters()) {
            Label t = new Label(c.getTitle());
            list.getItems().add(list.getItems().size(), t);
            list.scrollTo(list.getItems().size() - 1);
        }
    }
}
