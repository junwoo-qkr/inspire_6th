import features.car.Car;

public class CarApp {
    public static void main(String[] args) {
        Car car = new Car();
        // car.brand = "Audi";
        // System.out.println(car.brand);
        car.setBrand("Audi");
        System.out.println(car.getBrand());

        Car car2 = new Car("BMW");
        // System.out.println(car2.brand);
        System.out.println(car2.getBrand());

        Car car3 = new Car("Benz", "C200");
        // System.out.println(car3.brand + car3.model);
        System.out.println(car3.getBrand());
        System.out.println(car3.getModel());
        String carinfo = car3.carInfo();
        System.out.println(carinfo);
    }
}
