public class Owner {
    private int ownerID;
    private String name;
    private String phone;
    private int numberOfPets;

    // Parameterized constructor
    public Owner(int ownerID, String name, String phone, int numberOfPets) {
        this.ownerID = ownerID;
        setName(name);
        setPhone(phone);
        this.numberOfPets = numberOfPets;
    }
    // Default constructor
    public Owner() {
        this.ownerID = 0;
        this.name = "Unknown";
        this.phone = "N/A";
        this.numberOfPets = 0;
    }

    //getters
    public int getOwnerID() { return ownerID; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public int getNumberOfPets() { return numberOfPets; }

    //setters

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Warning: Name cannot be empty!");
        }
    }
    public void setPhone(String phone) {
        if (phone != null && !phone.trim().isEmpty()) {
            this.phone = phone;
        } else {
            System.out.println("Warning: Phone cannot be empty!");
        }
    }
    public void setNumberOfPets(int numberOfPets) {
        this.numberOfPets = numberOfPets;
    }

    //methods
    public void addPet() {
        numberOfPets++;
    }
    public boolean isFrequentClient() {
        return numberOfPets >= 3;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerID=" + ownerID +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", numberOfPets=" + numberOfPets +
                '}';
    }
}
