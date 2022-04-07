package kz.group36.springboot36.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_task")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String taskName;

    private String status;

    private String taskDescription;

    private int priorityTask;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

}
