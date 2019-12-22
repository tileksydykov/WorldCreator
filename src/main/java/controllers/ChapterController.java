package controllers;

import database.models.Chapter;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChapterController extends ControllerBase {
    MainController parent;
    Chapter chapter;

    public void init(Chapter c, MainController context) {
        parent = context;
        chapter = c;
    }

    @FXML
    private TextField titleField;

    @FXML
    private TextArea textArea;

}
