package daniella.iths.se.librarystorageservice.controllers;

import daniella.iths.se.librarystorageservice.resources.Book;
import daniella.iths.se.librarystorageservice.resources.ListOfObject;
import daniella.iths.se.librarystorageservice.resources.User;
import daniella.iths.se.librarystorageservice.storage.AuthorRepository;
import daniella.iths.se.librarystorageservice.storage.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookRepository bookRepository;

    private AuthorRepository authorRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping
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

    @GetMapping("{id}/users")
    public ResponseEntity<?> getOneBooksUsers(@PathVariable long id) throws URISyntaxException {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            Book b = book.get();
            if(b.getUser_id() > 0) {
                User user = restTemplate.getForEntity(new URI("http://user-service/get-user/id/"+id), User.class).getBody();
                return ResponseEntity.status(200).body(user);
            }
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookRepository.save(book);
    }

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

    @PutMapping("/borrow/{id}")
    public ResponseEntity<?> updateAvailability(@PathVariable long id, @RequestBody long user_id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Book b = book.get();
            if (b.isAvailable()) {
                b.setAvailable(false);
                b.setUser_Id(user_id);
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DATE, 10);
                String date = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + Calendar.DAY_OF_MONTH;
                b.setReturnDate(date);
                bookRepository.save(b);
                return ResponseEntity.ok(b);

            } else { return new ResponseEntity<String>("Book not available", HttpStatus.NOT_FOUND);}
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/return/{id}")
    public ResponseEntity<?> returnBook(@PathVariable long id){
        Optional<Book> b = bookRepository.findById(id);
        if(b.isPresent()){
            Book book = b.get();
                if(!book.isAvailable()) {
                    book.setAvailable(true);
                    book.setUser_Id(0);
                    book.setReturnDate("None");
                    bookRepository.save(book);
                    return ResponseEntity.ok(book);
                }else
                    return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(404).body("Book with that ID does not exist");
    }


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

