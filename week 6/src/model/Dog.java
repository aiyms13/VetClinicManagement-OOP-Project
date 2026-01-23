package model;

public class Dog extends Pet {
    private boolean trained;

    //constructor with super()
    public Dog(int petID, String name, int age, boolean trained) {
        super(petID, name, age, "Dog");
        this.trained = trained;
    }

    //getter
    public boolean isTrained() { return trained; }
    //setter
    public void setTrained(boolean trained) { this.trained = trained; }

    //abstract method from Staff 1
    @Override
    public void performAction() {
        System.out.println(name + " is going for a walk after treatment.🐕👣");
    }

    //abstract method from Staff 2
    @Override
    public String getType() {
        return "Dog";
    }

    // Dog-specific methods
    public boolean needsTraining() {
        return !trained;
    }

    // Dog-specific methods
    public void assistOwner() {
        if (trained) {
            System.out.println("   " + name + " is assisting the owner.🦮🚶‍♀️");
        } else {
            System.out.println("   " + name + " needs training before assisting.🐕‍🦺❗");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Trained: " + (trained ? "Yes✅" : "No❌");
    }
}
