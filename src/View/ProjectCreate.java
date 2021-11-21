package View;

import Model.Exceptions.ServiceException;
import Model.Project;
import Model.Service.ProjectService;
import Model.Task;
import View.Components.InputWithLabel;
import View.Components.MyButton;
import View.Components.Title;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectCreate extends JPanel implements ActionListener {

    private Manager manager;
    private Title title;
    private MyButton buttonBack;
    private MyButton createTaskButton;
    private InputWithLabel idInputWithLabel;
    private InputWithLabel titleInputWithLabel;

    public ProjectCreate(Manager manager) {
        this.manager = manager;
        armar();
    }

    public void armar() {
        this.title = new Title("New Task");

        idInputWithLabel = new InputWithLabel("ID");
        titleInputWithLabel = new InputWithLabel("Title");
        this.createTaskButton = new MyButton("Create Task");
        // TODO: this can be part of a wrapper;
        this.buttonBack = new MyButton("Back");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



        this.add(this.title);

        this.add(idInputWithLabel);
        this.add(titleInputWithLabel);

        this.add(this.createTaskButton);
        // TODO: this can be part of a wrapper;
        this.add(this.buttonBack);

        this.createTaskButton.addActionListener(this);

        this.buttonBack.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.createTaskButton){
            Integer id = Integer.parseInt(this.idInputWithLabel.getInput().getText());
            String title = this.titleInputWithLabel.getInput().getText();

            Project project = new Project(id, title);
            System.out.println(project);
            ProjectService projectService = new ProjectService();
            try {
                projectService.create(project);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }
}
