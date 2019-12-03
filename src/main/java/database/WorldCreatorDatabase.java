package database;

import java.sql.*;

public class WorldCreatorDatabase {
    private final String DB_TEST_URL = "jdbc:sqlite:src/main/resources/databases/WorldCreator.db";
    private final String AUTHOR_TABLE_NAME = "author";
    private final String BOOK_TABLE_NAME = "book";
    private final String CHAPTER_TABLE_NAME = "chapter";
    private final String BOOK_AUTHOR_TABLE_NAME = "bookauthor";

    public static void main(String[] args) {
        WorldCreatorDatabase w =  new WorldCreatorDatabase();


    }

    private Connection getConnection() throws Exception {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(DB_TEST_URL);
    }

    private void createTableAuthor() throws Exception{
        Connection c = null;
        Statement stmt = c.createStatement();

        String sql = "CREATE TABLE " + AUTHOR_TABLE_NAME +
                "(id             INTEGER    PRIMARY KEY    AUTOINCREMENT   NOT NULL," +
                " name           CHAR(50)   NOT NULL," +
                " surname        CHAR(50)  ," +
                " email          CHAR(50) " +
                ")";
        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

    private void createTableBook() throws Exception{
        Connection c = null;
        Statement stmt = c.createStatement();

        String sql = "CREATE TABLE " + BOOK_TABLE_NAME +
                "(id             INTEGER    PRIMARY KEY    AUTOINCREMENT   NOT NULL," +
                " name           CHAR(100)   NOT NULL," +
                " intro          TEXT ," +
                " world          TEXT ," +
                " description    TEXT " +
                ")" ;
        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

    private void createTableChapter() throws Exception{
        Connection c = null;
        Statement stmt = c.createStatement();

        String sql = "CREATE TABLE " + CHAPTER_TABLE_NAME +
                "(id             INTEGER    PRIMARY KEY    AUTOINCREMENT   NOT NULL," +
                " name           CHAR(100)   NOT NULL," +
                " body           TEXT ," +
                " book_id         INTEGER ," +
                "FOREIGN KEY(book_id) REFERENCES "+ BOOK_TABLE_NAME +"(id)" +
                ")" ;
        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

    private void createTableBookAuthor() throws Exception{
        Connection c = null;
        Statement stmt = c.createStatement();

        String sql = "CREATE TABLE " + CHAPTER_TABLE_NAME +
                "(book_id             INTEGER    PRIMARY KEY    AUTOINCREMENT   NOT NULL," +
                " author_id             INTEGER    PRIMARY KEY    AUTOINCREMENT   NOT NULL," +
                "FOREIGN KEY(book_id) REFERENCES "+ BOOK_TABLE_NAME +"(id), " +
                "FOREIGN KEY(author_id) REFERENCES "+ AUTHOR_TABLE_NAME +"(id)" +
                ")" ;
        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

    private void dropAuthorTable() throws Exception{
        Connection c = null;
        Statement stmt = c.createStatement();

        String sql = "DROP TABLE " + AUTHOR_TABLE_NAME;
        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

}
