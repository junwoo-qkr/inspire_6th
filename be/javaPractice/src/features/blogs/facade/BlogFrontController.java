package features.blogs.facade;

import java.util.List;

import features.blogs.controller.DeleteController;
import features.blogs.controller.FileController;
import features.blogs.controller.InsertController;
import features.blogs.controller.ListController;
import features.blogs.controller.ReadController;
import features.blogs.controller.SearchController;
import features.blogs.controller.UpdateController;
import features.blogs.domain.dto.BlogRequestDTO;
import features.blogs.domain.dto.BlogResponseDTO;
import features.blogs.factory.BlogBeanFactory;
import features.blogs.util.ResponseEntity;

public class BlogFrontController {
    private BlogBeanFactory factory;

    public BlogFrontController() {
        factory = BlogBeanFactory.getInstance();
    }

    public ResponseEntity<List<BlogResponseDTO>> list(String endPoint) {
        System.out.println("debug >>>> BlogFrontController.list, endPoint = " + endPoint);
        Object controller = factory.getBean(endPoint);
        return ((ListController)controller).list();
    }

    public ResponseEntity<BlogResponseDTO> read(String endPoint, int postId) {
        System.out.println("debug >>>> BlogFrontController.list, endPoint = " + endPoint + ", postId = " + postId);
        Object controller = factory.getBean(endPoint);
        return ((ReadController)controller).read(postId);
    }

    public ResponseEntity<List<BlogResponseDTO>> search(String endPoint, String searchParam) {
        System.out.println("debug >>>> BlogFrontController.search, endPoint = " + endPoint + ", searchParam = " + searchParam);
        Object controller = factory.getBean(endPoint);
        return ((SearchController)controller).search(searchParam);
    }

    public int insert(String endPoint, String title, String content, String email) {
        System.out.println("debug >>>> BlogFrontController.insert, endPoint = " + endPoint);
        Object controller = factory.getBean(endPoint);
        return ((InsertController)controller).insert(title, content, email);
    }

    public int delete(String endPoint, int postId) {
        System.out.println("debug >>>> BlogFrontController.delete, endPoint = " + endPoint + ", postId = " + postId);
        Object controller = factory.getBean(endPoint);
        return ((DeleteController)controller).delete(postId);
    }

    public int update(String endPoint, int postId, String title, String content) {
        System.out.println("debug >>>> BlogFrontController.update, endPoint = " + endPoint);
        Object controller = factory.getBean(endPoint);
        return ((UpdateController)controller).update(postId, title, content);
    }

    public boolean file(String endPoint, String action) {
        System.out.println("debug >>>> BlogFrontController.file, endPoint = " + endPoint + ", action = " + action);
        FileController controller = (FileController)factory.getBean(endPoint);
        if (action.equals("save")) {
            return controller.save();
        } else {
            return controller.load();
        }
    }
}
