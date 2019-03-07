package daniella.iths.se.librarystorageservice.resources;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


public class Author {

    private long id;
    private String firstName;
    private String lastName;
    private ListOfObject<Book> books;

    public Author(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public ListOfObject<Book> getBooks() {
        return books;
    }

    public void setBooks(ListOfObject<Book> books) {
        this.books = books;
    }
}
