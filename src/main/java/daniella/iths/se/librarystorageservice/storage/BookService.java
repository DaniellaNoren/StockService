package daniella.iths.se.librarystorageservice.storage;

import daniella.iths.se.librarystorageservice.exceptions.BookNotFoundException;
import daniella.iths.se.librarystorageservice.resources.Book;
import daniella.iths.se.librarystorageservice.resources.ListOfObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {



    @Autowired
    private BookRepository bookRepository;

    public Book getBookById(long id){
        Optional<Book> op = bookRepository.findById(id);
        if(!op.isPresent())
            throw new BookNotFoundException("No book with that Id exists");
        return op.get();
    }

    public ListOfObject<Book> getAllBooks(){
        List<Book> listOfBooks = new ArrayList<>();
        ListOfObject<Book> list = new ListOfObject<>();
        bookRepository.findAll().forEach(book -> listOfBooks.add(book));
        list.setBookList(listOfBooks);
        return list;
    }

    public void addBook(Book b){
        b.setLastUpdatedAt(new Date());
        bookRepository.saveAndFlush(b);

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


    public void deleteAll() {
        bookRepository.deleteAll();
    }
}
