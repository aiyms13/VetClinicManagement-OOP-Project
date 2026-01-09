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

    //override method 1
    @Override
    public void performAction() {
        System.out.println(name + " is going for a walk after treatment.");
    }

    //override method 2
    @Override
    public String getType() {
        return "Dog";
    }

    // new method 1
    public boolean needsTraining() {
        return !trained;
    }

    // new method 2
    public void assistOwner() {
        if (trained) {
            System.out.println("   " + name + " is assisting the owner.");
        } else {
            System.out.println("   " + name + " needs training before assisting.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Trained: " + (trained ? "Yes" : "No");
    }
}
