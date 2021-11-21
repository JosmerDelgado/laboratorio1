package View;

import javax.swing.*;

public class Manager {

    private JFrame frame;

    public Manager(JFrame frame){
        this.frame = frame;
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void redirectToHome(){
        Home home = new Home(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(home);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior
        frame.pack();
        frame.setVisible(true);
    }

    public void redirectToMain(){

        Main main = new Main(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(main);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior
        frame.pack();
        frame.setVisible(true);
    }

    public void redirectToEmployee(){

        EmployeeList main = new EmployeeList(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(main);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior
        frame.pack();
        frame.setVisible(true);
    }

    public void redirectToTask(){

        TaskList main = new TaskList(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(main);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior
        frame.pack();
        frame.setVisible(true);
    }

    public void redirectToEmployeeCreate() {

        EmployeeCreate employeeCreate = new EmployeeCreate(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(employeeCreate);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior
        frame.pack();
        frame.setVisible(true);
    }

    public void redirectToTaskCreate() {

        TaskCreate main = new TaskCreate(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(main);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior
        frame.pack();
        frame.setVisible(true);
    }

    public void redirectToProjectCreate() {

        ProjectCreate project = new ProjectCreate(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(project);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior
        frame.pack();
        frame.setVisible(true);
    }

    public void redirectToCompanyCreate() {

        CompanyCreate company = new CompanyCreate(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(company);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior
        frame.pack();
        frame.setVisible(true);
    }
}
