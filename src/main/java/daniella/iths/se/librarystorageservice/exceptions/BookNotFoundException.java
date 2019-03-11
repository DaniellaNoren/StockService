package daniella.iths.se.librarystorageservice.exceptions;

import java.util.function.Supplier;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String msg){
        super(msg);
    }

}
