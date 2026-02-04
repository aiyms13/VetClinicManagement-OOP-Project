package model;

public class Cat extends Pet {
    private boolean indoor;

    //constructor with super()
    public Cat(int petID, String name, int age, String species, boolean indoor) {
        super(petID, name, age, species);
        setIndoor(indoor);
    }

    //getter
    public boolean isIndoor() { return indoor; }
    //setter
    public void setIndoor(boolean indoor) { this.indoor = indoor; }

    //abstract method from Staff 1
    @Override
    public void performAction() {
        System.out.println(name + " is resting calmly after the visit.😴💤");
    }

    //abstract method from Staff 2
    @Override
    public String getType() {
        return "Cat";
    }

    //Cat-specific methods
    public boolean requiresOutdoorCheck() {
        return !indoor;
    }

    //Cat-specific methods
    public void scratch() {
        System.out.println("   " + name + " is scratching the furniture!🙀🛋️");
    }

    @Override
    public String toString() {
        return super.toString() + " | Indoor: " + (indoor ? "Yes✅" : "No❌");
    }
}
