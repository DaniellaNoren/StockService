package daniella.iths.se.librarystorageservice.controllers;

import daniella.iths.se.librarystorageservice.resources.Author;
import daniella.iths.se.librarystorageservice.resources.Book;
import daniella.iths.se.librarystorageservice.resources.ListOfObject;
import daniella.iths.se.librarystorageservice.storage.AuthorRepository;
import daniella.iths.se.librarystorageservice.storage.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable long id) throws URISyntaxException {
        if(authorRepository.findById(id).isPresent()) {
            Author author = authorRepository.findById(id).get();
            for(Book b : author.getBooks()){
                b.removeAuthor(author);
            }
            authorRepository.deleteById(id);
            return ResponseEntity.ok().location(new URI("/books")).build();
        }
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> addAuthor(@RequestBody Author author) throws URISyntaxException {
        authorRepository.save(author);
        return ResponseEntity.created(new URI("/books/"+author.getAuthor_id())).build();
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
                                if (bookRepository.findById(book.getId()).isPresent())
                                    bookRepository.findById(book.getId()).get().getAuthors().add(author1);
                                    //author1.getBooks().add(bookRepository.findById(book.getId()).get());
                                else {

                                   // author1.getBooks().add(book);
                                    book.addAuthor(author1);
                                    book.setLastUpdatedAt(new Date());
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

//    @GetMapping()
//    public ListOfObject<Author> getAll(){
//        return authorService.getAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getOneAuthor(@PathVariable long id){
//        if(authorService.getAuthorById(id).isPresent())
//            return new ResponseEntity(authorService.getAuthorById(id).get(), HttpStatus.OK);
//        return new ResponseEntity("no no no author found", HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("/delete")
//    public void deleteAll(){
//        authorService.deleteAll();
//    }
//
//    @PostMapping()
//    public void addAuthor(@RequestBody Author author){
//        System.out.println(author.getFirstName());
//        authorService.addAuthor(author);
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> updateAuthorName(@RequestBody Author author, @PathVariable long id){
//        if(authorService.getAuthorById(id).isPresent()) {
//            authorService.updateAuthor(id, author);
//            return new ResponseEntity(authorService.getAuthorById(id).get(), HttpStatus.OK);
//        }
//        return new ResponseEntity("where the author at???? not here!!", HttpStatus.NOT_FOUND);
//    }
//}
}
