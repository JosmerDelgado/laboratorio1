package View;

import Model.Employee;
import Model.Exceptions.ServiceException;
import Model.Project;
import Model.Service.EmployeeService;
import Model.Service.ProjectService;
import View.Components.InputWithLabel;
import View.Components.MyButton;
import View.Components.ProjectComboBox;
import View.Components.Title;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


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
    private ProjectComboBox projectComboBox;


    public EmployeeCreate(Manager manager) {
        this.manager = manager;
        armar();
    }

    public EmployeeCreate(Manager manager, int id) {
        this.manager = manager;
        armar(id);
    }

    public void armar() {

        Vector<Project> projectVector= new Vector<>();
        ProjectService projectService = new ProjectService();

        try {
            projectVector.addAll(projectService.list());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        this.title = new Title("New Employee");

        idInputWithLabel = new InputWithLabel("Document");
        nameInputWithLabel = new InputWithLabel("Name");
        lastNameInputWithLabel = new InputWithLabel("Last Name");
        rateInputWithLabel = new InputWithLabel("Rate Hourly");
        projectComboBox = new ProjectComboBox(projectVector);


        this.createEmployeeButton = new MyButton("Create Employee");
        // TODO: this can be part of a wrapper;
        this.buttonBack = new MyButton("Back");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



        this.add(this.title);

        this.add(idInputWithLabel);
        this.add(nameInputWithLabel);
        this.add(lastNameInputWithLabel);
        this.add(rateInputWithLabel);
        this.add(this.projectComboBox);

        this.add(this.createEmployeeButton);
        // TODO: this can be part of a wrapper;
        this.add(this.buttonBack);

        this.createEmployeeButton.addActionListener(this);

        this.buttonBack.addActionListener(this);

    }

    public void armar(int id) {
        Vector<Project> projectVector= new Vector<>();
        ProjectService projectService = new ProjectService();

        try {
            projectVector.addAll(projectService.list());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        EmployeeService employeeService = new EmployeeService();
        Employee employee;
        try {
            employee = employeeService.search(id);


            this.title = new Title("Update Employee");

            idInputWithLabel = new InputWithLabel("Document", employee.getIdentityNumber().toString() );
            idInputWithLabel.getInput().setEnabled(false);

            nameInputWithLabel = new InputWithLabel("Name", employee.getName());
            lastNameInputWithLabel = new InputWithLabel("Last Name", employee.getLastName());
            rateInputWithLabel = new InputWithLabel("Rate Hourly", employee.getRatePerHour().toString());
            projectComboBox = new ProjectComboBox(projectVector, new Project(employee.getProjectId()));


            this.updateEmployeeButton = new MyButton("Update Employee");
            // TODO: this can be part of a wrapper;
            this.buttonBack = new MyButton("Back");

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



            this.add(this.title);

            this.add(idInputWithLabel);
            this.add(nameInputWithLabel);
            this.add(lastNameInputWithLabel);
            this.add(rateInputWithLabel);
            this.add(this.projectComboBox);

            this.add(this.updateEmployeeButton);
            // TODO: this can be part of a wrapper;
            this.add(this.buttonBack);

            this.updateEmployeeButton.addActionListener(this);

            this.buttonBack.addActionListener(this);

        } catch (ServiceException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error trying to get Employee");
            manager.redirectToEmployee();
        }



    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.createEmployeeButton){
            String name = this.nameInputWithLabel.getInput().getText();
            String lastName = this.lastNameInputWithLabel.getInput().getText();
            Integer id = Integer.parseInt(this.idInputWithLabel.getInput().getText());
            Double rate = Double.parseDouble(this.rateInputWithLabel.getInput().getText());
            int project = ((Project)this.projectComboBox.getSelectedItem()).getId();

            Employee employee = new Employee(name, lastName, rate, id, project);
            System.out.println(employee);
            EmployeeService employeeService = new EmployeeService();
            try {


                employeeService.create(employee);
                manager.redirectToEmployee();
            } catch (ServiceException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error trying to create Employee");
            }
        }

        if(actionEvent.getSource() == this.updateEmployeeButton){
            String name = this.nameInputWithLabel.getInput().getText();
            String lastName = this.lastNameInputWithLabel.getInput().getText();
            Integer id = Integer.parseInt(this.idInputWithLabel.getInput().getText());
            Double rate = Double.parseDouble(this.rateInputWithLabel.getInput().getText());
            int project = ((Project)this.projectComboBox.getSelectedItem()).getId();


            Employee employee = new Employee(name, lastName, rate, id, project);
            System.out.println(employee);
            EmployeeService employeeService = new EmployeeService();
            try {


                employeeService.update(employee);
                manager.redirectToEmployee();

            } catch (ServiceException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error trying to update Employee");

            }
        }
        if(actionEvent.getSource() == this.buttonBack){
            this.manager.redirectToEmployee();
        }
    }
}
