package View;

import Model.Exceptions.ServiceException;
import Model.Project;
import Model.Service.ProjectService;
import Model.TableModel.ProjectTableModel;
import View.Components.MyButton;
import View.Components.Title;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProjectList extends JPanel implements ActionListener {
    private Title title;
    private MyButton createTaskButton;

    // TODO: this can be part of a wrapper;
    private MyButton buttonBack;
    private ProjectTableModel projectTableModel;
    private JTable taskTable;
    private JScrollPane scrollToTable;
    private Manager manager;

    public ProjectList(Manager manager) {
        this.manager = manager;
        armar();
    }

    public void armar() {

        Project e = new Project(123, "Title");
        List<Project> listTask = new ArrayList<>();
        ProjectService taskService = new ProjectService();

        try {
            listTask = taskService.list();
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }

        listTask.add(e);
        projectTableModel = new ProjectTableModel(listTask);
        taskTable = new JTable(projectTableModel);
        scrollToTable = new JScrollPane(taskTable);
        this.add(scrollToTable);

        this.title = new Title("Project List");
        this.createTaskButton = new MyButton("Create Project");
        // TODO: this can be part of a wrapper;
        this.buttonBack = new MyButton("Back");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(this.title);
        this.add(this.createTaskButton);
        // TODO: this can be part of a wrapper;
        this.add(this.buttonBack);

        this.createTaskButton.addActionListener(this);

        this.buttonBack.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.buttonBack){
            this.manager.redirectToMain();
        }
        if(actionEvent.getSource() == this.createTaskButton){
            this.manager.redirectToProjectCreate();
        }
    }
}
