package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") //localhost8080이면 이 페이지가 호출됨ㅇㅇ
    public String home(){
        return "home";
    }
}
