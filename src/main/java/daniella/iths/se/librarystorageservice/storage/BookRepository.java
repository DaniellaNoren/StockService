package daniella.iths.se.librarystorageservice.storage;

import daniella.iths.se.librarystorageservice.resources.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {

}
