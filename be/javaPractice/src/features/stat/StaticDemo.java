package features.stat;

public class StaticDemo {
    public String message = "그냥 변수";
    public static String staticMessage = "static 변수";
    public static final double PI = 3.14;

    public StaticDemo() {}

    public void nonStaticMethod() {
        System.out.println(message);
        System.out.println(staticMessage);
        System.out.println(PI);
    }

    public static void statMethod() {
        // System.out.println(message);
        System.out.println(new StaticDemo().message);
        System.out.println(staticMessage);
        System.out.println(PI);
    }
}
