public class Cat extends Pet {
    private boolean indoor;

    //constructor with super()
    public Cat(int petID, String name, int age, boolean indoor) {
        super(petID, name, age, "Cat");
        this.indoor = indoor;
    }

    //getter
    public boolean isIndoor() { return indoor; }
    //setter
    public void setIndoor(boolean indoor) { this.indoor = indoor; }

    //override method 1
    @Override
    public void performAction() {
        System.out.println(name + " is resting calmly after the visit.");
    }

    //override method 2
    @Override
    public String getType() {
        return "Cat";
    }

    //new method 1
    public boolean requiresOutdoorCheck() {
        return !indoor;
    }

    //new method 2
    public void scratch() {
        System.out.println("   " + name + " is scratching the furniture!");
    }

    @Override
    public String toString() {
        return super.toString() + " | Indoor: " + (indoor ? "Yes" : "No");
    }
}
