import features.oop.TV.LGTV;
import features.oop.TV.SamSungTV;
import features.oop.TV.TV;
import features.oop.factory.BeanFactory;

public class TVClientApp {
    public static void main(String[] args) {
        // tv1, tv2의 주소가 같음
        TV tv1 = SamSungTV.getInstance();
        System.out.println("address of tv1: " + tv1);

        TV tv2 = SamSungTV.getInstance();
        System.out.println("address of tv2: " + tv2);


        BeanFactory factory = BeanFactory.getInstance();
        TV tv3 = factory.getBrand("samsung");
        tv3.turnON();

        TV tv4 = factory.getBrand("LG");
        tv4.turnON();
    }
}
