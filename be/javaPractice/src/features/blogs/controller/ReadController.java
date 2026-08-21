package features.blogs.controller;

import features.blogs.domain.dto.BlogResponseDTO;
import features.blogs.service.BlogReactService;
import features.blogs.util.ResponseEntity;

public class ReadController {
    private BlogReactService service;

    public ReadController(BlogReactService service) {
        this.service = service;
    }

    public ResponseEntity<BlogResponseDTO> read(int postId) {
        System.out.println("debug >>>> ReadController.read()");
        return new ResponseEntity<BlogResponseDTO>(200, "OK", service.read(postId));
    }
}
