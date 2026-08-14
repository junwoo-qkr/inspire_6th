package features.oop.TV;

public class LGTV implements TV {

    private static LGTV instance;

    private LGTV() {

    }

    public static LGTV getInstance() {
        if (instance == null) {
            instance = new LGTV();
        }
        return instance;
    }
    
    @Override
    public void turnON() {
        System.out.println("LGTV ON");
    }
}
