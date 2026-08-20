package features.blogs.facade;

import java.util.List;

import features.blogs.controller.InsertController;
import features.blogs.controller.ListController;
import features.blogs.controller.ReadController;
import features.blogs.controller.SearchController;
import features.blogs.domain.dto.BlogRequestDTO;
import features.blogs.domain.dto.BlogResponseDTO;
import features.blogs.factory.BlogBeanFactory;

public class BlogFrontController {
    private BlogBeanFactory factory;

    public BlogFrontController() {
        factory = BlogBeanFactory.getInstance();
    }

    public List<BlogResponseDTO> list(String endPoint) {
        System.out.println("debug >>>> BlogFrontController.list, endPoint = " + endPoint);
        Object controller = factory.getBean(endPoint);
        return ((ListController)controller).list();
    }

    public BlogResponseDTO read(String endPoint, int postId) {
        System.out.println("debug >>>> BlogFrontController.list, endPoint = " + endPoint + ", postId = " + postId);
        Object controller = factory.getBean(endPoint);
        return ((ReadController)controller).read(postId);
    }

    public List<BlogResponseDTO> search(String endPoint, String searchParam) {
        System.out.println("debug >>>> BlogFrontController.search, endPoint = " + endPoint + ", searchParam = " + searchParam);
        Object controller = factory.getBean(endPoint);
        return ((SearchController)controller).search(searchParam);
    }

    public int insert(String endPoint, String title, String content, String email) {
        System.out.println("debug >>>> BlogFrontController.insert, endPoint = " + endPoint);
        Object controller = factory.getBean(endPoint);
        return ((InsertController)controller).insert(title, content, email);
    }
}
