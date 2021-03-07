package sammytripp.AddressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AddressBookController {


    private AddressBookRepositoryService service;

    @Autowired
    AddressBookController(AddressBookRepositoryService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getAddressBook(Model model) {
        Optional<AddressBook> addressBook = service.findById(1L);
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
    public ResponseEntity addBuddy(@RequestParam(name="name") String name,
                         @RequestParam(name="address") String address,
                         @RequestParam(name="telephone") String telephone) {

        Optional<AddressBook> addressBook = service.findById(1L);
        if (addressBook.isPresent()) {
            AddressBook book = addressBook.get();
            book.addBuddy(new BuddyInfo(name, address, telephone));
            service.save(book);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/remove")
    public ResponseEntity removeBuddy(@RequestParam(name="name") String name,
                                      @RequestParam(name="address") String address,
                                      @RequestParam(name="telephone") String telephone) {

        Optional<AddressBook> addressBook = service.findById(1L);
        if (addressBook.isPresent()) {
            AddressBook book = addressBook.get();
            book.removeBuddy(new BuddyInfo(name, address, telephone));
            service.save(book);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
