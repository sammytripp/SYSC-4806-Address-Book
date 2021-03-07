package sammytripp.AddressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressBookRepositoryService implements AddressBookRepository {

    @Qualifier("addressBookRepository")

    @Autowired
    private AddressBookRepository repository;

    @Override
    public Optional<AddressBook> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Iterable<AddressBook> findAll() {
        return repository.findAll();
    }

    @Override
    public Iterable<AddressBook> findAllById(Iterable<Long> longs) {
        return repository.findAllById(longs);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public <S extends AddressBook> S save(S entity) {
        return repository.save(entity);
    }

    @Override
    public <S extends AddressBook> Iterable<S> saveAll(Iterable<S> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(AddressBook entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends AddressBook> entities) {
        repository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Iterable<AddressBook> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<AddressBook> findAll(Pageable pageable) {
        return null;
    }
}
