package uz.pdp.controller;

import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.dao.ProjectDao;
import uz.pdp.dto.NewProjectDto;
import uz.pdp.model.ProjectEntity;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
@MultipartConfig
public class ProjectController {

    private final ProjectDao projectDao;

    @GetMapping
    public String projectPage(){
        return "project";

    }
    @GetMapping("/newproject")
    public String newProject(){
        return "new-project";
    }


    @PostMapping("/upload")
    public ModelAndView upload(ModelAndView modelAndView,
                               @ModelAttribute NewProjectDto newProjectDto,
                               @RequestPart(value = "uploadedFile", required = false) MultipartFile file,
                               @RequestParam("filename") String newName) throws IOException {

        if (file != null && !file.isEmpty()) {
            String documentsPath = System.getProperty("user.home") + "/Documents/g33/test/";
            String newFileName = newName.isEmpty() ? file.getOriginalFilename() : newName;


            Path filePath = Path.of(documentsPath + newFileName);
            Files.write(filePath, file.getBytes());



            final var projectEntity = ProjectEntity.builder()
                    .projectName(newProjectDto.getProject_name())
                    .projectDeveloper(newProjectDto.getProject_developer())
                    .originalName(newFileName)
                    .build();

            projectDao.save(projectEntity);

            modelAndView.addObject("file", projectEntity);

        } else {
            modelAndView.addObject("error", "No file uploaded");
        }


        modelAndView.setViewName("new-project");
        return modelAndView;
    }

    @GetMapping("/showprojects")
    public ModelAndView showProjects(ModelAndView modelAndView){

        modelAndView.setViewName("show-projects");
        return modelAndView;
    }

}
