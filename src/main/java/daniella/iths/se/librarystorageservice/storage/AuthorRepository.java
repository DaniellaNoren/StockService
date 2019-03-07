package daniella.iths.se.librarystorageservice.storage;

import daniella.iths.se.librarystorageservice.resources.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
