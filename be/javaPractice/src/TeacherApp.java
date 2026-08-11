import features.var.Teacher;

public class TeacherApp {
    public static void main(String[] args) {
        // new 연산자로 instance 생성 가능
        Teacher teacher = new Teacher();
        System.out.println("teacher = " + teacher);

        // 인스턴스의 메서드 접근
        teacher.setName("김영석");
        String name = teacher.getName();
        System.out.println(name);

        // 인스턴스의 변수 접근
        teacher.job = "화학 선생님";
        String job = teacher.getJob();
        System.out.println(job);
    }    
}
