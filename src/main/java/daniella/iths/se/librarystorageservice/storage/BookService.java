package daniella.iths.se.librarystorageservice.storage;

import daniella.iths.se.librarystorageservice.resources.Author;
import daniella.iths.se.librarystorageservice.resources.Book;
import daniella.iths.se.librarystorageservice.resources.ListOfObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {


    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    public Optional<Book> getBookById(long id){
        return bookRepository.findById(id);
    }

    public ListOfObject<Book> getAllBooks(){
        List<Book> listOfBooks = new ArrayList<>();
        ListOfObject<Book> list = new ListOfObject<>();
        bookRepository.findAll().forEach(book -> { listOfBooks.add(book); book.getAuthors().forEach(e -> System.out.println(e.getFirstName()));});
        list.setBookList(listOfBooks);
        return list;
    }

    public void addBook(Book b){
        b.setLastUpdatedAt(new Date());

        bookRepository.save(b);

    }

    public void deleteBook(long id){
        bookRepository.deleteById(id);
    }

    public void updateBookTitle(long id, String title){
        Book b = bookRepository.findById(id).get();
        b.setTitle(title);
        b.setLastUpdatedAt(new Date());
        bookRepository.saveAndFlush(b);
    }

    public void updateAuthor(long id, Author author){
        Optional<Author> a = authorRepository.findById(id);
        if(a.isPresent()){
            Author aut = a.get();
            aut.setFirstName(author.getFirstName());
            aut.setLastName(author.getLastName());
        }

    }

    public void removeAuthorsBooks(long author_id){
        Optional<Author> a = authorRepository.findById(author_id);
        if(a.isPresent()){
            Author aut = a.get();

        }
    }


    public void deleteAll() {
        bookRepository.deleteAll();
    }

    public ListOfObject<Author> getAuthors(){
        ListOfObject<Author> auts = new ListOfObject<>();
        auts.setBookList(authorRepository.findAll());
        return auts;
    }
    public void addAuthor(Author author, long id) {

        //System.out.println(author);

        Book b = bookRepository.findById(id).get();
        if(b != null) {
            //author.addBook(b);
            //b.addAuthor(author);
            bookRepository.save(b);
            // authorRepository.save(author);
            System.out.println(b);
            System.out.println(author);
        }


    }

    public void updateBook(long id, Book book) {
        book.setLastUpdatedAt(new Date());
        bookRepository.saveAndFlush(book);
    }

    public void deleteAllAuthors() {
        authorRepository.deleteAll();
    }

    public void updateAuthorName(long id, Author author) {
        Author a = authorRepository.findById(id).get();
        a.setFirstName(author.getFirstName());
        a.setLastName(author.getLastName());
        authorRepository.save(a);
    }


    public void deleteAuthor(long id) {
       Author author = authorRepository.findById(id).get();
       for(Book b : author.getBooks()){
           b.removeAuthor(author);
       }
            authorRepository.deleteById(id);
    }

    public Optional<Author> getAuthorById(long author_id) {
        return authorRepository.findById(author_id);
    }
}
