package daniella.iths.se.librarystorageservice.controllers;


import daniella.iths.se.librarystorageservice.resources.Author;
import daniella.iths.se.librarystorageservice.resources.Book;
import daniella.iths.se.librarystorageservice.resources.ListOfObject;
import daniella.iths.se.librarystorageservice.storage.AuthorService;
import daniella.iths.se.librarystorageservice.storage.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;


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
//        book.getAuthors().forEach(author -> {
//            Author a = au.findById(author.getAuthor_id()).get();
//            System.out.println(a);
//            if(a != null) {
//                author.setFirstName(a.getFirstName());
//                author.setLastName(a.getLastName());
//                a.getBooks().add(book);
//            }else
//                throw new BookNotFoundException("author not found");
//                });
//        book.getAuthors().forEach(System.out::println);

        bookService.addBook(book);


    }

    @PutMapping("/{id}/author")
    public void addAuthor(@RequestBody Author author, @PathVariable long id){
        Author a = authorService.getAuthorById(author.getAuthor_id());
        if(a != null)
            bookService.addAuthor(a, id);


    }

    @PutMapping("update/{id}")
    public void updateBook(@PathVariable long id, @RequestBody Book book){
        bookService.updateBookTitle(id, book.getTitle());
    }

    @DeleteMapping("delete/{id}")
    public void deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
    }

    @DeleteMapping("/delete")
    public void deleteAll(){
        bookService.deleteAll();
    }

}
