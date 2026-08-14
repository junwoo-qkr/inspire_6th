package features.oop.abs;

public abstract class Animal {
    private String name;

    public Animal() {

    }

    public void eat(String food) {
        System.out.println("eat " + food);
    }

    // public abstract void fly();
    // public abstract void takeOff();
    // public abstract void landing();
}
