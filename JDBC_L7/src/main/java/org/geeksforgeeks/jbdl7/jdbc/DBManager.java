package org.geeksforgeeks.jbdl7.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static Connection connection;

    public static void getDBConnection() throws SQLException{
        try {
            if(connection == null) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "");
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("");
        }
    }

    public static void createTable(String table_name) throws SQLException {

        getDBConnection();

        Statement statement = connection.createStatement();
        boolean result = statement.execute("CREATE TABLE IF NOT EXISTS " + table_name + " (id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(20), author varchar(40), cost int)");

    }

    public static void createBook(Book book) throws SQLException{

        getDBConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book (id, name, author, cost) VALUES (null, ?, ?, ?)");
        preparedStatement.setString(1, book.getName());
        preparedStatement.setString(2, book.getAuthorName());
        preparedStatement.setInt(3, book.getCost());

        int no_of_rows = preparedStatement.executeUpdate();

        if(no_of_rows > 0){
            System.out.println(no_of_rows + " record are inserted successfully ");
        }else{
            System.out.println("unable to insert data " + book);
        }
    }

    public static List<Book> getBooks() throws SQLException{

        getDBConnection();

        List<Book> books = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from book" );


        while(resultSet.next()){

            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String authorName = resultSet.getString(3);
            int cost = resultSet.getInt(4);

            Book book = new Book(id, authorName, name, cost);
            books.add(book);
        }

        return books;
    }

    public static List<Book> findBooksByAuthor(String author) throws SQLException{

        getDBConnection();

        List<Book> books = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from book where author = '" + author + "'");


        while(resultSet.next()){

            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String authorName = resultSet.getString(3);
            int cost = resultSet.getInt(4);

            Book book = new Book(id, name, authorName, cost);
            books.add(book);
        }

        return books;
    }
}
