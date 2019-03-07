package daniella.iths.se.librarystorageservice.controllers;

import daniella.iths.se.librarystorageservice.resources.Book;
import daniella.iths.se.librarystorageservice.resources.ListOfObject;
import daniella.iths.se.librarystorageservice.storage.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping()
    public ListOfObject<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getOneBook(@PathVariable("id")long id){
        return bookService.getBookById(id);
    }

//    @GetMapping("/{title}")
//    public ListOfObject<Book> getAllBooksBasedOnTitle(@PathVariable String title){
//        //return books with matching title
//        return null;
//    }

    @PostMapping()
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(){
        //delete a book
    }

}
