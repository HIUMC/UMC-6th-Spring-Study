package toy_project.hongik_hospital;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class MainController {
    @GetMapping("main")
    public String main(Model model) {
        model.addAttribute("data", "도도");
        return "main";
    }
}
