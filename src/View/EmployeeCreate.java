package View;

import Model.Employee;
import Model.Exceptions.ServiceException;
import Model.Service.EmployeeService;
import View.Components.InputWithLabel;
import View.Components.MyButton;
import View.Components.Title;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EmployeeCreate  extends JPanel implements ActionListener {

    private Manager manager;
    private Title title;
    private MyButton buttonBack;
    private MyButton createEmployeeButton;
    private InputWithLabel idInputWithLabel;
    private InputWithLabel nameInputWithLabel;
    private InputWithLabel lastNameInputWithLabel;
    private InputWithLabel rateInputWithLabel;
    private InputWithLabel projectInputWithLabel;


    public EmployeeCreate(Manager manager) {
        this.manager = manager;
        armar();
    }

    public void armar() {
        this.title = new Title("New Employee");

        idInputWithLabel = new InputWithLabel("Document");
        nameInputWithLabel = new InputWithLabel("Name");
        lastNameInputWithLabel = new InputWithLabel("Last Name");
        rateInputWithLabel = new InputWithLabel("Rate Hourly");
        projectInputWithLabel = new InputWithLabel("Project Assigned");


        this.createEmployeeButton = new MyButton("Create Employee");
        // TODO: this can be part of a wrapper;
        this.buttonBack = new MyButton("Back");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



        this.add(this.title);

        this.add(idInputWithLabel);
        this.add(nameInputWithLabel);
        this.add(lastNameInputWithLabel);
        this.add(rateInputWithLabel);
        this.add(this.projectInputWithLabel);

        this.add(this.createEmployeeButton);
        // TODO: this can be part of a wrapper;
        this.add(this.buttonBack);

        this.createEmployeeButton.addActionListener(this);

        this.buttonBack.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.createEmployeeButton){
            String name = this.nameInputWithLabel.getInput().getText();
            String lastName = this.nameInputWithLabel.getInput().getText();
            Integer id = Integer.parseInt(this.idInputWithLabel.getInput().getText());
            Double rate = Double.parseDouble(this.rateInputWithLabel.getInput().getText());

            Employee employee = new Employee(name, lastName, rate, id);
            System.out.println(employee);
            EmployeeService employeeService = new EmployeeService();
            try {
                Integer projectId;
                String project = this.projectInputWithLabel.getInput().getText();
                if(!project.equals("")){
                    projectId = Integer.parseInt(project);
                    employeeService.create(employee, projectId);
                } else {
                    employeeService.create(employee);

                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }
}
