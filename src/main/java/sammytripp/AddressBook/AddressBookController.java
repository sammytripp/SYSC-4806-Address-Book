package sammytripp.AddressBook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class AddressBookController {

    private final AddressBookRepository repository;

    AddressBookController(AddressBookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String getAddressBook(Model model) {
        Optional<AddressBook> addressBook = repository.findById(1L);
        if (addressBook.isPresent()) {
            AddressBook book = addressBook.get();
            model.addAttribute("buddies", book.toString());
        }
        return "index";
    }

    @GetMapping("/add")
    public String getAddForm(Model model) {
        return "add";
    }

    @GetMapping("/remove")
    public String getRemoveForm(Model model) {
        return "remove";
    }

    @PostMapping("/add")
    public void addBuddy(@RequestParam(name="name") String name,
                         @RequestParam(name="address") String address,
                         @RequestParam(name="telephone") String telephone) {

        Optional<AddressBook> addressBook = repository.findById(1L);
        if (addressBook.isPresent()) {
            AddressBook book = addressBook.get();
            book.addBuddy(new BuddyInfo(name, address, telephone));
            repository.save(book);
        }
    }

    @PostMapping("/remove")
    public void removeBuddy(@RequestBody Map<String, String> body) {

        Optional<AddressBook> addressBook = repository.findById(1L);
        if (addressBook.isPresent()) {
            AddressBook book = addressBook.get();
            book.removeBuddy(new BuddyInfo(body.get("name"), body.get("address"), body.get("telephone")));
            repository.save(book);
        }
    }
}
