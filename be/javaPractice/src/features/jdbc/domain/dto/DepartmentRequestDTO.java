package features.jdbc.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class DepartmentRequestDTO {
    private String dept_id, dept_name, loc_id;
}
