package com.example.testSpring.controller;



import com.example.testSpring.model.Book;
import com.example.testSpring.repository.DbConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/kitab")
public class ReadAllBookList {
    private static Connection connection = DbConnection.getInstance().getConnection();
    private static List<Book> list = null;

    public static void allBookList() {
        list = new ArrayList<>();
        try {
            String allBookQuery = "select * from list_of_book";
            PreparedStatement preparedStatement = connection.prepareStatement(allBookQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String author = resultSet.getString("author");
                String title = resultSet.getString("title");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                list.add(new Book(id, author, title, price, quantity));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        list.forEach(book -> System.out.println(book));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBookList() {
        list = new ArrayList<>();
        try {
            String allBookQuery = "select * from list_of_book";
            PreparedStatement preparedStatement = connection.prepareStatement(allBookQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String author = resultSet.getString("author");
                String title = resultSet.getString("title");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                list.add(new Book(id, author, title, price, quantity));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);




    }
    @PostMapping
    public ResponseEntity<Book> postNewBook(@RequestBody Book book) throws SQLException {
        PreparedStatement preparedStatement;

        String query = "insert into list_of_book(id,author,title,price,quantity) values(?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, book.getId());
        preparedStatement.setString(2, book.getAuthor());
        preparedStatement.setString(3, book.getBookName());
        preparedStatement.setDouble(4, book.getPrice());
        preparedStatement.setInt(5, book.getQuantity());
        preparedStatement.executeUpdate();



     list.add(book);
     return new ResponseEntity<>(book,HttpStatus.CREATED);
    }
}
