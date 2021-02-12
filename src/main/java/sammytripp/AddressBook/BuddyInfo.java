package sammytripp.AddressBook;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Scanner;

@Entity
public class BuddyInfo implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String telephone;

    /**
     * Default BuddyInfo constructor.
     */
    protected BuddyInfo() {}

    /**
     * BuddyInfo constructor specifying buddy information.
     * @param name String
     * @param address String
     * @param telephone String
     */
    public BuddyInfo(String name, String address, String telephone) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
    }

    public Long getId() { return this.id; }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Name: " + name  + " \tAddress: " + address + " \tTelephone: " + telephone;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Reference equality
        if (!(obj instanceof BuddyInfo)) return false; // Not of class type BuddyInfo
        // Compare individual fields
        BuddyInfo other = (BuddyInfo) obj;
        return name.equals(other.getName()) && address.equals(other.getAddress())
                && telephone.contentEquals(other.getTelephone());
    }

    public static BuddyInfo importBuddy(String text){
        Scanner scanner = new Scanner(text).useDelimiter("\t");
        BuddyInfo buddy = new BuddyInfo(scanner.next(), scanner.next(), scanner.next());
        scanner.close();
        return buddy;
    }
}
