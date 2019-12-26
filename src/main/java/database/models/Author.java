package database.models;

import java.util.Arrays;

public class Author extends Model {
    private Integer id = 0;
    private String name = "";
    private String email = "";
    private Book[] books;

    public Author() {
        id = generateId();
    }

    public Author(String name, String email) {
        id = generateId();
        this.name = name;
        this.email = email;
    }

    public Author(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public Integer getId() {
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

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", books=" + Arrays.toString(books) +
                '}';
    }
}
