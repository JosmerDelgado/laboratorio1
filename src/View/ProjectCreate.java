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
    private MyButton createProjectButton;

    private MyButton updateProjectButton;
    private InputWithLabel idInputWithLabel;
    private InputWithLabel titleInputWithLabel;
//    private InputWithLabel companyInputWithLabel;

    public ProjectCreate(Manager manager) {
        this.manager = manager;
        armar();
    }

    public ProjectCreate(Manager manager, int id) {
        this.manager = manager;
        armar(id);
    }

    public void armar() {
        this.title = new Title("New Project");

        idInputWithLabel = new InputWithLabel("ID");
        titleInputWithLabel = new InputWithLabel("Title");
        this.createProjectButton = new MyButton("Create Task");
//        companyInputWithLabel = new InputWithLabel("Company Id");
        // TODO: this can be part of a wrapper;
        this.buttonBack = new MyButton("Back");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



        this.add(this.title);

        this.add(idInputWithLabel);
        this.add(titleInputWithLabel);
//        this.add(companyInputWithLabel);
        this.add(this.createProjectButton);
        // TODO: this can be part of a wrapper;
        this.add(this.buttonBack);

        this.createProjectButton.addActionListener(this);

        this.buttonBack.addActionListener(this);

    }

    public void armar(int id) {
        this.title = new Title("Update Project");
        ProjectService projectService = new ProjectService();
        try {
            Project project= projectService.search(id);
            idInputWithLabel = new InputWithLabel("ID", project.getId().toString());
            idInputWithLabel.getInput().setEnabled(false);
            titleInputWithLabel = new InputWithLabel("Title", project.getName());
            this.updateProjectButton = new MyButton("Update Task");
//        companyInputWithLabel = new InputWithLabel("Company Id");
            // TODO: this can be part of a wrapper;
            this.buttonBack = new MyButton("Back");

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



            this.add(this.title);

            this.add(idInputWithLabel);
            this.add(titleInputWithLabel);
//        this.add(companyInputWithLabel);
            this.add(this.updateProjectButton);
            // TODO: this can be part of a wrapper;
            this.add(this.buttonBack);

            this.updateProjectButton.addActionListener(this);

            this.buttonBack.addActionListener(this);

        } catch (ServiceException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error trying to get Project");
            manager.redirectToProjectList();
        }


    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.createProjectButton){
            Integer id = Integer.parseInt(this.idInputWithLabel.getInput().getText());
            String title = this.titleInputWithLabel.getInput().getText();
            Integer company =  0;//Integer.parseInt(this.companyInputWithLabel.getInput().getText());
            Project project = new Project(id, title);
            project.setCompanyId(company);
            System.out.println(project);
            ProjectService projectService = new ProjectService();
            try {
                projectService.create(project);
                manager.redirectToProjectList();

            } catch (ServiceException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error trying to create Project");

            }
        }
        if(actionEvent.getSource() == this.buttonBack){
            this.manager.redirectToMain();
        }

        if(actionEvent.getSource() == this.updateProjectButton){
            Integer id = Integer.parseInt(this.idInputWithLabel.getInput().getText());
            String title = this.titleInputWithLabel.getInput().getText();
            Integer company =  0;//Integer.parseInt(this.companyInputWithLabel.getInput().getText());
            Project project = new Project(id, title);
            project.setCompanyId(company);
            System.out.println(project);
            ProjectService projectService = new ProjectService();
            try {
                projectService.update(project);
                manager.redirectToProjectList();

            } catch (ServiceException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error trying to update Project");

            }
        }
    }
}
