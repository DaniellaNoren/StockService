//package daniella.iths.se.librarystorageservice.storage;
//
//import daniella.iths.se.librarystorageservice.resources.Author;
//import daniella.iths.se.librarystorageservice.resources.ListOfObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class AuthorService {
//
//    @Autowired
//    private AuthorRepository authorRepository;
//
//    public ListOfObject<Author> getAll(){
//        List<Author> list = new ArrayList<>();
//        authorRepository.findAll().forEach(author -> { list.add(author);
//            System.out.println(author.getFirstName());});
//        ListOfObject<Author> authorList = new ListOfObject<>();
//        authorList.setBookList(list);
//        return authorList;
//    }
//
//    public void addAuthor(Author author) {
//        System.out.println(author.getFirstName());
//        authorRepository.saveAndFlush(author);
//    }
//    public Optional<Author> getAuthorById(long id){
//        return authorRepository.findById(id);
//    }
//
//    public void deleteAll() {
//        authorRepository.deleteAll();
//    }
//
//    public void updateAuthor(long id, Author author) {
//    }
//}
