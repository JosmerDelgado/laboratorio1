package View;

import Model.Employee;
import Model.Exceptions.ServiceException;
import Model.Project;
import Model.Service.TaskService;
import Model.Task;
import View.Components.InputWithLabel;
import View.Components.MyButton;
import View.Components.Title;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TaskCreate  extends JPanel implements ActionListener {

    private Manager manager;
    private Title title;
    private MyButton buttonBack;
    private MyButton createTaskButton;
    private MyButton updateTaskButton;
    private InputWithLabel idInputWithLabel;
    private InputWithLabel titleInputWithLabel;
    private InputWithLabel projectIdInputWithLabel;
    private InputWithLabel employeeIdInputWithLabel;
    private InputWithLabel estimationInputWithLabel;


    public TaskCreate(Manager manager) {
        this.manager = manager;
        armar();
    }

    public TaskCreate(Manager manager, int id) {
        this.manager = manager;
        armar(id);
    }

    public void armar() {
        this.title = new Title("New Task");

        idInputWithLabel = new InputWithLabel("ID");
        titleInputWithLabel = new InputWithLabel("Title");

        estimationInputWithLabel = new InputWithLabel("Estimation");
        projectIdInputWithLabel = new InputWithLabel("Project (Project ID)");
        employeeIdInputWithLabel = new InputWithLabel("Employee ID ");

        this.createTaskButton = new MyButton("Create Task");
        // TODO: this can be part of a wrapper;
        this.buttonBack = new MyButton("Back");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



        this.add(this.title);

        this.add(idInputWithLabel);
        this.add(titleInputWithLabel);
        this.add(projectIdInputWithLabel);
        this.add(employeeIdInputWithLabel);
        this.add(estimationInputWithLabel);

        this.add(this.createTaskButton);
        // TODO: this can be part of a wrapper;
        this.add(this.buttonBack);

        this.createTaskButton.addActionListener(this);

        this.buttonBack.addActionListener(this);

    }

    public void armar(int id) {
        this.title = new Title("Update Task");

        TaskService taskService = new TaskService();

        try {
            Task task = taskService.search(id);

            idInputWithLabel = new InputWithLabel("ID", task.getId().toString());
            idInputWithLabel.setEnabled(false);
            titleInputWithLabel = new InputWithLabel("Title", task.getTitle());

            estimationInputWithLabel = new InputWithLabel("Estimation", task.getEstimation().toString());
            projectIdInputWithLabel = new InputWithLabel("Project (Project ID)", task.getProject().getId().toString());
            employeeIdInputWithLabel = new InputWithLabel("Employee ID ", task.getAssigned().getIdentityNumber().toString());

            this.updateTaskButton = new MyButton("Create Task");
            // TODO: this can be part of a wrapper;
            this.buttonBack = new MyButton("Back");

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



            this.add(this.title);

            this.add(idInputWithLabel);
            this.add(titleInputWithLabel);
            this.add(projectIdInputWithLabel);
            this.add(employeeIdInputWithLabel);
            this.add(estimationInputWithLabel);

            this.add(this.updateTaskButton);
            // TODO: this can be part of a wrapper;
            this.add(this.buttonBack);

            this.updateTaskButton.addActionListener(this);

            this.buttonBack.addActionListener(this);
        } catch (ServiceException e) {
            e.printStackTrace();
        }



    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.createTaskButton){
            Integer id = Integer.parseInt(this.idInputWithLabel.getInput().getText());
            String title = this.titleInputWithLabel.getInput().getText();
            Integer project = Integer.parseInt(this.projectIdInputWithLabel.getInput().getText());
            Integer employee = Integer.parseInt(this.employeeIdInputWithLabel.getInput().getText());
            Integer estimation = Integer.parseInt(this.estimationInputWithLabel.getInput().getText());
            Task task = new Task(id, title);
            System.out.println(task);
            TaskService taskService = new TaskService();
            task.setAssigned(new Employee(employee));
            task.setEstimation(estimation);
            try {
                taskService.create(task, project);
            } catch (ServiceException e) {
                e.printStackTrace();
            }

        }
        if(actionEvent.getSource() == this.updateTaskButton){
            Integer id = Integer.parseInt(this.idInputWithLabel.getInput().getText());
            String title = this.titleInputWithLabel.getInput().getText();
            Integer projectId = Integer.parseInt(this.projectIdInputWithLabel.getInput().getText());
            Integer employee = Integer.parseInt(this.employeeIdInputWithLabel.getInput().getText());
            Integer estimation = Integer.parseInt(this.estimationInputWithLabel.getInput().getText());
            Task task = new Task(id, title);
            System.out.println(task);
            TaskService taskService = new TaskService();
            task.setAssigned(new Employee(employee));
            task.setEstimation(estimation);
            Project project = new Project(projectId);
            task.setProject(project);
            try {
                taskService.update(task);
            } catch (ServiceException e) {
                e.printStackTrace();
            }

        }
        if(actionEvent.getSource() == this.buttonBack){
            this.manager.redirectToMain();
        }
    }

}
