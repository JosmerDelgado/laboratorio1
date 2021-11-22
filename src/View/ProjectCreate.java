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
//    private InputWithLabel companyInputWithLabel;

    public ProjectCreate(Manager manager) {
        this.manager = manager;
        armar();
    }

    public void armar() {
        this.title = new Title("New Project");

        idInputWithLabel = new InputWithLabel("ID");
        titleInputWithLabel = new InputWithLabel("Title");
        this.createTaskButton = new MyButton("Create Task");
//        companyInputWithLabel = new InputWithLabel("Company Id");
        // TODO: this can be part of a wrapper;
        this.buttonBack = new MyButton("Back");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



        this.add(this.title);

        this.add(idInputWithLabel);
        this.add(titleInputWithLabel);
//        this.add(companyInputWithLabel);
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
            Integer company =  0;//Integer.parseInt(this.companyInputWithLabel.getInput().getText());
            Project project = new Project(id, title);
            project.setCompanyId(company);
            System.out.println(project);
            ProjectService projectService = new ProjectService();
            try {
                projectService.create(project);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        if(actionEvent.getSource() == this.buttonBack){
            this.manager.redirectToMain();
        }

    }
}
