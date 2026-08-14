package features.oop.service;

import features.oop.sub.ManagerDTO;
import features.oop.sub.StudentDTO;
import features.oop.sub.TeacherDTO;
import features.oop.sup.PersonDTO;
import features.oop.util.Flag;
import features.var.Teacher;

public class OOPService {
    private PersonDTO[] arr;
    private int idx;
    
    public OOPService() {
        arr = new PersonDTO[10];
        idx = 0;
    }

    // public void addArr(TeacherDTO teacher) {
    //     arr[idx++] = teacher;
    // }

    // public void addArr(StudentDTO student) {
    //     arr[idx++] = student;
    // }

    // public void addArr(ManagerDTO manager) {
    //     arr[idx++] = manager;
    // }

    public void addArr(PersonDTO person) {
        arr[idx++] = person;
    }

    public PersonDTO[] getArr() {
        return arr;
    }

    public void makePerson(Flag flag, String name, int age, String address, String comm) {
        // System.out.println("flag: " + flag);
        // System.out.println("flag.getFlag(): " + flag.getFlag());
        // switch (flag) {
        //     case STUDENT -> System.out.println("학생");
        //     case MANAGER -> System.out.println("매니저");
        //     case TEACHER -> System.out.println("강사");
        // }
        // switch (flag.getFlag()) {
        //     case 1 -> System.out.println("학생");
        //     case 2 -> System.out.println("매니저");
        //     case 3 -> System.out.println("강사");
        // }

        PersonDTO person = (flag.getFlag() == 1)
        ? StudentDTO.builder().name(name).age(age).address(address).ssn(comm).build()
        : (flag.getFlag() == 2)
        ? TeacherDTO.builder().name(name).age(age).address(address).subject(comm).build()
        : ManagerDTO.builder().name(name).age(age).address(address).dept(comm).build();

        addArr(person);

    }

    public PersonDTO findPerson(String name) {
        for (PersonDTO person : arr) {
            if (person == null) {
                break;
            } else if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }
}
