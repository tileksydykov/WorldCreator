package database.models;

import database.models.modelhelper.Modelnterface;

public class BookCharacter implements Modelnterface {
    private int id = 0;
    private String name = "";
    private String history = "";
    private int bookId = 0;
    private Book book;

    public BookCharacter(){}

    public BookCharacter( String name, String history, Book book) {
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
    public int getId() {
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
