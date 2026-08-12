public class StringApp {
    public static void main(String[] args) {
        String str01 = "AAA";
        String str02 = "AAA";

        if (str01 == str02) {
            System.out.println("OK");
        } else {
            System.out.println("FAIL");
        }
    

        String str03 = new String("AAA");
        String str04 = new String("AAA");

        if (str03 == str04) {
            System.out.println("OK");
        } else {
            System.out.println("FAIL");
        }

        if (str03.equals(str04)) {
            System.out.println("OK");
        } else {
            System.out.println("FAIL");
        }
    }
}
