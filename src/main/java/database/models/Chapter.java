package database.models;

import database.models.modelhelper.Modelnterface;

public class Chapter implements Modelnterface {
    private int id;
    private Book book;
    private String title;
    private String body;
    private int bookOrder;

    public Chapter(){}

    public Chapter(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Chapter(Book book, String title, String body) {
        this.book = book;
        this.title = title;
        this.body = body;
    }

    public Chapter(Book book, String title, String body, int bookOrder) {
        this.book = book;
        this.title = title;
        this.body = body;
        this.bookOrder = bookOrder;
    }

    public Chapter(int id, Book book, String title, String body, int bookOrder) {
        this.id = id;
        this.book = book;
        this.title = title;
        this.body = body;
        this.bookOrder = bookOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getBookOrder() {
        return bookOrder;
    }

    public void setBookOrder(int bookOrder) {
        this.bookOrder = bookOrder;
    }
}
