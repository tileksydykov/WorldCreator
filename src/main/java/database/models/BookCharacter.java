package database.models;

import database.models.modelhelper.Model;
import database.models.modelhelper.ModelInterface;

public class BookCharacter extends Model {
    private int id = 0;
    private String name = "";
    private String history = "";
    private String relation = "";
    private int bookId = 0;
    private Book book;

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public BookCharacter(){
        id = generateId();
    }

    public BookCharacter(String name, String history, String relation) {
        id = generateId();
        this.name = name;
        this.history = history;
        this.relation = relation;
    }

    public BookCharacter(String name, String history, Book book) {
        id = generateId();
        this.name = name;
        this.history = history;
        this.book = book;
    }

    public BookCharacter(int id, String name, String history, Book book) {
        this.id = id;
        this.name = name;
        this.history = history;
        this.book = book;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


}
