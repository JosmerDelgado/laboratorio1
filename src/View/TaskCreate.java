package View;

import Model.Employee;
import Model.Exceptions.ServiceException;
import Model.Project;
import Model.Service.EmployeeService;
import Model.Service.ProjectService;
import Model.Service.TaskService;
import Model.Task;
import View.Components.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;


public class TaskCreate  extends JPanel implements ActionListener, ItemListener {

    private Manager manager;
    private Title title;
    private MyButton buttonBack;
    private MyButton createTaskButton;
    private MyButton updateTaskButton;
    private InputWithLabel idInputWithLabel;
    private InputWithLabel titleInputWithLabel;
    private ProjectComboBox projectComboBox;
    private EmployeeComboBox employeeComboBox;
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
        Vector<Project> projectVector= new Vector<>();
        ProjectService projectService = new ProjectService();

        Vector<Employee> employeeVector = new Vector<>();
        EmployeeService employeeService = new EmployeeService();


        try {
            List<Project> projectList = projectService.list();
            projectVector.addAll(projectList);
            employeeVector.addAll(employeeService.list(projectList.get(0).getId()));
        } catch (ServiceException e) {
            e.printStackTrace();
        }


        this.title = new Title("New Task");

        idInputWithLabel = new InputWithLabel("ID");
        titleInputWithLabel = new InputWithLabel("Title");

        estimationInputWithLabel = new InputWithLabel("Estimation");
        projectComboBox = new ProjectComboBox(projectVector);
        employeeComboBox = new EmployeeComboBox(employeeVector);
        projectComboBox.addItemListener(this);
        this.createTaskButton = new MyButton("Create Task");
        // TODO: this can be part of a wrapper;
        this.buttonBack = new MyButton("Back");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



        this.add(this.title);

        this.add(idInputWithLabel);
        this.add(titleInputWithLabel);
        this.add(projectComboBox);
        this.add(employeeComboBox);
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
        Vector<Project> projectVector= new Vector<>();
        ProjectService projectService = new ProjectService();
        Vector<Employee> employeeVector = new Vector<>();
        EmployeeService employeeService = new EmployeeService();
        try {
            projectVector.addAll(projectService.list());
            employeeVector.addAll(employeeService.list());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            Task task = taskService.search(id);

            idInputWithLabel = new InputWithLabel("ID", task.getId().toString());
            idInputWithLabel.getInput().setEnabled(false);

            titleInputWithLabel = new InputWithLabel("Title", task.getTitle());

            estimationInputWithLabel = new InputWithLabel("Estimation", task.getEstimation().toString());
            projectComboBox = new ProjectComboBox(projectVector, task.getProject());
            employeeComboBox = new EmployeeComboBox(employeeVector, task.getAssigned());
            projectComboBox.addItemListener(this);
            this.updateTaskButton = new MyButton("Update Task");
            // TODO: this can be part of a wrapper;
            this.buttonBack = new MyButton("Back");

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



            this.add(this.title);

            this.add(idInputWithLabel);
            this.add(titleInputWithLabel);
            this.add(projectComboBox);
            this.add(employeeComboBox);
            this.add(estimationInputWithLabel);

            this.add(this.updateTaskButton);
            // TODO: this can be part of a wrapper;
            this.add(this.buttonBack);

            this.updateTaskButton.addActionListener(this);

            this.buttonBack.addActionListener(this);
        } catch (ServiceException e) {
            e.printStackTrace();
            manager.redirectToTask();

            JOptionPane.showMessageDialog(null, "Error trying to get Task");

        }



    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.createTaskButton){
            try {
                Integer id = Integer.parseInt(this.idInputWithLabel.getInput().getText());
                String title = this.titleInputWithLabel.getInput().getText();
                Project project = ((Project)this.projectComboBox.getSelectedItem());

                Integer employee = this.employeeComboBox.getSelectedItem().getIdentityNumber();
                Integer estimation = Integer.parseInt(this.estimationInputWithLabel.getInput().getText());
                Task task = new Task(id, title);
                task.setProject(project);
                System.out.println(task);
                TaskService taskService = new TaskService();
                task.setAssigned(new Employee(employee));
                task.setEstimation(estimation);
                taskService.create(task);
                manager.redirectToTask();

            } catch (ServiceException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error trying to create Task");

            }

        }
        if(actionEvent.getSource() == this.updateTaskButton){
            Integer id = Integer.parseInt(this.idInputWithLabel.getInput().getText());
            String title = this.titleInputWithLabel.getInput().getText();
            Integer projectId = ((Project)this.projectComboBox.getSelectedItem()).getId();

            Integer employee = this.employeeComboBox.getSelectedItem().getIdentityNumber();
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
                manager.redirectToTask();

            } catch (ServiceException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error trying to update Task");

            }

        }
        if(actionEvent.getSource() == this.buttonBack){
            this.manager.redirectToMain();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
