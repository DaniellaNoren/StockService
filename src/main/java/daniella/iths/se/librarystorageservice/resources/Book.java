package daniella.iths.se.librarystorageservice.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;

    private boolean available = true;
    private String returnDate = "None";
    private final String postedAt;
    private String lastUpdatedAt;

    private long user_id;

    @JsonIgnoreProperties("books")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
            )
    private Set<Author> authors;

    public String getPostedAt(){
        return postedAt;
    }

    public String getLastUpdatedAt(){
        return lastUpdatedAt;
    }
    public void setLastUpdatedAt(String lastUpdatedAt){
        if(lastUpdatedAt == null)
            lastUpdatedAt = new Date().toString();
        else
        this.lastUpdatedAt = lastUpdatedAt.toString();
    }

    public Book(){
        postedAt = new Date().toString();
        System.out.println(this.getPostedAt());
        lastUpdatedAt = postedAt;

    }
    public Book(String title){
        this();
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

    public void setAuthors(Set<Author> authors){
        this.authors = authors;
    }

    public Set<Author> getAuthors() {
        return authors;
    }


    public void addAuthor (Author author) {
        if (authors == null)
            authors = new HashSet<>();
        authors.add(author);
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
    }

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

    public long getUser_id(){
        return user_id;
    }

    public void setUser_Id(long user_id){
        this.user_id = user_id;
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

}
