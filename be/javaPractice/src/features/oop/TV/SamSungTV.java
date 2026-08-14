package features.oop.TV;

public class SamSungTV implements TV {

    private static SamSungTV instance;

    private SamSungTV() {

    }

    public static SamSungTV getInstance() {
        if (instance == null) {
            instance = new SamSungTV();
        }
        return instance;
    }

    @Override
    public void turnON() {
        System.out.println("SamSung TV ON");
    }
}
