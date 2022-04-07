package kz.group36.springboot36.sevices;

import kz.group36.springboot36.model.Project;
import kz.group36.springboot36.model.Task;
import kz.group36.springboot36.repositories.ProjectRepository;
import kz.group36.springboot36.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public Project addProject(Project project){
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public Project getProject(Long id){
        return projectRepository.findById(id).orElse(null);
    }

    public Project saveProject(Project project){
        return projectRepository.save(project);
    }

    public void deleteProject(Long id){
        projectRepository.deleteById(id);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public List<Task> getTaskByProjectId(Long id){
        return taskRepository.findAllByProjectId(id);
    }

    public Task addTask(Task task){
        return taskRepository.save(task);
    }

    public Task getTask(Long id){
        return taskRepository.findById(id).orElse(null);
    }

    public Task saveTask(Task task){
        return taskRepository.save(task);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

}
