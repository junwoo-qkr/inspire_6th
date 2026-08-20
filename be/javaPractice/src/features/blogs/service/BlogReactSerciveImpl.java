package features.blogs.service;

import java.util.List;
import java.util.Optional;

import features.blogs.domain.dto.BlogRequestDTO;
import features.blogs.domain.dto.BlogResponseDTO;
import features.blogs.repository.BlogReactDao;

public class BlogReactSerciveImpl implements BlogReactService {
    private BlogReactDao dao;

    public BlogReactSerciveImpl() {

    }

    public BlogReactSerciveImpl(BlogReactDao dao) {
        this.dao = dao;
    }

    @Override
    public List<BlogResponseDTO> list() {
        System.out.println("debug >>>> Blog Service: list()");
        return dao.printAll();
    }

    @Override
    public BlogResponseDTO read(int postId) {
        System.out.println("debug >>>> Blog Service: read(), postId = " + postId);
        // return dao.printSinglePost(postId).orElse(null);
        // return dao.printSinglePost(postId).orElseThrow(() -> new RuntimeException("No post found : postId = " + postId));
        Optional<BlogResponseDTO> optional = dao.printSinglePost(postId);
        if (!optional.isPresent()) {
            return null;
        }
        return optional.get();
    }

    @Override
    public int insert(BlogRequestDTO request) {
        System.out.println("debug >>>> Blog Service: insert()");
        return dao.insert(request);
    }

    @Override
    public int update(BlogRequestDTO request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(int postId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<BlogResponseDTO> search(BlogRequestDTO request) {
        System.out.println("debug >>>> Blog Service: search()");
        List<BlogResponseDTO> response = dao.search(request);
        return response;
    }
    
}