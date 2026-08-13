package features.blogs.repository;

import features.blogs.domain.dto.BlogResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BlogRepository {
    private BlogResponseDTO[] list = new BlogResponseDTO[10];

    public BlogResponseDTO[] blogs() {
        System.out.println("BlogRepository.blogs()");
        return list;
    }
    
}
