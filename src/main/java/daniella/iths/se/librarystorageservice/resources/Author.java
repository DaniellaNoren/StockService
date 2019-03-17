package daniella.iths.se.librarystorageservice.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Author {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long author_id;
    private String firstName;
    private String lastName;

//    private boolean available = true;
//    private String returnDate;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("authors")
    private Set<Book> books;



    public Author(){

    }


    @Override
    public String toString() {
        return "Author{" +
                "author_id=" + author_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", books=" + books +
                '}';
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books){
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }


    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    @PreRemove
//    private void removeBook(){
//        for(Book b : books){
//            b.removeAuthor(this);
//        }
//    }

}
