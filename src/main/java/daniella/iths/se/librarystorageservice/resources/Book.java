package daniella.iths.se.librarystorageservice.resources;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;

    //@Value("true")
    private boolean available = true;
    //@Value("none")
    private String returnDate = "None";
    //@Temporal(TemporalType.TIMESTAMP)
    private final String postedAt = new Date().toString();

    private String lastUpdatedAt;

    @JsonIgnoreProperties("books")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
            )
    private Set<Author> authors;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "last_updated_at")
//    private Date lastUpdatedAt = new Date();

//    public Date getLastUpdatedAt() {
//        return lastUpdatedAt;
//    }
//
//    public void setLastUpdatedAt(Date lastUpdatedAt) {
//        this.lastUpdatedAt = lastUpdatedAt;
//    }

    public String getPostedAt(){
        return postedAt;
    }

//    public void setPostedAt(){
//        this.postedAt = new Date().toString();
//    }

    public String getLastUpdatedAt(){
        return lastUpdatedAt;
    }
    public void setLastUpdatedAt(Date date){
        this.lastUpdatedAt = date.toString();
    }



    //    @ManyToMany
//    private ListOfObject<Author> authors;

    public Book(){

    }
    public Book(String title){
        this.title = title;
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

    public Set<Author> getAuthors() {
        return authors;
    }


    public void addAuthor (Author author) {
        authors.add(author);
//        author.getBooks().add(this);
    }

//    public void removeAuthor(Author author){
//        authors.remove(author);
//        //author.getBooks().remove(this);
//    }



    //    public ListOfObject<Author> getAuthors() {
//        return authors;
//    }
//
//    public void setAuthors(ListOfObject<Author> authors) {
//        this.authors = authors;
//    }


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", postedAt='" + postedAt + '\'' +
                ", lastUpdatedAt='" + lastUpdatedAt + '\'' +
                ", authors=" + authors +
                '}';
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
    }
}
