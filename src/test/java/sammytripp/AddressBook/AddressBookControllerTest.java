package sammytripp.AddressBook;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class AddressBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addressBookHomeAPITest() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().isOk())
            .andExpect(view().name("index"));
    }

    @Test
    public void addBuddyAPITest() throws Exception {
        this.mockMvc.perform(post("/add?name=Buddy&address=Earth&telephone=555 555 5555"))
            .andExpect(status().isCreated());
    }

    @Test
    public void removeBuddyAPITest() throws Exception {
        this.mockMvc.perform(post("/remove?name=Buddy&address=Earth&telephone=555 555 5555"))
                .andExpect(status().isAccepted());
    }
}
