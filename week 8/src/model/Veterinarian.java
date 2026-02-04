package model;

public class Veterinarian implements TreatsPets {
    private int vetID;
    private String name;
    private String specialization;
    private int experienceYears;

    // Parameterized constructor
    public Veterinarian(int vetID, String name, String specialization, int experienceYears) {
        setVetID(vetID);
        setName(name);
        setSpecialization(specialization);
        setExperienceYears(experienceYears);
    }

    //getters
    public int getVetID() { return vetID; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public int getExperienceYears() { return experienceYears; }

    //setters with validation
    public void setVetID(int vetID) {
        if (vetID <= 0) {
            throw new IllegalArgumentException("Vet ID must be positive");
        }
        this.vetID = vetID;
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (name.length() < 2) {
            throw new IllegalArgumentException("Name must be at least 2 characters");
        }
        this.name = name;
    }
    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be empty");
        }
        this.specialization = specialization;
    }
    public void setExperienceYears(int experienceYears) {
        if (experienceYears < 0) {
            throw new IllegalArgumentException("ExperienceYears cannot be negative");
        }
        this.experienceYears = experienceYears;
    }

    @Override
    public void treat(Pet pet) {
        System.out.println("🩺 Treating pet: " + pet.getName());
        System.out.println("   Pet ID: " + pet.getPetID());
        System.out.println("   Age: " + pet.getAge());

        System.out.println("💉 Performing medical examination...");
        pet.performAction();

        System.out.println("✅ Treatment completed for " + pet.getName());
    }

    @Override
    public String getTreatmentPlan(Pet pet) {
        return "- Checkup\n- Vaccination\n- Observation";
    }

    //methods
    public boolean isExperienced() {
        return experienceYears >= 5;
    }
    public boolean canTreat(String species) {
        return specialization.equals(species);
    }

    @Override
    public String toString() {
        return "Veterinarian{" +
                "vetID=" + vetID +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", experienceYears= " + experienceYears + " years" +
                '}';
    }

}
