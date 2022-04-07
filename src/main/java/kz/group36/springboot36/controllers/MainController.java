package kz.group36.springboot36.controllers;

import kz.group36.springboot36.model.Project;
import kz.group36.springboot36.model.Task;
import kz.group36.springboot36.sevices.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {

    private final ProjectService projectService;

    @GetMapping(value = "/")
    private String index(Model model){
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "index";
    }

    @GetMapping(value = "/addproject")
    public String addProjectPage(Model model){
        return "addproject";
    }

    @PostMapping(value = "/addproject")
    public String addProject(@RequestParam(name = "name") String name,
                          @RequestParam(name = "startDate") Date startDate,
                          @RequestParam(name = "completionDate") Date completionDate,
                          @RequestParam(name = "status") String status){
        Project project = new Project();
        project.setName(name);
        project.setStartDate(startDate);
        project.setCompletionDate(completionDate);
        project.setStatus(status);
        project.setPriority(0);

        projectService.addProject(project);

        return "redirect:/";
    }

    @GetMapping(value = "/projectdetails/{id}.html")
    public String readProject(Model model, @PathVariable(name = "id") Long id){
        Project project = projectService.getProject(id);
        List<Task> tasks = projectService.getTaskByProjectId(id);
        model.addAttribute("tasks", tasks);
        model.addAttribute("project", project);
        return "projectdetails";
    }

    @PostMapping(value = "/saveproject")
    public String saveProject(@RequestParam(name = "id") Long id,
                           @RequestParam(name = "name") String name,
                           @RequestParam(name = "startDate") Date startDate,
                           @RequestParam(name = "completionDate") Date completionDate,
                           @RequestParam(name = "status") String status){

        Project project = projectService.getProject(id);
        if (project != null){
            project.setName(name);
            project.setStartDate(startDate);
            project.setCompletionDate(completionDate);
            project.setStatus(status);
            project.setPriority(projectService.getProject(id).getPriority());
            projectService.saveProject(project);
            return "redirect:/projectdetails/" + id + ".html";
        }
        return "redirect:/";
    }

    @PostMapping(value = "/savetask")
    public String saveTask(@RequestParam(name = "id") Long id,
                           @RequestParam(name = "name") String name,
                           @RequestParam(name = "status") String status,
                           @RequestParam(name = "description") String description,
                           @RequestParam(name = "priority") int priority){
        Task task = projectService.getTask(id);
        if(task != null){
            task.setTaskName(name);
            task.setStatus(status);
            task.setTaskDescription(description);
            task.setPriorityTask(priority);
            projectService.saveTask(task);
            return "redirect:/";
        }
        return "redirect:/";
    }

    @PostMapping(value = "/deleteproject")
    public String saveTask(@RequestParam(name = "id") Long id){

        projectService.deleteProject(id);
        return "redirect:/";
    }

    @GetMapping(value = "/projectdetails/{id}/addtask.html")
    public String addTaskPage(Model model,
                          @PathVariable(name = "id") Long id){
        Project project = projectService.getProject(id);
        model.addAttribute("project", project);
        return "/addtask";
    }

    @PostMapping(value = "/projectdetails/{id}/addtask")
    public String addTask(Model model,
                          @RequestParam(name = "projectId") Long projectId,
                          @RequestParam(name = "name") String name,
                          @RequestParam(name = "status") String status,
                          @RequestParam(name = "description") String description,
                          @RequestParam(name = "priority") int priority){
        Project project = projectService.getProject(projectId);
        if (project != null) {
            Task task = new Task();
            task.setTaskName(name);
            task.setStatus(status);
            task.setTaskDescription(description);
            task.setPriorityTask(priority);
            task.setProject(project);
            projectService.addTask(task);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/taskdetails/{id}.html")
    public String readTask(Model model, @PathVariable(name = "id") Long id){
        Task task = projectService.getTask(id);
        model.addAttribute("task", task);
        return "taskdetails";
    }

    @PostMapping(value = "/deletetask")
    public String deleteTask(@RequestParam(name = "id") Long id){
        projectService.deleteTask(id);
        return "redirect:/";
    }
}
