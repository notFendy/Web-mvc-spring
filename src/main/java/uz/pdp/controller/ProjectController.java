package uz.pdp.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    @GetMapping
    public String projectPage(){
        return "project";
    }
    @GetMapping("/newproject")
    public String newProject(){
        return "new-project";
    }

}
