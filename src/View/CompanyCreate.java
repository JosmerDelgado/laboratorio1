package View;

import Model.Company;
import Model.Exceptions.ServiceException;
import Model.Project;
import Model.Service.CompanyService;
import Model.Service.ProjectService;
import View.Components.InputWithLabel;
import View.Components.MyButton;
import View.Components.Title;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompanyCreate extends JPanel implements ActionListener {

    private Manager manager;
    private Title title;
    private MyButton buttonBack;
    private MyButton createTaskButton;
    private InputWithLabel idInputWithLabel;
    private InputWithLabel titleInputWithLabel;

    public CompanyCreate(Manager manager) {
        this.manager = manager;
        armar();
    }

    public void armar() {
        this.title = new Title("New Company");

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

            Company company = new Company(title, id);
            System.out.println(company);
            CompanyService companyService = new CompanyService();
            try {
                companyService.create(company);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }
}
