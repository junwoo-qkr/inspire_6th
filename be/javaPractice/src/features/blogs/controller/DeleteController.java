package features.blogs.controller;

import features.blogs.service.BlogReactService;

public class DeleteController {
    private BlogReactService service;

    public DeleteController(BlogReactService service) {
        this.service = service;
    }

    public int delete(int postId) {
        System.out.println("debug >>>> ReadController.read()");
        return service.delete(postId);
    }
}
