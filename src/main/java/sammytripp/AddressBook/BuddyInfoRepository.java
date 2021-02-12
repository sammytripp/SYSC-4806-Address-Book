package sammytripp.AddressBook;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {

    List<BuddyInfo> findByName(String name);
    List<BuddyInfo> findByAddress(String address);
    List<BuddyInfo> findByTelephone(String telephone);
    Optional<BuddyInfo> findById(Long id);
}
