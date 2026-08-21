package features.blogs.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import features.blogs.domain.dto.BlogRequestDTO;
import features.blogs.domain.dto.BlogResponseDTO;

public class BlogReactDao {
    private List<BlogResponseDTO> posts;

    public BlogReactDao() {
        posts = new ArrayList<>(List.of(
            BlogResponseDTO.builder()
            .postId(1).title("title1").content("Hi").email("AAA@gmail.com").viewCnt(3).build(),
            BlogResponseDTO.builder()
            .postId(2).title("title2").content("Monster Energy Drink").email("BBB@gmail.com").viewCnt(4).build(),
            BlogResponseDTO.builder()
            .postId(3).title("title3").content("Bench Press").email("CCC@gmail.com").viewCnt(1).build(),
            BlogResponseDTO.builder()
            .postId(4).title("title4").content("Fan").email("DDD@gmail.com").viewCnt(12).build()
        ));
    }

    public void setPosts(List<BlogResponseDTO> posts) {
        this.posts = posts;
    }

    public List<BlogResponseDTO> printAll() {
        System.out.println("debug >>>> BlogDao.printAll()");
        return posts;
    }

    public Optional<BlogResponseDTO> printSinglePost(int postId) {
        System.out.println("debug >>>> BlogDao.printSinglePost()");
        Optional<BlogResponseDTO> response = posts.stream().filter(p -> p.getPostId().equals(postId)).findAny();
        return response;
    }

    public List<BlogResponseDTO> search(BlogRequestDTO request) {
        System.out.println("debug >>>> BlogDao.search()");
        List<BlogResponseDTO> response = posts.stream().filter(p -> p.getContent().contains(request.getSearchParam()) || p.getTitle().contains(request.getSearchParam())).collect(Collectors.toList());
        return response;
    }

    public int insert(BlogRequestDTO request) {
        System.out.println("debug >>>> BlogDao.insert()");
        BlogResponseDTO response = BlogRequestDTO.toEntity(request);
        int nextPostId = posts.stream().mapToInt(BlogResponseDTO::getPostId).max().getAsInt() + 1;
        response.setPostId(nextPostId);
        posts.add(response);
        return 1;
    }

    public int delete(int postId) {
        System.out.println("debug >>>> BlogDao.delete()");
        boolean isFlag = posts.removeIf(p -> p.getPostId() == postId);
        return isFlag ? 1 : 0;
    }

    public int update(BlogRequestDTO request) {
        System.out.println("debug >>>> BlogDao.update()");
        return posts.stream()
            .filter(p -> p.getPostId().equals(request.getId()))
            .findAny()
            .map(post -> {
                post.setTitle(request.getTitle());
                post.setContent(request.getContent());
                return 1;
            })
            .orElse(0);
    }
}
