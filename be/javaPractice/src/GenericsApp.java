import java.util.ArrayList;
import java.util.List;

import features.generics.ResponseTemplate;
import features.oop.sub.ManagerDTO;
import features.oop.sub.StudentDTO;
import features.oop.sub.TeacherDTO;
import features.oop.sup.PersonDTO;

public class GenericsApp {
    public static void main(String[] args) {
        StudentDTO student = StudentDTO.builder()
                                .name("Cindy").build();
        TeacherDTO teacher = TeacherDTO.builder()
                                .name("Greg").build();
        ManagerDTO manager = ManagerDTO.builder()
                                .name("Paul").build();

        // DTO 하나만 담는 경우
        System.out.println("DTO 하나만 담는 경우");
        ResponseTemplate<PersonDTO> response = 
            new ResponseTemplate<PersonDTO>(201, "CREATED", student);
        System.out.println(response.getData().personInfo());
        System.out.println();

        /////////////////////////////////

        // DTO 목록을 담는 경우
        System.out.println("DTO 목록을 담는 경우");
        List<PersonDTO> personList = new ArrayList<PersonDTO>();
        // List<? extends PersonDTO> personList = new ArrayList<PersonDTO>();  // 읽기 전용
        // List<? super PersonDTO> personList = new ArrayList<PersonDTO>();  // 쓰기 전용

        personList.add(student);
        personList.add(teacher);
        personList.add(manager);

        ResponseTemplate<List<PersonDTO>> response2 =
            new ResponseTemplate<List<PersonDTO>>(200, "OK", personList);
                    
        List<PersonDTO> lst = response2.getData();
        for (int i = 0; i < lst.size(); i++) {
            PersonDTO person = lst.get(i);
            System.out.println(person.personInfo());
        }
    }
}
