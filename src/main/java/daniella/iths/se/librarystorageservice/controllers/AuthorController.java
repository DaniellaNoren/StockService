package daniella.iths.se.librarystorageservice.controllers;

import daniella.iths.se.librarystorageservice.resources.Author;
import daniella.iths.se.librarystorageservice.resources.Book;
import daniella.iths.se.librarystorageservice.resources.ListOfObject;
import daniella.iths.se.librarystorageservice.storage.AuthorRepository;
import daniella.iths.se.librarystorageservice.storage.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    AuthorRepository authorRepository;

    BookRepository bookRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository, BookRepository bookRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @GetMapping
    public ResponseEntity<?> getAllAuthors(){
        ListOfObject<Author> list = new ListOfObject<>();
        List<Author> authorList = new ArrayList<>();
        authorRepository.findAll().forEach(author -> {authorList.add(author);});
        list.setBookList(authorList);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable long id){
        Optional<Author> aut = authorRepository.findById(id);
        if(aut.isPresent()){
            return ResponseEntity.ok().body(aut.get());
        }
        return ResponseEntity.status(404).body("Author with that id does not exist");
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<?> getBooksByAuthor(@PathVariable long id){
        Optional<Author> aut = authorRepository.findById(id);
        if(aut.isPresent()){
            return ResponseEntity.ok().body(aut.get().getBooks());
        }
        return ResponseEntity.status(404).body("Author with that id does not exist");
    }

    @PostMapping
    public ResponseEntity<?> addAuthor(@RequestBody Author author) throws URISyntaxException {
        Author aut = new Author();
        aut.setLastName(author.getLastName());
        aut.setFirstName(author.getFirstName());
        aut.setBooks(new HashSet<>());
//        if(author.getBooks() != null) {
//            author.getBooks().forEach(book -> {
//                        if (bookRepository.findById(book.getId()).isPresent()) {
//                            Book b = bookRepository.findById(book.getId()).get();
//                            b.addAuthor(aut);
//                            bookRepository.save(b);
//                        } else {
//                            aut.addBook(book);
//                        }
//                    });
//        }
       // authorRepository.save(aut);
        authorRepository.save(aut);
        return ResponseEntity.created(new URI("/books/"+aut.getAuthor_id())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable long id, @RequestBody Author author) throws URISyntaxException {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if(optionalAuthor.isPresent()){
            optionalAuthor.map(author1 -> {
                        if (author.getFirstName() != null)
                            author1.setFirstName(author.getFirstName());
                        if (author.getLastName() != null)
                            author1.setLastName(author.getLastName());
                        if (author.getBooks() != null) {
                            author.getBooks().forEach(book -> {
                                if (bookRepository.findById(book.getId()).isPresent()) {
                                    Book b = bookRepository.findById((book.getId())).get();
                                    b.addAuthor(author1);
                                    b.setLastUpdatedAt(new Date().toString());
                                    bookRepository.save(b);
                                }
                                else {
                                    book.addAuthor(author1);
                                    book.setLastUpdatedAt(new Date().toString());
                                    bookRepository.save(book);
                                }
                            });
                             }
                    return authorRepository.save(author1);
                        });
            return ResponseEntity.created(new URI("/books/"+id)).build();
        }

            return ResponseEntity.notFound().build();
        }

    @DeleteMapping("/delete/{author_id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable long author_id){
        Optional<Author> optionalAuthor = authorRepository.findById(author_id);
        if(optionalAuthor.isPresent()){
            Author a = optionalAuthor.get();
            a.getBooks().forEach(b -> b.removeAuthor(a));
            authorRepository.deleteById(author_id);
            return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body("Author deleted");
        }
        return ResponseEntity.status(404).body("Author not found");
    }

}
