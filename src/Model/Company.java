package Model;

import java.util.List;

public class Company {
    private String name;
    private List<Project> projects;
    private int id;

    public Company(String name, int id,List<Project> projects) {
        this.name = name;
        this.projects = projects;
        this.id = id;
    }

    public Company(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public List<Project> getProyects() {
        return projects;
    }

    public void setProyects(List<Project> projects) {
        this.projects = projects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }
}
