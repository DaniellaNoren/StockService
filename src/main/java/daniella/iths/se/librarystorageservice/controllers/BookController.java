package daniella.iths.se.librarystorageservice.controllers;

import daniella.iths.se.librarystorageservice.resources.Book;
import daniella.iths.se.librarystorageservice.resources.ListOfObject;
import daniella.iths.se.librarystorageservice.storage.AuthorRepository;
import daniella.iths.se.librarystorageservice.storage.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookRepository bookRepository;

    private AuthorRepository authorRepository;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping()
    public ResponseEntity<ListOfObject<Book>> getAllBooks() {
        ListOfObject<Book> listOfObject = new ListOfObject<>();
        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().forEach(book -> bookList.add(book));
        listOfObject.setBookList(bookList);
        return new ResponseEntity(listOfObject, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneBook(@PathVariable("id") long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent())
            return ResponseEntity.ok(book.get());
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/authors")
    public ResponseEntity<?> getOneBooksAuthors(@PathVariable("id") long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent())
            return ResponseEntity.ok(book.get().getAuthors());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookRepository.save(book);
    }

//    @PutMapping("/update/{id}/author")
//    public ResponseEntity<?> updateAuthorName(@RequestBody Author author, @PathVariable long id){
//        if(bookService.getBookById(id).isPresent()) {
//            bookService.updateAuthorName(id, author);
//            return new ResponseEntity<Book>(bookService.getBookById(id).get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<String>("where the book at???? not here", HttpStatus.NOT_FOUND);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBookTitle(@PathVariable long id, @RequestBody Book book) {
        Optional<Book> b = bookRepository.findById(id);
        System.out.println(book);
        if (b.isPresent()) {
            b.map(book1 -> {
                if(book.getTitle() != null)
                    book1.setTitle(book.getTitle());
                if(book.getReturnDate() != null)
                    book1.setReturnDate(book.getReturnDate());
                if (book.getAuthors() != null) {
                    book.getAuthors().forEach(author -> {
                        if (authorRepository.findById(author.getAuthor_id()).isPresent())
                            book1.getAuthors().add(authorRepository.findById(author.getAuthor_id()).get());
                        else
                            book1.getAuthors().add(author);
                    });
                }
                book1.setLastUpdatedAt(new Date());
                return bookRepository.save(book1);
            });
            return ResponseEntity.ok(b.get());
        }
        return ResponseEntity.notFound().build();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> partiallyUpdateBook(@PathVariable long id, @RequestBody Book book){
//        Optional<Book> b = bookService.getBookById(id);
//        if(b.isPresent()){
//            bookService.updateBookPartially(id, book);
//            return new ResponseEntity(b.get(), HttpStatus.ACCEPTED);
//        }
//        return new ResponseEntity<String>("noooo", HttpStatus.NOT_FOUND);
//    }

    @PutMapping("borrow/{id}/")
    public ResponseEntity<?> updateAvailability(@PathVariable long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            if (book.get().isAvailable()) {
                book.get().setAvailable(false);
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DATE, 10);
                String date = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + Calendar.DAY_OF_MONTH;

                book.get().setReturnDate(date);
                return ResponseEntity.ok(book.get());
            } else ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();


//            Book bok = b.get();
//            bok.setLastUpdatedAt(new Date());
//            bok.setTitle(book.getTitle());
//            if(bok.getTitle() != null)
//            bookService.addBook(book);
//            if(bok.getReturnDate)
        //else


    }


//    @PutMapping("/{id}/authors/{author_id}")
//    public ResponseEntity<?> updateAuthors(@PathVariable long id, @PathVariable long author_id//, @RequestBody Author... authors
//                                           ){
//        Optional<Book> b = bookRepository.findById(id);
//        if(b.isPresent()){
//            Book book = b.get();
//            Author a = authorRepository.findById(author_id).get();
//            book.addAuthor(a);
//            bookRepository.save(book);
//            authorRepository.save(a);
//            return new ResponseEntity(book, HttpStatus.OK);
//        }
//        else
//            return new ResponseEntity(new BookNotFoundException("Book with that id not found"), HttpStatus.NOT_FOUND);
//    }


    @DeleteMapping("/delete")
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

//
//    @GetMapping("/authors")
//    public ResponseEntity<ListOfObject<Author>> getAllAuthors(){
//        return new ResponseEntity(bookService.getAuthors(), HttpStatus.OK);
//    }
//
//    @GetMapping("/authors/{id}")
//    public ResponseEntity<?> getAuthorById(@PathVariable long id){
//        Optional<Author> aut = bookService.getAuthorById(id);
//        if(aut.isPresent()){
//            return new ResponseEntity(aut.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity("no author here bitch", HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("/authors/delete/{id}")
//    public void deleteAuthor(@PathVariable long id){
//        bookService.deleteAuthor(id);
//    }


//
//    @DeleteMapping("/authors/delete")
//    public void deleteAllAuthors(){
//        bookService.deleteAllAuthors();
//    }

