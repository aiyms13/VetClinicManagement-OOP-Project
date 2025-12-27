public class Owner {
    private int ownerID;
    private String name;
    private String phone;
    private int numberOfPets;

    // Parameterized constructor
    public Owner(int ownerID, String name, String phone, int numberOfPets) {
        this.ownerID = ownerID;
        this.name = name;
        this.phone = phone;
        this.numberOfPets = numberOfPets;
    }
    // Default constructor
    public Owner() {
        this.ownerID = 0;
        this.name = "Unknown";
        this.phone = "N/A";
        this.numberOfPets = 0;
    }

    // Methods
    public void addPet() {
        numberOfPets++;
    }
    public int getNumberOfPets() {
        return numberOfPets;
    }

    public boolean isFrequentClient() {
        return numberOfPets >= 3;
    }

    public int getOwnerID() {
        return ownerID;
    }
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getNumberOfPets(int numberOfPets) {
        return numberOfPets;
    }
    public void setNumberOfPets(int numberOfPets) {
        this.numberOfPets = numberOfPets;
    }

    @Override
    public String toString() {
        return "Owner{ownerID=" + ownerID +
                ", name=" + name +
                ", phone=" + phone +
                ", numberOfPets=" + numberOfPets + "}";
    }
}
