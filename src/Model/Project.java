package Model;

import java.util.Objects;

public class Project {
    private Integer id;
    private String name;
    private String description;
    private Task[] tasks;
    private Employee[] employees;
    private Integer companyId;

    public Project(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public Project(int id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
