package controllers.controllerHelpers;

import controllers.BookDescriptionController;
import controllers.EditAuthorController;
import controllers.MainController;
import controllers.WorldDescriptionController;
import database.models.Author;
import database.models.Book;
import database.models.BookCharacter;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainDataHolder extends DataHolder {
    Book book;
    MainController context;

    public MainDataHolder(MainController context) {
        this.context = context;
        // book = database.getBookById();
    }

    public TreeItem getTree() {
        TreeItem rootItem = new TreeItem(context.project.getName());
        rootItem.setExpanded(true);

        TreeItem authorsItem = new TreeItem("Authors");
        for (Author a : context.project.getAuthors()) {
            Label auth = new Label(a.getName());
            auth.setOnMouseClicked((MouseEvent e) -> {
                String tabName = a.getName() + " : Author";
                addTab(tabName, getPane(a));
            });
            authorsItem.getChildren().add(new TreeItem(auth));
        }

        rootItem.getChildren().add(authorsItem);

        Label labelBookDescr = new Label("Book description");
        labelBookDescr.setOnMouseClicked((MouseEvent e) ->
            addTab( context.project.getName() +  " : Book description", getBookDescriptionPane()));
        TreeItem desc = new TreeItem(labelBookDescr);

        rootItem.getChildren().add(desc);

        Label worldDesc = new Label("World description");
        worldDesc.setOnMouseClicked((MouseEvent e) ->
                addTab( context.project.getName() +  " : World Description", getWorldDescriptionPane()));
        rootItem.getChildren().add(new TreeItem(worldDesc));
        rootItem.getChildren().add(new TreeItem("Introduction"));

        TreeItem chapterItem = new TreeItem("Chapters");
        chapterItem.getChildren().add(new TreeItem("Chapter 1"));
        chapterItem.getChildren().add(new TreeItem("Chapter 2"));
        chapterItem.getChildren().add(new TreeItem("Chapter 3"));
        rootItem.getChildren().add(chapterItem);

        return rootItem;
    }

    private void addTab(String tabName, Pane pane){
        Tab t = new Tab();
        boolean exist = false;
        for (Tab b : context.mainPane.getTabs()) {
            if (b.getText().equals(tabName)) {
                t = b;
                exist = true;
            }
        }
        if (!exist) {
            t.setText(tabName);
            t.setContent(pane);
            context.mainPane.getTabs().add(t);
        }
        context.mainPane.getSelectionModel().select(t);
    }

    public Pane getPane(Author a){
        BorderPane p = new BorderPane();
        try{
            FXMLLoader l = context.loader.getLoader("EditAuthor");
            BorderPane pane = l.load();
            p = pane;
            EditAuthorController c = l.getController();
            c.init(a, context);
        }catch (IOException e){
            e.printStackTrace();
        }
        return p;
    }

    public Pane getPane(String paneName) {
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

    private Pane getBookDescriptionPane(){
        BorderPane p = new BorderPane();
        try{
            FXMLLoader l = context.loader.getLoader("BookDescription");
            BorderPane pane = l.load();
            p = pane;
            BookDescriptionController c = l.getController();
            c.init(context);
        }catch (IOException e){
            e.printStackTrace();
        }
        return p;
    }

    private Pane getWorldDescriptionPane(){
        BorderPane p = new BorderPane();
        try{
            FXMLLoader l = context.loader.getLoader("WorldDescription");
            BorderPane pane = l.load();
            p = pane;
            WorldDescriptionController c = l.getController();
            c.init(context);
        }catch (IOException e){
            e.printStackTrace();
        }
        return p;
    }
}
