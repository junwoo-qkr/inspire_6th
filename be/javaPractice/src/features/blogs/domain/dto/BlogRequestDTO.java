package features.blogs.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class BlogRequestDTO {
    private Integer id;
    private String title;
    private String content;
    private String email;
    private String searchParam;

    public static BlogResponseDTO toEntity(BlogRequestDTO request) {
        return BlogResponseDTO.builder()
            .title(request.getTitle())
            .content(request.getContent())
            .email(request.getEmail())
            .build();
    }
}
