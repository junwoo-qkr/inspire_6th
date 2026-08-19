package features.blogs.service;

import java.util.List;

import features.blogs.domain.dto.BlogRequestDTO;
import features.blogs.domain.dto.BlogResponseDTO;

public interface BlogReactService {
    public List<BlogResponseDTO> list();  // 전체 검색
    public BlogResponseDTO read(int postId);  // 게시글 상세보기
    public int insert(BlogRequestDTO request);  // 게시글 입력
    public int update(BlogRequestDTO request);  // 게시글 수정
    public int delete(int postId);  // 게시글 삭제
    public List<BlogResponseDTO> search(BlogRequestDTO request);  // 게시글 검색
}
