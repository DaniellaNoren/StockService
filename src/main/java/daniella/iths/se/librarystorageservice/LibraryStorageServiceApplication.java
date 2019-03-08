package daniella.iths.se.librarystorageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class LibraryStorageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryStorageServiceApplication.class, args);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 10);
        System.out.println(c.getTime());


    }

}
