package kz.group36.springboot36.repositories;


import kz.group36.springboot36.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByProjectId(Long id);
}
