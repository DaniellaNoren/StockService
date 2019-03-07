package daniella.iths.se.librarystorageservice.resources;

import java.util.List;

public class ListOfObject<T> {

    private List<T> bookList;

    public ListOfObject(){

    }

    public List<T> getBookList() {
        return bookList;
    }

    public void setBookList(List<T> bookList) {
        this.bookList = bookList;
    }
}
