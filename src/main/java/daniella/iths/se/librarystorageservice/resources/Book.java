package daniella.iths.se.librarystorageservice.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    //@Temporal(TemporalType.TIMESTAMP)
    private String postedAt = new Date().toString();

    private String lastUpdatedAt;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
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

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    //    public ListOfObject<Author> getAuthors() {
//        return authors;
//    }
//
//    public void setAuthors(ListOfObject<Author> authors) {
//        this.authors = authors;
//    }
}
