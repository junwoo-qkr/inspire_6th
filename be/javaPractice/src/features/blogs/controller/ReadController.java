package features.blogs.controller;

import features.blogs.domain.dto.BlogResponseDTO;
import features.blogs.service.BlogReactService;

public class ReadController {
    private BlogReactService service;

    public ReadController(BlogReactService service) {
        this.service = service;
    }

    public BlogResponseDTO read(int postId) {
        System.out.println("debug >>>> ReadController.read()");
        return service.read(postId);
    }
}
