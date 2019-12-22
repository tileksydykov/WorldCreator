package controllers;

import database.models.Chapter;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.fxmisc.richtext.StyleClassedTextArea;

import java.util.Date;

public class ChapterController extends ControllerBase {
    private MainController parent;
    private Chapter chapter;
    long time;

    @FXML
    private BorderPane Board;

    @FXML
    private TextField titleField;



    public void init(Chapter c, MainController context) {
        parent = context;
        chapter = c;


        StyleClassedTextArea textArea = new StyleClassedTextArea();
        textArea.setWrapText(true);

        Board.setCenter(textArea);

        textArea.replaceText(c.getBody());
        titleField.setText(c.getTitle());
        textArea.textProperty().addListener((obs, old, niu) -> {
            chapter.setBody(textArea.getText());
            safeSave();
        });
        titleField.textProperty().addListener((obs, old, niu) -> {
            chapter.setTitle(titleField.getText());
            safeSave();
        });
    }

    private void safeSave(){
        save();
    }

    private void save() {
        Date d = new Date();
        long t = d.getTime();
        if (t - time > 1000) {
            parent.fileSystem.updateChapter(chapter);
            time = t;
        }
    }
}
