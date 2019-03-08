package daniella.iths.se.librarystorageservice.controllers;


import daniella.iths.se.librarystorageservice.resources.Author;
import daniella.iths.se.librarystorageservice.resources.Book;
import daniella.iths.se.librarystorageservice.resources.ListOfObject;
import daniella.iths.se.librarystorageservice.storage.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping()
    public ResponseEntity<ListOfObject<Book>> getAllBooks(){
        ListOfObject<Book> listOfObject = bookService.getAllBooks();
        return new ResponseEntity(listOfObject, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneBook(@PathVariable("id")long id){
        Optional<Book> book = bookService.getBookById(id);
        if(book.isPresent())
            return new ResponseEntity(bookService.getBookById(id).get(), HttpStatus.OK);
        return new ResponseEntity("didn't find no darn bookin", HttpStatus.NOT_FOUND);

    }

    @PostMapping()
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }

    @PutMapping("/update/{id}/author")
    public ResponseEntity<?> updateAuthorName(@RequestBody Author author, @PathVariable long id){
        if(bookService.getBookById(id).isPresent()) {
            bookService.updateAuthorName(id, author);
            return new ResponseEntity(bookService.getBookById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity("where the book at???? not here", HttpStatus.NOT_FOUND);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateBookTitle(@PathVariable long id, @RequestBody Book book){
      Optional<Book> b = bookService.getBookById(id);
       if(b.isPresent()) {
            bookService.updateBook(id, book);
            return new ResponseEntity(b.get(), HttpStatus.OK);
       }

//            Book bok = b.get();
//            bok.setLastUpdatedAt(new Date());
//            bok.setTitle(book.getTitle());
//            if(bok.getTitle() != null)
//            bookService.addBook(book);
//            if(bok.getReturnDate)
        //else

            return new ResponseEntity("we no found no book :(", HttpStatus.NOT_FOUND);

    }



    @PutMapping("update/{id}/author/{author_id}")
    public ResponseEntity<?> updateAuthors(@PathVariable long id, @PathVariable long author_id, @RequestBody Author... authors){
        return new ResponseEntity("fix this shit later ok", HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }


    @DeleteMapping("/delete")
    public void deleteAll(){
        bookService.deleteAll();
    }
    @DeleteMapping("delete/{id}")
    public void deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
    }


    @GetMapping("/authors")
    public ResponseEntity<ListOfObject<Author>> getAllAuthors(){
        return new ResponseEntity(bookService.getAuthors(), HttpStatus.OK);
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable long id){
        Optional<Author> aut = bookService.getAuthorById(id);
        if(aut.isPresent()){
            return new ResponseEntity(aut.get(), HttpStatus.OK);
        }
        return new ResponseEntity("no author here bitch", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/authors/delete/{id}")
    public void deleteAuthor(@PathVariable long id){
        bookService.deleteAuthor(id);
    }


//
//    @DeleteMapping("/authors/delete")
//    public void deleteAllAuthors(){
//        bookService.deleteAllAuthors();
//    }



}
