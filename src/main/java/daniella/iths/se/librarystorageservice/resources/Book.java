package daniella.iths.se.librarystorageservice.resources;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;

@Entity
public class Book {

    @Id
    private long id;
    private String title;
//    @ManyToMany
//    private ListOfObject<Author> authors;

    public Book(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public ListOfObject<Author> getAuthors() {
//        return authors;
//    }
//
//    public void setAuthors(ListOfObject<Author> authors) {
//        this.authors = authors;
//    }
}
