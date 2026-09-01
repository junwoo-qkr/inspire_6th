package com.example.demo.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

// 액션 수행 후 페이지 반환 - Full-Browsing 방식
@Controller  // DemoController demoController = new Controller()
@RequestMapping("/demo")
public class DemoController {
    @GetMapping("/index")
    public String getMethodName() {
        System.out.println("endPoint : / index");
        return "index";  // .yml의 prefix, suffix를 앞뒤에 붙여서 불러올 페이지 파일의 경로를 만듦
    }
    
}
