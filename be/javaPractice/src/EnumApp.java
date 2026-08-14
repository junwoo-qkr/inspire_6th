import features.oop.util.Flag;

public class EnumApp {
    public static void main(String[] args) {
        Flag flag = Flag.STUDENT;
        System.out.println(flag);
        System.out.println(flag.getFlag());

        switch (flag) {
            case STUDENT:
                System.out.println("학생");
                break;
            case MANAGER:
                System.out.println("매니저");
                break;
            case TEACHER:
                System.out.println("강사");
                break;
        }

        System.out.println(flag == Flag.STUDENT);
    }
}
