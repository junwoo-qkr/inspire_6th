package features.oop.factory;

import features.oop.TV.LGTV;
import features.oop.TV.SamSungTV;
import features.oop.TV.TV;

public class BeanFactory {
    private static BeanFactory instance;
    private TV[] arr;

    private BeanFactory() {
        arr = new TV[2];
        arr[0] = SamSungTV.getInstance();
        arr[1] = LGTV.getInstance();
    }

    public static BeanFactory getInstance() {
        if (instance == null) {
            instance = new BeanFactory();
        }
        return instance;
    }

    public TV getBrand(String brandName) {
        return brandName.equalsIgnoreCase("samsung") ? arr[0] : arr[1];
    }
}
