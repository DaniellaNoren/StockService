package daniella.iths.se.librarystorageservice.exceptions;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String msg){
        super(msg);
    }
}
