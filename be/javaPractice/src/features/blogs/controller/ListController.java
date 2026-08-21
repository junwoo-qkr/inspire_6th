package features.blogs.controller;

import java.util.List;

import features.blogs.domain.dto.BlogResponseDTO;
import features.blogs.service.BlogReactService;
import features.blogs.util.ResponseEntity;

public class ListController {
    private BlogReactService service;

    public ListController() {

    }

    public ListController(BlogReactService service) {
        this.service = service;
    }
    
    public ResponseEntity<List<BlogResponseDTO>> list() {
        return new ResponseEntity<List<BlogResponseDTO>>(200, "OK", service.list());
    }
}
