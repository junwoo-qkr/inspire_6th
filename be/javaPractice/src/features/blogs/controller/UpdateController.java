package features.blogs.controller;

import java.util.List;

import features.blogs.domain.dto.BlogRequestDTO;
import features.blogs.domain.dto.BlogResponseDTO;
import features.blogs.service.BlogReactService;

public class UpdateController {
    private BlogReactService service;

    public UpdateController(BlogReactService service) {
        this.service = service;
    }

    public int update(int postId, String title, String content) {
        System.out.println("debug >>>> UpdateController.update()");
        return service.update(BlogRequestDTO.builder().title(title).content(content).id(postId).build());
    }
}
