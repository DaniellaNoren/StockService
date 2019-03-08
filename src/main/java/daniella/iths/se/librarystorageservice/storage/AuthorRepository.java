package daniella.iths.se.librarystorageservice.storage;

import daniella.iths.se.librarystorageservice.resources.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
