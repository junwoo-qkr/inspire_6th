package features.blogs.domain.dto;

public class BlogResponseDTO {
    private int status ;
    private String message;

    public BlogResponseDTO() {}

    public BlogResponseDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
    
}
