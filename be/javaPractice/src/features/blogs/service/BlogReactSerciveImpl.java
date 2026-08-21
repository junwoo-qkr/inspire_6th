package features.blogs.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Optional;

import features.blogs.domain.dto.BlogRequestDTO;
import features.blogs.domain.dto.BlogResponseDTO;
import features.blogs.repository.BlogReactDao;
import features.blogs.util.ResponseEntity;

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
        System.out.println("debug >>>> Blog Service: update(), postId = " + postId);
        int response = dao.update(request);
        return response;
    }

    @Override
    public int delete(int postId) {
        System.out.println("debug >>>> Blog Service: delete(), postId = " + postId);
        int response = dao.delete(postId);
        return response;
    }

    @Override
    public List<BlogResponseDTO> search(BlogRequestDTO request) {
        System.out.println("debug >>>> Blog Service: search()");
        List<BlogResponseDTO> response = dao.search(request);
        return response;
    }

    @Override
    public boolean save() {
        System.out.println("debug >>>> Blog Service: save()");
        String path = "./blogs.txt";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)))) {
            oos.writeObject(dao.printAll());
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean load() {
        String path = "./blogs.txt";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)))) {
            dao.setPosts((List<BlogResponseDTO>)ois.readObject());
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}