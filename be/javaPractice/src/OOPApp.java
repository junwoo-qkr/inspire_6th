import features.oop.service.OOPService;
import features.oop.sub.ManagerDTO;
import features.oop.sub.StudentDTO;
import features.oop.sub.TeacherDTO;
import features.oop.sup.PersonDTO;
import features.oop.util.Flag;

public class OOPApp {
    public static void main(String[] args) {
        // StudentDTO stu = new StudentDTO();
        // stu.setSsn(null);
        // stu.setName(null);
        // stu.setAge(0);
        // stu.setAddress(null);

        // StudentDTO stu2 = new StudentDTO("박준우", 27, null, "2026");
        // System.out.print(stu2.getName() + " ");
        // System.out.print(stu2.getAge() + " ");
        // System.out.print(stu2.getAddress() + " ");
        // System.out.print(stu2.getSsn() + " ");
        // System.out.println();
        // System.out.println();

        // TeacherDTO teacher = new TeacherDTO("박준우", 27, null, "English");
        // System.out.print(teacher.getName() + " ");
        // System.out.print(teacher.getAge() + " ");
        // System.out.print(teacher.getAddress() + " ");
        // System.out.print(teacher.getSubject() + " ");
        // System.out.println();
        // System.out.println();

        // PersonDTO manager = new ManagerDTO("김혜림", 20, "서울", "교육사무국");
        // // System.out.print(manager.getDept());  undefined
        // System.out.print(((ManagerDTO)manager).getDept());

        // PersonDTO[] arr = new PersonDTO[3];
        // arr[0] = new ManagerDTO("김혜림", 20, "서울", "교육사무국");
        // arr[1] = new StudentDTO("박준우", 27, "김해", "2026");
        // arr[2] = new TeacherDTO("문기철", 40, "김해", "science");

        // PersonDTO per01 = arr[3];
        // System.out.println(((TeacherDTO)per01).getSubject());

        // for (int i = 0; i < arr.length; i++) {
        //     PersonDTO temp = arr[i];
        //     if (temp instanceof TeacherDTO) {
        //         System.out.println(((TeacherDTO)temp).getSubject());
        //     } else if (temp instanceof ManagerDTO) {
        //         System.out.println(((ManagerDTO)temp).getDept());
        //     } else if (temp instanceof StudentDTO) {
        //         System.out.println(((StudentDTO)temp).getSsn());
        //     }
        // }

        // for (int i = 0; i < arr.length; i++) {
        //     System.out.println(arr[i].personInfo());
        // }

        ///////////////////////////////
        
        OOPService service = new OOPService();
        // service.addArr(stu);
        // service.addArr(teacher);
        // service.addArr(manager);
        service.makePerson(Flag.STUDENT, "A", 21, "A.address", "A.comm");
        service.makePerson(Flag.TEACHER, "B", 45, "B.address", "B.comm");
        service.makePerson(Flag.MANAGER, "C", 24, "C.address", "C.comm");

        // PersonDTO[] arr2 = service.getArr();
        // for (PersonDTO person : arr2) {
        //     if (person == null) { break; }
        //     System.out.println(person.personInfo());
        // }

        PersonDTO find = service.findPerson("D");
        if (find != null) {
            System.out.println(find.personInfo());
        } else {
            System.out.println("ERROR");
        }
    }
}
