package sammytripp.AddressBook;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.io.*;

@Entity
public class AddressBook implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private List<BuddyInfo> buddyList;

    protected AddressBook() { }

    public AddressBook(List<BuddyInfo> buddyList) {
        this.buddyList = buddyList;
    }

    public void addBuddy(BuddyInfo buddy) {
        this.buddyList.add(buddy);
    }

    public void removeBuddy(BuddyInfo buddy) {
        if (this.buddyList.isEmpty() || !this.buddyList.contains(buddy)) return;
        this.buddyList.remove(buddy);
    }

    public void clearBuddyList() {
        this.buddyList = new LinkedList<>();
    }

    @Id
    @GeneratedValue
    public Long getId() { return this.id; }

    public void setId(Long id) { this.id = id; }

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    public List<BuddyInfo> getBuddyList() {
        return this.buddyList;
    }

    public void setBuddyList(List<BuddyInfo> buddyList) {
        this.buddyList = buddyList;
    }

    public void save(String fileName) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(fileName);
        for (BuddyInfo buddy : this.buddyList){ // For all BuddyInfo objects
            out.println(buddy.toString());
        }
        out.close();
    }

    public void importAddressBook(String fileName) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        if (input.ready()) {
            String line;
            while((line = input.readLine()) != null) {
                this.addBuddy(BuddyInfo.importBuddy(line));
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (BuddyInfo buddy : buddyList) {
            s.append(buddy.toString()).append("\n");
        }
        return s.toString();
    }

}
