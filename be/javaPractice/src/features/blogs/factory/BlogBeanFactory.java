package features.blogs.factory;

import java.util.HashMap;
import java.util.Map;

import features.blogs.controller.DeleteController;
import features.blogs.controller.FileController;
import features.blogs.controller.InsertController;
import features.blogs.controller.ListController;
import features.blogs.controller.ReadController;
import features.blogs.controller.SearchController;
import features.blogs.controller.UpdateController;
import features.blogs.repository.BlogReactDao;
import features.blogs.service.BlogReactSerciveImpl;
import features.blogs.service.BlogReactService;

public class BlogBeanFactory {
    private static BlogBeanFactory instance;
    private Map<String, Object> map;

    // dependency injection
    private BlogReactService service;
    private BlogReactDao dao;

    // 추후 추가될 Controller는 여기서 구현
    private BlogBeanFactory() {
        map = new HashMap<>();
        dao = new BlogReactDao();
        service = new BlogReactSerciveImpl(dao);
        map.put("list", new ListController(service));
        map.put("read", new ReadController(service));
        map.put("search", new SearchController(service));
        map.put("insert", new InsertController(service));
        map.put("delete", new DeleteController(service));
        map.put("update", new UpdateController(service));
        map.put("file", new FileController(service));
    }

    public static BlogBeanFactory getInstance() {
        if(instance == null) {
            instance = new BlogBeanFactory();
        }
        return instance;
    }
    
    // front Controller가 호출하는 메서드 반환하기
    public Object getBean(String endPoint) {
        return map.get(endPoint);
    }
}
