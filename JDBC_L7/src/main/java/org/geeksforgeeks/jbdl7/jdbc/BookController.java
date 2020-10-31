package org.geeksforgeeks.jbdl7.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.BadAttributeValueExpException;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    @GetMapping("/books")
    public List<Book> getBooks() throws SQLException {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(2);
        System.out.println(al);
        return DBManager.getBooks();
    }

    @PostMapping("/book")
    public void createBook(@RequestBody Book book) throws SQLException {
        DBManager.createBook(book);
    }

    @GetMapping("/book")
    public List<Book> getBooksByAuthorName(@QueryParam("author") String author) throws SQLException {
        return DBManager.findBooksByAuthor(author);
    }

    @GetMapping("/book/{author}")
    public List<Book> getBooksByAuthorName2(@PathVariable("author") String author, @RequestParam("table") String table) throws SQLException {
        return DBManager.findBooksByAuthor(author);
    }

    @PostMapping("/create_table")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createTable(@RequestParam("table") String table) throws SQLException {
        DBManager.createTable(table);
    }
}
