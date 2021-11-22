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
    private MyButton updateEmployeeButton;

    private InputWithLabel idInputWithLabel;
    private InputWithLabel nameInputWithLabel;
    private InputWithLabel lastNameInputWithLabel;
    private InputWithLabel rateInputWithLabel;
    private InputWithLabel projectInputWithLabel;


    public EmployeeCreate(Manager manager) {
        this.manager = manager;
        armar();
    }

    public EmployeeCreate(Manager manager, int id) {
        this.manager = manager;
        armar(id);
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

    public void armar(int id) {
        EmployeeService employeeService = new EmployeeService();
        Employee employee;
        try {
            employee = employeeService.search(id);
            System.out.println(employee);
            this.title = new Title("Update Employee");

            idInputWithLabel = new InputWithLabel("Document", employee.getIdentityNumber().toString() );
            nameInputWithLabel = new InputWithLabel("Name", employee.getName());
            lastNameInputWithLabel = new InputWithLabel("Last Name", employee.getLastName());
            rateInputWithLabel = new InputWithLabel("Rate Hourly", employee.getRatePerHour().toString());
            projectInputWithLabel = new InputWithLabel("Project Assigned", employee.getProjectId().toString());


            this.updateEmployeeButton = new MyButton("Update Employee");
            // TODO: this can be part of a wrapper;
            this.buttonBack = new MyButton("Back");

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



            this.add(this.title);

            this.add(idInputWithLabel);
            this.add(nameInputWithLabel);
            this.add(lastNameInputWithLabel);
            this.add(rateInputWithLabel);
            this.add(this.projectInputWithLabel);

            this.add(this.updateEmployeeButton);
            // TODO: this can be part of a wrapper;
            this.add(this.buttonBack);

            this.updateEmployeeButton.addActionListener(this);

            this.buttonBack.addActionListener(this);
        } catch (ServiceException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.createEmployeeButton){
            String name = this.nameInputWithLabel.getInput().getText();
            String lastName = this.lastNameInputWithLabel.getInput().getText();
            Integer id = Integer.parseInt(this.idInputWithLabel.getInput().getText());
            Double rate = Double.parseDouble(this.rateInputWithLabel.getInput().getText());
            int project = Integer.parseInt(this.projectInputWithLabel.getInput().getText());

            Employee employee = new Employee(name, lastName, rate, id, project);
            System.out.println(employee);
            EmployeeService employeeService = new EmployeeService();
            try {


                employeeService.create(employee);

            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        if(actionEvent.getSource() == this.updateEmployeeButton){
            String name = this.nameInputWithLabel.getInput().getText();
            String lastName = this.lastNameInputWithLabel.getInput().getText();
            Integer id = Integer.parseInt(this.idInputWithLabel.getInput().getText());
            Double rate = Double.parseDouble(this.rateInputWithLabel.getInput().getText());
            int project = Integer.parseInt(this.projectInputWithLabel.getInput().getText());

            Employee employee = new Employee(name, lastName, rate, id, project);
            System.out.println(employee);
            EmployeeService employeeService = new EmployeeService();
            try {


                employeeService.update(employee);

            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        if(actionEvent.getSource() == this.buttonBack){
            this.manager.redirectToMain();
        }
    }
}
