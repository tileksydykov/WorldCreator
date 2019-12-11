package controllers.controllerHelpers;

import database.models.Book;
import database.models.BookCharacter;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainDataHolder extends DataHolder {
    Book book;

    public MainDataHolder(){
        // book = database.getBookById();
    }

    public TreeItem getTree() {
        TreeItem rootItem = new TreeItem("BookName");

        TreeItem authorsItem = new TreeItem("Authors");
        authorsItem.getChildren().add(new TreeItem("FirstAuthor"));
        rootItem.getChildren().add(authorsItem);

        rootItem.getChildren().add(new TreeItem("Book description"));
        rootItem.getChildren().add(new TreeItem("World description"));
        rootItem.getChildren().add(new TreeItem("Introduction"));

        TreeItem chapterItem = new TreeItem("Chapters");
        chapterItem.getChildren().add(new TreeItem("Chapter 1"));
        chapterItem.getChildren().add(new TreeItem("Chapter 2"));
        chapterItem.getChildren().add(new TreeItem("Chapter 3"));
        rootItem.getChildren().add(chapterItem);

        return rootItem;
    }

    public Pane getPane(String paneName){
        TextArea textArea = new TextArea();
        BorderPane p = new BorderPane();
        p.setCenter(textArea);
        return p;
    }

    public void saveCharacter(String name) {
        try {
            database.putCharacter(new BookCharacter("", "", book));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
