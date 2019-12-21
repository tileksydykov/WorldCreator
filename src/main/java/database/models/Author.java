package database.models;

import database.models.modelhelper.Modelnterface;

public class Author implements Modelnterface {
    private int id = 0;
    private String name = "";
    private String email = "";
    private Book[] books;

    public Author() {}

    public Author(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Author(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }
}
