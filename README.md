# Task

#### DataBase: PostgreSql
POJO classes (file name model): Project, Task 

Project table
- id
- name
- start date
- campation date
- status
- priority

Task table
- id
- name
- status
- description 
- priority
- project (annotation @ManyToone fetch Lazy)

Repositoryies: 
- ProjectRepository extends JpaRepository
- TaskRepository extends JpaRepository

Service for Repositoryies
 - ProjectService 

Templates:
- index.html (all pjrojects)
- addproject.html
- projectdetails.html (details of project and project's tasks )
- taskdetails.html (details of task)
- addtask.html


Dependencies
- postgresql
- Spring Data JPA
- thymeleaf
- lombok



