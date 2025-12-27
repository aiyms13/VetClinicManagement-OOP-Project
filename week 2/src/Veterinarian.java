public class Veterinarian {
    private int vetID;
    private String name;
    private String specialization;
    private int experienceYears;

    // Parameterized constructor
    public Veterinarian(int vetID, String name, String specialization, int experienceYears) {
        this.vetID = vetID;
        this.name = name;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
    }
    // Default constructor
    public Veterinarian() {
        this.vetID = 0;
        this.name = "Unknown";
        this.specialization = "General";
        this.experienceYears = 0;
    }

    // Methods
    public boolean isExperienced() {
        return experienceYears >= 5;
    }

    public boolean canTreat(String species) {
        return specialization.equals(species);
    }

    public int getVetID() {
        return vetID;
    }
    public void setVetID(int vetID) {
        this.vetID = vetID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperienceYears() {
        return experienceYears;
    }
    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    @Override
    public String toString() {
        return "Veterinarian{vetID=" + vetID +
                ", name=" + name +
                ", specialization=" + specialization +
                ", experienceYears=" + experienceYears + "}";
    }
}
