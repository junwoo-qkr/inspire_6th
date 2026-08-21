package features.blogs.controller;

import java.util.List;

import features.blogs.domain.dto.BlogRequestDTO;
import features.blogs.domain.dto.BlogResponseDTO;
import features.blogs.service.BlogReactService;
import features.blogs.util.ResponseEntity;

public class SearchController {
    private BlogReactService service;

    public SearchController(BlogReactService service) {
        this.service = service;
    }

    public ResponseEntity<List<BlogResponseDTO>> search(String searchParam) {
        System.out.println("debug >>>> SearcgController.search()");
        return new ResponseEntity<List<BlogResponseDTO>>(200, "OK", service.search(BlogRequestDTO.builder().searchParam(searchParam).build()));
    }
}
