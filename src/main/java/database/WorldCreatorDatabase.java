package database;

import database.models.BookCharacter;

import java.sql.*;

public class WorldCreatorDatabase {
    private final String DB_TEST_URL = "jdbc:sqlite:src/main/resources/databases/WorldCreator.db";
    private final String AUTHOR_TABLE_NAME = "author";
    private final String BOOK_TABLE_NAME = "book";
    private final String CHAPTER_TABLE_NAME = "chapter";
    private final String CHARACTER_TABLE_NAME = "characters";
    private final String BOOK_AUTHOR_TABLE_NAME = "bookauthor";

    public static void main(String[] args) {
        WorldCreatorDatabase w = new WorldCreatorDatabase();

        try {
            w.recreateAllTables();
            System.out.println("all recreated ");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    private void recreateAllTables() throws Exception {
        dropAllTables();
        createTableAuthor();
        createTableBook();
        createTableBookAuthor();
        createTableChapter();
        createTableCharacter();
    }

    private Connection getConnection() throws Exception {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(DB_TEST_URL);
    }

    private void createTableAuthor() throws Exception {
        Connection c = getConnection();
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

    private void createTableBook() throws Exception {
        Connection c = getConnection();
        Statement stmt = c.createStatement();

        String sql = "CREATE TABLE " + BOOK_TABLE_NAME +
                "(id             INTEGER    PRIMARY KEY    AUTOINCREMENT   NOT NULL," +
                " name           CHAR(100)   NOT NULL," +
                " intro          TEXT ," +
                " world          TEXT ," +
                " description    TEXT " +
                ")";
        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

    private void createTableChapter() throws Exception {
        Connection c = getConnection();
        Statement stmt = c.createStatement();

        String sql = "CREATE TABLE " + CHAPTER_TABLE_NAME +
                "(id                  INTEGER    PRIMARY KEY    AUTOINCREMENT   NOT NULL," +
                " title                CHAR(100)   NOT NULL," +
                " body                TEXT ," +
                " book_order          INTEGER," +
                " book_id             INTEGER ," +
                "FOREIGN KEY(book_id) REFERENCES " + BOOK_TABLE_NAME + "(id)" +
                ")";
        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

    private void createTableBookAuthor() throws Exception {
        Connection c = getConnection();
        Statement stmt = c.createStatement();

        String sql = "CREATE TABLE " + BOOK_AUTHOR_TABLE_NAME +
                "(book_id             INTEGER     NOT NULL ," +
                " author_id             INTEGER     NOT NULL," +
                "FOREIGN KEY(book_id) REFERENCES " + BOOK_TABLE_NAME + "(id), " +
                "FOREIGN KEY(author_id) REFERENCES " + AUTHOR_TABLE_NAME + "(id)" +
                ")";
        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

    private void createTableCharacter() throws Exception {
        Connection c = getConnection();
        Statement stmt = c.createStatement();

        String sql = "CREATE TABLE " + CHARACTER_TABLE_NAME +
                "(id             INTEGER    PRIMARY KEY    AUTOINCREMENT   NOT NULL," +
                " name           CHAR(100)   NOT NULL," +
                " history           TEXT ," +
                " book_id         INTEGER ," +
                "FOREIGN KEY(book_id) REFERENCES " + BOOK_TABLE_NAME + "(id)" +
                ")";
        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

    private void dropTable(String tableName) throws Exception {
        Connection c = getConnection();
        Statement stmt = c.createStatement();

        String sql = "DROP TABLE " + tableName;
        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

    private void dropAllTables() throws Exception {
        Connection c = getConnection();
        Statement stmt = c.createStatement();

        String sql = "DROP TABLE " + AUTHOR_TABLE_NAME + ";";
        sql += "DROP TABLE " + BOOK_TABLE_NAME + ";";
        sql += "DROP TABLE " + BOOK_AUTHOR_TABLE_NAME + ";";
        sql += "DROP TABLE " + CHAPTER_TABLE_NAME + ";";
        sql += "DROP TABLE " + CHARACTER_TABLE_NAME + ";";
        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

    

    private void putCharacter(BookCharacter c) {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO " + CHARACTER_TABLE_NAME + " (id, name, history, book_id)" +
                    "VALUES (null , '" + c.getName() + "', '" + c.getHistory() + "', " + c.getBook().getId() + ");";

            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
        }
    }
}
