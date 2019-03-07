package daniella.iths.se.librarystorageservice.controllers;

import daniella.iths.se.librarystorageservice.resources.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping()
    public List<Book> getAllBooks(){
        //return all books
        return null;
    }

    @GetMapping("/{id}")
    public Book getOneBook(){
        //return one book based on id
        return null;
    }

    @GetMapping("/{title}")
    public List<Book> getAllBooksBasedOnTitle(){
        //return books with matching title
        return null;
    }

    @PostMapping()
    public void addBook(){
        //add a book
    }

    @DeleteMapping("/{id}")
    public void deleteBook(){
        //delete a book
    }

}
