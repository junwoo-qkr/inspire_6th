package features.blogs.controller;

import java.util.List;

import features.blogs.domain.dto.BlogRequestDTO;
import features.blogs.domain.dto.BlogResponseDTO;
import features.blogs.service.BlogReactService;
import features.blogs.util.ResponseEntity;

public class InsertController {
    private BlogReactService service;

    public InsertController(BlogReactService service) {
        this.service = service;
    }

    public int insert(String title, String content, String email) {
        System.out.println("debug >>>> InsertController.insert()");
        return service.insert(BlogRequestDTO.builder().title(title).content(content).email(email).build());
    }
}
