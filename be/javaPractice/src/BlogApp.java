import features.blogs.domain.dto.BlogRequestDTO;

public class BlogApp {
    public static void main(String[] args) {
        BlogRequestDTO request1 = new BlogRequestDTO();
        BlogRequestDTO request2 = new BlogRequestDTO(1, "", "", "");
        BlogRequestDTO request3 = BlogRequestDTO.builder()
                                    .title("ABC")
                                    .content("Hi")
                                    .id(3)
                                    .build();
        request3.setEmail("AAA@gmail.com");
        System.out.println(request3.getContent());
    }   
}
