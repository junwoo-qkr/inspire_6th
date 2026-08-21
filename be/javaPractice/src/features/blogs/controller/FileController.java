package features.blogs.controller;

import features.blogs.service.BlogReactService;

public class FileController {
    private BlogReactService service;

    public FileController(BlogReactService service) {
        this.service = service;
    }

    public boolean save() {
        System.out.println("debug >>>> FileController.save()");
        return service.save();
    }

    public boolean load() {
        System.out.println("debug >>>> FileController.load()");
        return service.load();
    }
}
