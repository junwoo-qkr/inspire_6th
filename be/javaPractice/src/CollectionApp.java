import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import features.oop.sub.ManagerDTO;
import features.oop.sub.StudentDTO;
import features.oop.sub.TeacherDTO;
import features.oop.sup.PersonDTO;

public class CollectionApp {
    public static void main(String[] args) {
        System.out.println("일반 배열");
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(arr.length);
        System.out.println(Arrays.toString(arr));
        System.out.println();

        /////////////////////////////////
        
        System.out.println("List 실습1");
        List<String> list = new ArrayList<>();
        list.add("HI");

        // list.add(new Integer(10));
        // list.add(10);
        // list.add(true);

        System.out.println("list.size() = " + list.size());
        System.out.println("list = " + list);
        for (int i = 0; i < list.size(); i++) {
            String element = list.get(i);
            System.out.println(i + "번째 요소(list.get(i)) = " + element);
        }
        System.out.println();

        /////////////////////////////////
        
        System.out.println("List에 객체 담기");
        List<PersonDTO> personList = new ArrayList<>();
        StudentDTO student = StudentDTO.builder()
                                    .name("student").build();
        TeacherDTO teacher = TeacherDTO.builder()
                                    .name("teacher").build();
        ManagerDTO managerDTO = ManagerDTO.builder()
                                    .name("manager").build();
        personList.add(student);
        personList.add(teacher);
        personList.add(managerDTO);

        /////////////////////////////////
        
        System.out.println("명령형 처리와 선언적 처리");
        List<String> filterdList = new ArrayList<>();

        // 명령형 처리
        for (int i = 0; i < personList.size(); i++) {
            PersonDTO person = personList.get(i);
            if (person.getName().startsWith("s")) {
                System.out.println(person.personInfo());
                filterdList.add(person.getName().toUpperCase());
            }
        }
        System.out.println();

        // Stream API (선언적 처리)
        // Collection을 Stream 객체로 바인딩 - 중간연산(chaining) - 연산 결과를 최종 연산(하나의 연산)
        List<String> filterdList2 = personList.stream()
                                        .filter(s -> s.getName().startsWith("s"))
                                        .map(s -> s.getName().toUpperCase())
                                        .collect(Collectors.toList());

        System.out.println(filterdList2);
        System.out.println();

        personList.stream()
            .filter(person -> person.getName().startsWith("t"))
            .forEach(person -> System.out.println(person.personInfo()));
        System.out.println();

        /////////////////////////////////
        
        // Set 실습
        System.out.println("Set 실습");
        Set<String> set = new HashSet<>();
        set.add("John");
        set.add("Bobby");
        set.add("Tom");
        set.add("John");
        set.add("Britney");
        set.add("Adam");
        System.out.println("set = " + set);

        Object[] setArr = set.toArray();
        for(Object data : setArr) {
            System.out.println("set의 요소 = " + data);
        }

        /////////////////////////////////
        
        // Map 실습
        List<StudentDTO> studentList = new ArrayList<StudentDTO>();
        List<TeacherDTO> teacherList = new ArrayList<TeacherDTO>();
        List<ManagerDTO> managerList = new ArrayList<ManagerDTO>();
        Map<String, List<? extends PersonDTO>> map = new HashMap<>();

        map.put("student", studentList);
        map.put("teacher", teacherList);
        map.put("manager", managerList);

        List<? extends PersonDTO> mapList = map.get("manager");
        mapList.forEach(person -> System.out.println(person.personInfo()));

        // map.get("student")
        // .stream()
        // .filter(null)
        // .map(null)
        // .forEach(person -> System.out.println(person.personInfo()));

    
    }
}
