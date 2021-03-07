package sammytripp.AddressBook;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel="addresses", path="addresses")
public interface AddressBookRepository extends PagingAndSortingRepository<AddressBook, Long> {

    Optional<AddressBook> findById(@Param("id") Long id);
}