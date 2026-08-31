import java.util.List;

import features.jdbc.MariadbDAO;
import features.jdbc.domain.dto.DepartmentRequestDTO;
import features.jdbc.domain.dto.DepartmentResponseDTO;

public class jdbcApp {
    public static void main(String[] args) {
        MariadbDAO dao = new MariadbDAO();
        List<DepartmentResponseDTO> list = dao.departments();
        list.stream()
            .forEach(System.out::println);

        DepartmentRequestDTO request = DepartmentRequestDTO.builder()
            .dept_id("90")
            .dept_name("교육팀")
            .build();
        int flag = dao.update(request);
        System.out.println(flag == 1 ? "updated" : "not updated");
    }
}
