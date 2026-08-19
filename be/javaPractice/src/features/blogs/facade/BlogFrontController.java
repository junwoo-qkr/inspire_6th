package features.blogs.facade;

import java.util.List;

import features.blogs.controller.ListController;
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
}
