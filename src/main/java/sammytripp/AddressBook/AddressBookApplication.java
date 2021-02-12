package sammytripp.AddressBook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@SpringBootApplication
public class AddressBookApplication {

	private static final Logger log = LoggerFactory.getLogger(AddressBookApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AddressBookApplication.class, args);
	}

	@Bean
	public CommandLineRunner addressBookInit(AddressBookRepository repository) {
		return (args) -> {

			// save address book
			List<BuddyInfo> buddyList = new LinkedList<>();
			//buddyList.add(new BuddyInfo("Thomas", "Toronto", "111 111 1111"));
			repository.save(new AddressBook(buddyList));

			// fetch address books
			log.info("Address books found with findAdd():");
			log.info("-----------------------------------");
			for (AddressBook b : repository.findAll()) {
				log.info("Address book ID: " + b.getId());
				log.info(b.toString());
			}
			log.info("");
		};
	}

	@Bean
	public CommandLineRunner buddyDemo(BuddyInfoRepository repository) {
		return (args) -> {
			// save buddies
			repository.save(new BuddyInfo("Samantha", "Old Ottawa South", "777 777 7777"));
			repository.save(new BuddyInfo("Amrit", "H-Block", "555 555 5555"));
			repository.save(new BuddyInfo("Olive", "Nepean", "333 333 3333"));

			// fetch buddies
			log.info("Buddies found with findAll():");
			log.info("-----------------------------");
			for (BuddyInfo buddy : repository.findAll()) {
				log.info(buddy.toString());
			}
			log.info("");

			// fetch an individual buddy by ID
			Optional<BuddyInfo> buddy = repository.findById(1L);
			log.info("Buddy found with findById(1L):");
			log.info("------------------------------");
			log.info(buddy.toString());
			log.info("");

			// fetch buddies by address
			log.info("Buddies found with findByAddress('H-Block'):");
			log.info("--------------------------------------------");
			repository.findByAddress("H-Block").forEach(bud -> log.info(bud.toString()));
			log.info("");
		};
	}



}
